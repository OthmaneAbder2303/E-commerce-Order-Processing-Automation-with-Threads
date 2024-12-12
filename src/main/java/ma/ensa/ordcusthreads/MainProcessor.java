package ma.ensa.ordcusthreads;

public class MainProcessor {
    public static void main(String[] args) {
        FileHandler fileHandler = new FileHandler();
        OrderProcessorThread orderProcessorThread = new OrderProcessorThread(fileHandler);
        orderProcessorThread.start();
    }
}
