package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer;
import Main.Order.Task.Repair.*;
import Main.Order.Task.Task;

public class RepairerElectricity extends Repairer {
    public RepairerElectricity(double salary) { super(salary); }
    @Override
    public Repair getRepairType() { return new RepairElectricity(Repair.NOT_ASSIGNED_COMPLEXITY); }
    @Override
    public boolean hasTheFunctionality(int taskTypeID) {
        return (taskTypeID == Task.REPAIR_ELECTRICITY);
    }
}
