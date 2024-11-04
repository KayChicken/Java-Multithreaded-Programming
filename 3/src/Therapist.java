import java.util.Random;

public class Therapist {
    private boolean isBusy = false;

    public synchronized boolean service(Patient patient) throws InterruptedException {
        if (isBusy) {
            return false;
        }
        isBusy = true;
        System.out.println("Терапевт принимает пациента " + patient.getId());

        Thread.sleep(new Random().nextInt(3000));

        isBusy = false;
        System.out.println("Терапевт освободился");
        notifyAll();
        return true;
    }
}
