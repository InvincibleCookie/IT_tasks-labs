package lab2;

import java.util.ArrayList;

public class Fridge2 extends Fridge{
    public int capacity2;
    public Fridge2(String brand, int voltage, int amperage, int capacity, int capacity2) {
        super(brand, voltage, amperage, capacity);
        this.capacity2 = capacity2;
    }
        public int getCapacity2() {
            return capacity2;
        }

        public void setCapacity2(int capacity2) {
            this.capacity2 = capacity2;
        }
}
