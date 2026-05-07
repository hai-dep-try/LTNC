package before;

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
        double totalFee = 0;
        int bonusPoints = 0;
        String result = "Parking Receipt for " + name + "\n";

        for (ParkingTicket each : tickets) {
            double thisFee = 0;

            // calculate fee per ticket
            switch (each.getVehicle().getType()) {
                case Vehicle.CAR:
                    thisFee += 10;
                    if (each.getHours() > 2) {
                        thisFee += (each.getHours() - 2) * 3;
                    }
                    break;
                case Vehicle.BIKE:
                    thisFee += 5;
                    if (each.getHours() > 3) {
                        thisFee += (each.getHours() - 3) * 2;
                    }
                    break;
                case Vehicle.TRUCK:
                    thisFee += 15 + each.getHours() * 4;
                    break;
            }

            totalFee += thisFee;

            // bonus points
            bonusPoints++;
            if (each.getVehicle().getType() == Vehicle.TRUCK && each.getHours() > 5) {
                bonusPoints++;
            }

            result += "\t" + each.getVehicle().getPlate() + "\t" + thisFee + "\n";
        }

        result += "Total fee is " + totalFee + "\n";
        result += "You earned " + bonusPoints + " bonus points";
        return result;
    }
}
