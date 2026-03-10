import java.util.*;

class ParkingLot {

    String[] table;
    int size;

    ParkingLot(int capacity) {
        table = new String[capacity];
        size = capacity;
    }

    int hash(String plate) {
        return Math.abs(plate.hashCode()) % size;
    }

    public int parkVehicle(String plate) {

        int index = hash(plate);
        int probes = 0;

        while (table[index] != null) {

            index = (index + 1) % size;
            probes++;
        }

        table[index] = plate;

        System.out.println("Vehicle parked at spot "
                + index + " probes=" + probes);

        return index;
    }

    public void exitVehicle(String plate) {

        for (int i = 0; i < size; i++) {

            if (plate.equals(table[i])) {

                table[i] = null;
                System.out.println("Vehicle exited spot " + i);
                return;
            }
        }
    }
}