import java.util.Random;

public class MRT {
    private boolean isBusy = false;

    public synchronized boolean service(Patient patient) throws InterruptedException {
        if (isBusy) {
            return false;
        }
        isBusy = true;
        System.out.println("МРТ принимает пациента " + patient.getId());

        Thread.sleep(new Random().nextInt(5000));

        isBusy = false;
        System.out.println("МРТ освободился");
        notifyAll();
        return true;
    }
}
