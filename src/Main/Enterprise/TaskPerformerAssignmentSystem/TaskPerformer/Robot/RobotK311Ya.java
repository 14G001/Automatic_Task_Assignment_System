package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import Main.Order.Task.Shine.Surface;
import Main.Order.Task.Task;

public class RobotK311Ya extends Robot {
    public RobotK311Ya() {
        super(
                5000,
                new int[]{
                        Task.CLEAN,
                        Task.SHINE,
                        Task.SORT
                },
                new int[]{
                        Surface.FLOOR,
                        Surface.FURNITURE
                }
        );
    }
}
