package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import Main.Order.Task.Task;
import Main.Order.Task.Shine.Surface;

public class RobotP011H extends Robot {
    public RobotP011H() {
        super(
                1500,
                new int[]{
                        Task.CLEAN,
                        Task.SHINE
                },
                new int[]{
                        Surface.FLOOR
                }
        );
    }
}
