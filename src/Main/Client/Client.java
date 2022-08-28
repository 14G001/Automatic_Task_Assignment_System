package Main.Client;
import java.util.HashMap;
import java.util.List;
import java.util.Comparator;

import Main.Order.Order;
import Main.Client.Exceptions.NoAvailableLimitedOrdersException;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Order.Task.Task;
import Main.Report.Affordable;


public abstract class Client implements Affordable {
    public static final int NOT_ASSIGNED_ID = -1;

    private int ID; // <--- ONLY ClientsDB CLASS CAN DEFINE ITS VALUE
    private int numberOfLimitedOrdersDoneThisMonth;
    private HashMap<Integer, Order> ordersMade;

    public Client() {
        this.ID = NOT_ASSIGNED_ID;
        this.numberOfLimitedOrdersDoneThisMonth = 0;
        this.ordersMade = new HashMap<Integer, Order>();
    }

    @Override
    public String toString() {
        return "ClientID: " + ID;
    }

    public abstract boolean isMakingALimitedOrder(Order order);
    public abstract boolean isDebtor(double debt);

    public int getID() { return ID; }
    public void setID(int assignedID) {
        this.ID = assignedID;
    }
    public double getCost() {
        Double cost = 0.0;
        for (Order order : ordersMade.values()) {
            cost += order.getCost();
        }
        return cost;}
    public void addOrder(Order order){
        this.ordersMade.put(order.getID(), order);
    }
    public int getNumberOfLimitedOrdersDoneThisMonth() { return numberOfLimitedOrdersDoneThisMonth; }
    // Client types that dont override the following method can request any kind of order:
    public boolean canMakeAnOrderWithThatTasksTypes(HashMap<Integer, Task> tasks) {
        return true;
    }
    public TaskPerformer getTheMostAppropiateTaskPerformer(List<TaskPerformer> taskPerformersThatHaveTheFunctionalities) {
        return taskPerformersThatHaveTheFunctionalities.stream().min(Comparator.comparing(TaskPerformer::getPrice)).get();
    }
    public void increaseLimitedOrdersDoneThisMonth() {
        this.numberOfLimitedOrdersDoneThisMonth++;
    }
    public boolean haveLimitedOrdersAvailable() { return (this.numberOfLimitedOrdersDoneThisMonth < 3); }
    public void useALimitedOrder() throws NoAvailableLimitedOrdersException {
        if(this.haveLimitedOrdersAvailable()){
            this.increaseLimitedOrdersDoneThisMonth();
        }else{
            throw new NoAvailableLimitedOrdersException();
        }
    }
}
