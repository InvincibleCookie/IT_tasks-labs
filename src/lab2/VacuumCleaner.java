package lab2;

public class VacuumCleaner extends Appliance {
    private int suctionPower;
    private static int objectCount = 0;

    public VacuumCleaner(String brand, int voltage, int amperage, int suctionPower) {
        super(brand, voltage, amperage);
        this.suctionPower = suctionPower;
        objectCount++;
    }

    public int getSuctionPower() {
        return suctionPower;
    }

    public void setSuctionPower(int suctionPower) {
        this.suctionPower = suctionPower;
    }
    public static int getObjectCount() {
        return objectCount;
    }

    @Override
    public int power() {
        return super.power() + suctionPower;
    }
}
