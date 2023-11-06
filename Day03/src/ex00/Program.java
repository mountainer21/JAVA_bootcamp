package ex00;

public class Program {

    public static void main(String[] args) {
        // Считываем количество выводов из параметров командной строки
        int count = 50;
        for (String arg : args) {
            if (arg.startsWith("--count=")) {
                count = Integer.parseInt(arg.substring(8));
            }
        }

        // Создаем потоки и запускаем их
        EggThread eggThread = new EggThread(count);
        HenThread henThread = new HenThread(count);
        eggThread.start();
        henThread.start();

        // Дожидаемся завершения работы потоков
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Главный поток выводит свое сообщение 'Human' count раз
        for (int i = 0; i < count; i++) {
            System.out.println("Human");
        }

    }
}