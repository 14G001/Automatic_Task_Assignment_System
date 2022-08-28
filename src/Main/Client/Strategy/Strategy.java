package Main.Client.Strategy;
import java.util.List;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;

public interface Strategy {
    public TaskPerformer getTheMostAppropiateTaskPerformer(List<TaskPerformer> taskPerformersThatHaveTheFunctionalities);
}
