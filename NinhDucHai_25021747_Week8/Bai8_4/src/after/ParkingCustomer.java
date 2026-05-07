package after;

import java.util.ArrayList;
import java.util.List;

public class ParkingCustomer {
    private final String name;
    private final List<ParkingTicket> tickets = new ArrayList<>();

    public ParkingCustomer(String name) {
        this.name = name;
    }

    public void addTicket(ParkingTicket ticket) {
        tickets.add(ticket);
    }

    public String receipt() {
        String result = "Parking Receipt for " + name + "\n";

        for (ParkingTicket each : tickets) {
            result += "\t" + each.getVehicle().getPlate() + "\t" + each.getFee() + "\n";
        }

        result += "Total fee is " + getTotalFee() + "\n";
        result += "You earned " + getTotalBonusPoints() + " bonus points";
        
        return result;
    }

    // Replace Temp with Query: extracting total fee to a separate method
    private double getTotalFee() {
        double total = 0;
        for (ParkingTicket each : tickets) {
            total += each.getFee();
        }
        return total;
    }

    // Replace Temp with Query: extracting total points to a separate method
    private int getTotalBonusPoints() {
        int points = 0;
        for (ParkingTicket each : tickets) {
            points += each.getBonusPoints();
        }
        return points;
    }
}
