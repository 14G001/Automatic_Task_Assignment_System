package Main.Order;
import java.util.HashMap;
import static Utilities.EasierCommands.print;
import Utilities.Time;

import Main.Report.Affordable;
import Main.Enterprise.OrderProcessingSystem;
import Main.Order.Place.*;
import Main.Order.Task.Task;




public class Order implements Affordable {
    public static final int NOT_ASSIGNED_ID = -1;

    private int ID; // <--- ONLY SYSTEM CAN MODIFY IT'S VALUE
    private int clientID;
    private HashMap<Integer, Task> tasks; // Key: task type (clean, sort, shine)
    private Place place;
    private double fullPrice;

    public Order(int clientID, Task[] requestedTasks, Place place) {
        this.ID = NOT_ASSIGNED_ID;
        this.clientID = clientID;
        this.tasks = new HashMap<Integer, Task>();
        for (Task task:requestedTasks) {
            //if(task != null) {
                this.tasks.put(task.getTypeID(), task);
            //}
        }
        this.place = place;
    }
    public Order() {
        this.ID = NOT_ASSIGNED_ID;
    }

    @Override
    public String toString() {
        return "OrderID: " + ID +
                ", clientID: " + clientID ;
    }

    public int getID() { return ID; }
    private void setID(int ID) {
        // ADVERTISEMENT; for security; this method shouldn't be called there; you should call the following setID method:
        print("ADVERTISEMENT; for security; this method shouldn't be called there; you should call the following setID method:");
    }
    ///< ADVERTISEMENT: Don't do more setters for the following atribute:
    public void setID(OrderProcessingSystem orderProcessingSystem) {
        ///< ADVERTISEMENT: Do not use the following method from class order on another part of the code:
        this.ID = orderProcessingSystem.assignOrderID();
    }
    public int getClientID() { return clientID; }
    public HashMap<Integer, Task> getTasks() { return tasks; }
    public Place getPlace() { return place; }
    public double getCost() { return fullPrice;}
    public void setCost(double fullPrice) {this.fullPrice = fullPrice;}
    public void addTask(Task task) { this.tasks.put(task.getTypeID(), task); }
    private void setOriginalID(OrderProcessingSystem orderProcessingSystem, Order originalOrder) {
        ///< ADVERTISEMENT: Do not use the following method from class order on another part of the code:
        this.ID = originalOrder.getID();
    }
    public Order getOrderWithIndividualTask(Task task, OrderProcessingSystem orderProcessingSystem) {
        Order order = new Order(this.clientID, new Task[]{ task }, this.place);
        order.setOriginalID(orderProcessingSystem, this);
        return order;
    }

    public boolean isSimple(int lastCleanByEnterpriseDate) {
        if (lastCleanByEnterpriseDate == Place.NEVER_CLEANED) { return false; }
        return (
                Time.getCurrentDate() - lastCleanByEnterpriseDate < 16
            || !this.place.getSurfaceResidueTypes().contains(Residue.MUD)
            || (this.place.getNumberOfPetsLiving() < 2)
        );
    }
}