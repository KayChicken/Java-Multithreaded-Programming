import java.util.Random;

public class App {
    private static final int TOTAL_PERSONS = 100;

    public static void main(String[] args) throws InterruptedException {
        Window[] windows = { new Window(Category.ANY), new Window(Category.YOUNG), new Window(Category.BUSINESS) };
        Thread[] persons = new Thread[TOTAL_PERSONS];

        for (int i = 0; i < TOTAL_PERSONS; i++) {
            Category category = Category.getRandomCategory();
            persons[i] = new Thread(new Person(category, windows));
            int random = new Random().nextInt(100, 300);
            Thread.sleep(random);
            persons[i].start();
        }

        for (Thread person : persons) {
            person.join();
        }

        for (Category category : Category.values()) {
            if (category != Category.ANY) {
                System.out.println("Процент ушедших клиентов категории " + category + ": " +
                        (double) Person.getLeftCount(category) / TOTAL_PERSONS * 100 + "%");
            }
        }
    }
}
