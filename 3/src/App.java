import java.util.concurrent.atomic.AtomicInteger;

public class App {
    public static int maxQueueSize = 0;
    public static AtomicInteger queue = new AtomicInteger(0);

    public static void main(String[] args) throws Exception {

        Therapist therapist = new Therapist();
        MRT mrt = new MRT();

        for (int i = 0; i < 5; i++) {
            Patient patient = new Patient(i + 1, therapist, mrt);
            queue.incrementAndGet();
            new Thread(patient).start();

            if (queue.get() > maxQueueSize) {
                maxQueueSize = queue.get();
            }

            Thread.sleep(5000); 
        }

        while (queue.get() != 0) {
            Thread.sleep(1000);
        }

        System.out.println("Максимальная длина очереди: " + maxQueueSize);
    }
}