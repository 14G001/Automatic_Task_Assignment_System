package Main.Report;

public class Report {
    private String objectID;
    private Double totalCost;

    public Report(String ID, Double cost){
        this.objectID = ID;
        this.totalCost = cost;
    }


    @Override
    public String toString() {
        return (objectID==null? "General Report\n" : objectID + ", ") +
                "totalCost = " + totalCost + "\n";
    }
}
