package ex01;

class HenThread extends Thread {
    private final EggHenOrchestrator orchestrator;
    private final int count;

    public HenThread(EggHenOrchestrator orchestrator, int count) {
        this.orchestrator = orchestrator;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                orchestrator.getNextTurn(false);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}