package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer;
import java.util.HashSet;

import Main.Order.Order;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Order.Task.Repair.Repair;


public abstract class Repairer extends TaskPerformer {
    public double salary;

    public Repairer(double salary) { this.salary = salary; }

    public abstract Repair getRepairType();

    public double getSalary() { return salary; }
    @Override
    public HashSet<Integer> getAvailableSurfaces() { return null; }
    @Override
    public double getPrice() { return this.getSalary(); }
    public double getOrderPrice(Order order) {
        Repair task = (Repair) order.getTasks().get(this.getRepairType().getTypeID());
        return task.getPrice() + (this.salary / 160) * task.getComplexity();
    }
}
