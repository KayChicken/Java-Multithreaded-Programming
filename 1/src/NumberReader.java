import java.util.concurrent.CopyOnWriteArrayList;

public class NumberReader implements Runnable {

  private final CopyOnWriteArrayList<Integer> listOfNumbers;

  public NumberReader(CopyOnWriteArrayList<Integer> listOfNumbers) {
    this.listOfNumbers = listOfNumbers;
  }
  
  @Override
  public void run() {
      while (true) {
          if (!listOfNumbers.isEmpty()) {
              for (Integer number : listOfNumbers) {
                  System.out.println("Прочитано число: " + number);
              }
          } else {
              System.out.println("Список пуст.");
          }
          try {
              Thread.sleep(700);
          } catch (InterruptedException e) {
              Thread.currentThread().interrupt();
              System.out.println("Поток чтения был прерван.");
              break;
          }
      }
  }
}
