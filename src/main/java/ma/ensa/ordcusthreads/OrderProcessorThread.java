package ma.ensa.ordcusthreads;

public class OrderProcessorThread extends Thread {
    private FileHandler fileHandler;

    public OrderProcessorThread(FileHandler fileHandler) {
        this.fileHandler = fileHandler;
    }

    @Override
    public void run() {
        while (true) {
            try {
                fileHandler.handleSourceDirectory();
                Thread.sleep(3600 * 1000); // Sleep for 1 hour
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}