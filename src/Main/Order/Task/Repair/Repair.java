package Main.Order.Task.Repair;
import Main.Order.Task.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;

public abstract class Repair extends Task {
    public static final int NOT_ASSIGNED_COMPLEXITY = -1;

    private int complexity;
    private RepairPrices prices;

    public Repair(int complexity, RepairPrices prices) {
        this.complexity = complexity;
        this.prices = prices;
    }

    public int getComplexity() { return complexity; }

    public double getPrice() {
        if (this.complexity < 3) {
            return this.prices.getEasyTaskPrice();
        } else if (this.complexity < 7) {
            return this.prices.getMediumTaskPrice();
        } // else:
        return this.prices.getComplexTaskPrice();
    }

    @Override
    public boolean hasTaskPerformerTheFunctionality(TaskPerformer taskPerformer) {
        return (taskPerformer.hasTheFunctionality(this.getTypeID()));
    }
    @Override
    public boolean hasToShowPriceToClient() {
        return true;
    }
}
