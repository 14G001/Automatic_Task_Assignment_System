package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import Main.Order.Task.Task;

public class RobotS031RTY extends Robot {
    public RobotS031RTY() {
        super(
                2700,
                new int[]{ // NOT_APPLICABLE == Sin limpieza ni lustre
                        Task.SORT
                },
                new int[]{} // <--- ESO SIGNIFICA NOT_APPLICABLE
        );
    }
}
