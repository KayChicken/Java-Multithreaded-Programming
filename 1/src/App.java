import java.util.concurrent.CopyOnWriteArrayList;

public class App {
    public static void main(String[] args) throws Exception {
        CopyOnWriteArrayList<Integer> listOfNumbers = new CopyOnWriteArrayList<>();

        Thread writerThread = new Thread(new NumberWriter(listOfNumbers));
        Thread readerThread = new Thread(new NumberReader(listOfNumbers));
        
        writerThread.start();
        readerThread.start();
        
        try {
            writerThread.join();
            readerThread.interrupt();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Основной поток был прерван.");
        }
    }
}
