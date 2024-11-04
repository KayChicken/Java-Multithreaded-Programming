import java.util.concurrent.CopyOnWriteArrayList;

public class NumberWriter implements Runnable{
   private final CopyOnWriteArrayList<Integer> listOfNumbers;

  public NumberWriter(CopyOnWriteArrayList<Integer> listOfNumbers) {
    this.listOfNumbers = listOfNumbers;
  }

  @Override
    public void run() {
        for (int i = 1; i <= 14; i++) {
            listOfNumbers.add(i);
            System.out.println("Добавлено число: " + i);
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Поток записи был прерван.");
            }
        }
    }
  
  
}
