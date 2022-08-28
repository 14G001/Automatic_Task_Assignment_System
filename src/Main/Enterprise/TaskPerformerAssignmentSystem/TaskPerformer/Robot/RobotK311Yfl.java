package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import Main.Order.Task.Shine.Surface;
import Main.Order.Task.Task;

public class RobotK311Yfl extends Robot {
    public RobotK311Yfl() {
        super(
                1000,
                new int[]{
                        Task.CLEAN
                },
                new int[]{
                        Surface.FLOOR
                }
        );
    }
}
