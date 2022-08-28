package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer;
import Main.Order.Task.Repair.*;
import Main.Order.Task.Task;

public class RepairerGas extends Repairer {
    public RepairerGas(double salary) { super(salary); }
    @Override
    public Repair getRepairType() { return new RepairGas(Repair.NOT_ASSIGNED_COMPLEXITY); }
    @Override
    public boolean hasTheFunctionality(int taskTypeID) {
        return (taskTypeID == Task.REPAIR_GAS);
    }
}
