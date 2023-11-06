package ex01;

class EggThread extends Thread {
    private final EggHenOrchestrator orchestrator;
    private final int count;

    public EggThread(EggHenOrchestrator orchestrator, int count) {
        this.orchestrator = orchestrator;
        this.count = count;
    }

    public void run() {
        for (int i = 0; i < count; i++) {
            try {
                orchestrator.getNextTurn(true);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
