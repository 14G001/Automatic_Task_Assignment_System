package Main.Order.Task.Repair;
import Main.Order.Task.Task;

public class RepairElectricity extends Repair {
    public RepairElectricity(int complexity) {
        super(complexity, new RepairPrices(2000, 4573, 7359));
    }

    @Override
    public int getTypeID() { return Task.REPAIR_ELECTRICITY; }
}
