package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import Main.Order.Task.Task;
import Main.Order.Task.Shine.Surface;

public class RobotK311Yfu extends Robot {
    public RobotK311Yfu() {
        super(
                500,
                new int[]{
                        Task.CLEAN,
                        Task.SHINE
                },
                new int[]{
                        Surface.FURNITURE
                }
        );
    }
}
