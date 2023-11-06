package ex00;

public class EggThread extends Thread {
    private final int count;

    public EggThread(int count) {
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            System.out.println("Egg");
            try {
                Thread.sleep(100); // Задержка между выводами
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}