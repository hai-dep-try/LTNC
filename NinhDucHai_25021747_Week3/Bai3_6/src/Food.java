import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class Food extends Product {
    private LocalDate expirationDate;

    public Food(String name, double basePrice, LocalDate expirationDate) {
        super(name, basePrice);
        this.expirationDate = expirationDate;
    }

    @Override
    public double getFinalPrice() {
        long daysLeft = ChronoUnit.DAYS.between(LocalDate.now(), expirationDate);
        if (daysLeft >= 0 && daysLeft < 7) {
            return basePrice * 0.8;
        }
        return basePrice;
    }
}