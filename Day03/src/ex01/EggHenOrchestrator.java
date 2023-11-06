package ex01;

public class EggHenOrchestrator {
    private boolean isEggTurn = true;

    public synchronized void getNextTurn(boolean isEgg) throws InterruptedException {
        while (isEgg != isEggTurn) {
            wait();
        }
        System.out.println(isEgg ? "Egg" : "Hen");
        isEggTurn = !isEggTurn;
        notify();
    }
}
