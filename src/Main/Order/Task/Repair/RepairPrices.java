package Main.Order.Task.Repair;

public class RepairPrices {
    private double easyTaskPrice;
    private double mediumTaskPrice;
    private double complexTaskPrice;

    public RepairPrices(double easyTaskPrice, double mediumTaskPrice, double complexTaskPrice) {
        this.easyTaskPrice = easyTaskPrice;
        this.mediumTaskPrice = mediumTaskPrice;
        this.complexTaskPrice = complexTaskPrice;
    }

    public double getEasyTaskPrice() { return easyTaskPrice; }
    public double getMediumTaskPrice() { return mediumTaskPrice; }
    public double getComplexTaskPrice() { return complexTaskPrice; }
}
