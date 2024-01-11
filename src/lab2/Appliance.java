package lab2;

public abstract class Appliance {
    private String brand;
    private int voltage;
    private int amperage;

    public Appliance(String brand, int voltage, int amperage){
        this.brand = brand;
        this.voltage = voltage;
        this.amperage = amperage;
    }
    public String getBrand(){
        return brand;
    }
    public int getVoltage(){
        return voltage;
    }
    public int getAmperage() {
        return amperage;
    }
    public void setBrand(String brand){
        this.brand = brand;
    }
    public void setVoltage(int voltage){
        this.voltage = voltage;
    }
    public void setAmperage(int amperage){
        this.amperage = amperage;
    }
    public int power(){
        return voltage * amperage;
    }
}