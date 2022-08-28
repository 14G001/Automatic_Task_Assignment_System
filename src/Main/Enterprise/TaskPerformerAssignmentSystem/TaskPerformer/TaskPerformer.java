package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer;
import java.util.HashMap;
import java.util.HashSet;
import Utilities.DEVELOPMENT_ERROR;

import Main.Enterprise.OrderProcessingSystem;
import Main.Order.Order;
import Main.Order.Task.Task;


public abstract class TaskPerformer {
    private int ID;
    private HashMap<Integer, Order> pendingOrders; // Key: order ID

    public TaskPerformer() {
        this.pendingOrders = new HashMap<Integer, Order>(); // Key: order ID
    }

    public abstract boolean hasTheFunctionality(int taskTypeID);
    public abstract HashSet<Integer> getAvailableSurfaces();
    public abstract double getPrice();
    public abstract double getOrderPrice(Order order);

    public void setID(int ID) { this.ID = ID; }
    public int getID() { return this.ID; }
    public boolean containsOrder(int originalOrderID) {
        return (this.pendingOrders.containsKey(originalOrderID));
    }

    public HashMap<Integer, Order> getPendingOrders() { return this.pendingOrders; }
    public int getNumberOfPendingOrders() { return this.pendingOrders.size(); }
    ///< The next method is just for tests:
    public void addOrder(OrderProcessingSystem orderProcessingSystem, Order order) throws DEVELOPMENT_ERROR {
        if (order.getID() == Order.NOT_ASSIGNED_ID) {
            throw new DEVELOPMENT_ERROR("El ID de orden debe asignarse por el Sistema usando el metodo assignOrderID.");
        }
        HashMap<Integer, Task> tasksToAdd = order.getTasks();
        for (Integer taskTypeID:tasksToAdd.keySet()) {
            if (this.pendingOrders.containsKey(order.getID())) { // Este robot tiene 2 o mas tipos de tareas por hacer de este mismo pedido realizado
                this.pendingOrders.get(order.getID()).addTask(tasksToAdd.get(taskTypeID));
            } else {
                this.pendingOrders.put(order.getID(), order.getOrderWithIndividualTask(tasksToAdd.get(taskTypeID), orderProcessingSystem));
            }
        }
    }
}
