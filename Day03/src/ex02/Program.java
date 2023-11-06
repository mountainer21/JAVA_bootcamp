package ex02;

import java.util.Arrays;

public class Program {
    private static final Object sumLock = new Object();
    private static int sum = 0;
    private static int threadNumber = 1;

    public static void main(String[] args) {
        if (args.length != 2) {
            System.err.println("Usage: Program --arraySize=<array size> --threadsCount=<threads count>");
            System.exit(1);
        }

        // Считываем количество выводов из параметров командной строки
        int arraySize = 0;
        int threadsCount = 0;

        for (String arg : args) {
            if (arg.startsWith("--arraySize=")) {
                arraySize = Integer.parseInt(arg.substring(12));
            } else if (arg.startsWith("--threadsCount=")) {
                threadsCount = Integer.parseInt(arg.substring(15));
            }
        }

        int[] array = generateArray(arraySize);

        System.out.println("Array: " + Arrays.toString(array));
        int expectedSum = sumArraySequentially(array);

        System.out.println("Expected sum: " + expectedSum);

        int sumByThreads = sumArrayByThreads(array, threadsCount);

        System.out.println("Sum by threads: " + sumByThreads);
    }

    private static int[] generateArray(int size) {
        int[] array = new int[size];
        for (int i = 0; i < size; i++) {
            array[i] = (int) (Math.random() * 200) - 100;
        }
        return array;
    }

    private static int sumArraySequentially(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    private static int sumArrayByThreads(int[] array, int threadsCount) {
        int sectionSize = array.length / threadsCount;

        Thread[] threads = new Thread[threadsCount];

        for (int i = 0; i < threadsCount; i++) {
            int startIndex = i * sectionSize;
            int endIndex = (i == threadsCount - 1) ? array.length : (i + 1) * sectionSize;
            threads[i] = new Thread(new SumThread(array, startIndex, endIndex));
            threads[i].start();
        }

        // ожидание завершения всех потоков
        for (int i = 0; i < threadsCount; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        return sum;
    }

    private static class SumThread implements Runnable {
        private int[] array;
        private int startIndex;
        private int endIndex;

        public SumThread(int[] array, int startIndex, int endIndex) {
            this.array = array;
            this.startIndex = startIndex;
            this.endIndex = endIndex;
        }

        @Override
        public void run() {
            int currentThreadNumber;
            synchronized (Program.class) {
                currentThreadNumber = threadNumber++;
            }
            int localSum = sumSection();
            System.out.println("Thread " + currentThreadNumber + ": from " + startIndex + " to " + (endIndex - 1)
                    + " sum is " + localSum);
            addSum(localSum);
        }

        private int sumSection() {
            int sum = 0;
            for (int i = startIndex; i < endIndex; i++) {
                sum += array[i];
            }
            return sum;
        }

        private void addSum(int s) {
            synchronized (sumLock) {
                sum += s;
            }
        }
    }
}