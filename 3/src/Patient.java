public class Patient implements Runnable {
    private final int id;
    private final Therapist therapist;
    private final MRT mrt;

    public Patient(int id, Therapist therapist, MRT mrt) {
        this.id = id;
        this.therapist = therapist;
        this.mrt = mrt;
    }

    @Override
    public void run() {
        try {

            synchronized (therapist) {
                while (!therapist.service(this)) {
                    therapist.wait();
                }
            }

            synchronized (mrt) {
                while (!mrt.service(this)) {
                    mrt.wait();
                }
            }
            App.queue.decrementAndGet();
            System.out.println("Пациент " + id + " завершил обследование.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    public int getId() {
        return id;
    }
}
