package Main.Client.Strategy;
import java.util.Comparator;
import java.util.List;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;

public class EconomicStrategy implements Strategy{
    public TaskPerformer getTheMostAppropiateTaskPerformer(List<TaskPerformer> taskPerformersThatHaveTheFunctionalities){
        return taskPerformersThatHaveTheFunctionalities.stream().min(Comparator.comparing(TaskPerformer::getPrice)).get();
    }
}
