import java.util.Random;

public enum Category {
    OLD,
    YOUNG,
    BUSINESS,
    ANY; 

    public static Category getRandomCategory() {
        Random random = new Random();
        return values()[random.nextInt(3)];
    }
}
