package Main.Order.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;

public class Sort extends Task {
    public int getTypeID() { return Task.SORT; }

    public boolean hasTaskPerformerTheFunctionality(TaskPerformer taskPerformer) {
        return taskPerformer.hasTheFunctionality(this.getTypeID());
    }
}
