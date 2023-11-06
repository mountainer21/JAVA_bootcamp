package ex01;

public class Program {
    public static void main(String[] args) {
        // Считываем количество выводов из параметров командной строки
        int count = 50;
        for (String arg : args) {
            if (arg.startsWith("--count=")) {
                count = Integer.parseInt(arg.substring(8));
            }
        }


        EggHenOrchestrator orchestrator = new EggHenOrchestrator();
        EggThread eggThread = new EggThread(orchestrator, count);
        HenThread henThread = new HenThread(orchestrator, count);

        eggThread.start();
        henThread.start();

        // Завершения работы потоков
        try {
            eggThread.join();
            henThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}