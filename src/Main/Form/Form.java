package Main.Form;


public class Form {
    private int clientID;
    private String place;
    private boolean clean;
    private int lastCleaningDate;
    private int residue;
    private int pets;
    private boolean sort;
    private boolean shine;
    private boolean floor;
    private boolean furniture;
    private boolean repair;
    private boolean electrical;
    private int electricalComplexity;
    private boolean gas;
    private int gasComplexity;

    public Form(int clientID) {
        this.clientID = clientID;
    }

    public int getClientID() {
        return clientID;
    }

    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public boolean isClean() {
        return clean;
    }

    public void setClean(boolean clean) {
        this.clean = clean;
    }

    public int getLastCleaningDate() {
        return lastCleaningDate;
    }

    public void setLastCleaningDate(int lastCleaningDate) {
        this.lastCleaningDate = lastCleaningDate;
    }

    public int getResidue() {
        return residue;
    }

    public void setResidue(int residue) {
        this.residue = residue;
    }

    public int getPets() {
        return pets;
    }

    public void setPets(int pets) {
        this.pets = pets;
    }

    public boolean isSort() {
        return sort;
    }

    public void setSort(boolean sort) {
        this.sort = sort;
    }

    public boolean isShine() {
        return shine;
    }

    public void setShine(boolean shine) {
        this.shine = shine;
    }

    public boolean isFloor() {
        return floor;
    }

    public void setFloor(boolean floor) {
        this.floor = floor;
    }

    public boolean isFurniture() {
        return furniture;
    }

    public void setFurniture(boolean furniture) {
        this.furniture = furniture;
    }

    public boolean isRepair() {
        return repair;
    }

    public void setRepair(boolean repair) {
        this.repair = repair;
    }

    public boolean isElectrical() {
        return electrical;
    }

    public void setElectrical(boolean electrical) {
        this.electrical = electrical;
    }

    public int getElectricalComplexity() {
        return electricalComplexity;
    }

    public void setElectricalComplexity(int electricalComplexity) {
        this.electricalComplexity = electricalComplexity;
    }

    public boolean isGas() {
        return gas;
    }

    public void setGas(boolean gas) {
        this.gas = gas;
    }

    public int getGasComplexity() {
        return gasComplexity;
    }

    public void setGasComplexity(int gasComplexity) {
        this.gasComplexity = gasComplexity;
    }


}
