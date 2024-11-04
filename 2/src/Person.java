import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

class Person implements Runnable {
    private final Category category;
    private final Window[] windows;
    private static final Random random = new Random();
    private static final AtomicInteger[] leftCount = {
            new AtomicInteger(0), // OLD
            new AtomicInteger(0), // YOUNG
            new AtomicInteger(0), // BUSINESS
    };

    public Person(Category category, Window[] windows) {
        this.category = category;
        this.windows = windows;
    }

    @Override
    public void run() {
        int windowIndex = random.nextInt(windows.length);
        Window window = windows[windowIndex];

        if (!window.service(this)) {
            incrementLeftCount(category);
        }
    }

    public Category getCategory() {
        return category;
    }

    private void incrementLeftCount(Category category) {
        switch (category) {
            case OLD -> leftCount[0].incrementAndGet();
            case YOUNG -> leftCount[1].incrementAndGet();
            case BUSINESS -> leftCount[2].incrementAndGet();
        }
    }

    public static int getLeftCount(Category category) {
        return switch (category) {
            case OLD -> leftCount[0].get();
            case YOUNG -> leftCount[1].get();
            case BUSINESS -> leftCount[2].get();
            default -> 0;
        };
    }
}
