package Main.Order.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;


public abstract class Task {
    // typedef enum {
    public static final int CLEAN = 0;
    public static final int SHINE = 1;
    public static final int SORT = 2;
    public static final int REPAIR_ELECTRICITY = 3;
    public static final int REPAIR_GAS = 4;
    // } taskType;


    public Task() {
    }

    public abstract int getTypeID();
    public abstract boolean hasTaskPerformerTheFunctionality(TaskPerformer taskPerformer);
    // Task types that dont override the following method dont ask client his decision:
    public boolean hasToShowPriceToClient() {
        return false;
    }
}
