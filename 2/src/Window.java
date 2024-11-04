class Window {
    private final Category category;
    private boolean isBusy = false;

    public Window(Category category) {
        this.category = category;
    }

    public boolean service(Person person) {
        synchronized (this) {
            if (isBusy || !canEnter(person)) {
                return false;
            }

            isBusy = true;
            try {
                System.out.println("Обслуживается гражданин категории: " + person.getCategory() + " в окне категории: " + category);
                Thread.sleep(200); 
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                isBusy = false;
            }
        }
        return true;
    }

    private boolean canEnter(Person person) {
        return category == Category.ANY || category == person.getCategory();
    }
}
