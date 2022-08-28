package Main.Order.Task.Repair;
import Main.Order.Task.Task;

public class RepairGas extends Repair {
    public RepairGas(int complexity) {
        super(complexity, new RepairPrices(1000, 3530, 6389));
    }

    @Override
    public int getTypeID() { return Task.REPAIR_GAS; }
}
