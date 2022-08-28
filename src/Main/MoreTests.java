package Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.util.List;

import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer.*;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.*;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformerAssignmentSystem;
import Main.Order.Task.*;
import Main.Order.Task.Repair.*;
import Main.Order.Task.Shine.*;




public class MoreTests {
    Client economicClient, classicClient, platinumClient;
    Enterprise enterprise;

    int[] taskPerformersElectricity;
    int[] taskPerformersGas;

    @BeforeEach
    void setUp() {
        this.economicClient = new EconomicClient();
        this.classicClient = new ClassicClient();
        this.platinumClient = new PlatinumClient();

        this.enterprise = new Enterprise(
                new Client[]{                   // THE FOLLOWING IDS WILL BE ASSIGNED BY SYSTEM CLASS CONSTRUCTOR:
                        this.economicClient,    // <--- Assigned ID = 0
                        this.classicClient,     // <--- Assigned ID = 1
                        this.platinumClient     // <--- Assigned ID = 2
                }
        );



        this.enterprise.getTaskPerformerAssignmentSystem().add(
                new TaskPerformer[]{
                        /* new RobotK311Yfl(),
                        new RobotK311Yfu(),
                        new RobotP011H(),
                        new RobotK311Ya(),
                        new RobotS031RTY() */
                        new RepairerElectricity(30000), // ID:  5
                        new RepairerGas(25000),         // ID:  6
                        new RepairerGas(40000),         // ID:  7
                        new RepairerElectricity(20000), // ID:  8
                        new RepairerElectricity(50000), // ID:  9
                        new RepairerGas(30000),         // ID: 10
                        new RepairerElectricity(45000)  // ID: 11
                }
        );

        this.taskPerformersElectricity = new int[]{ 5, 8, 9, 11 };
        this.taskPerformersGas = new int[]{ 6, 7, 10 };
    }

    private static boolean isTaskPerformerInArray(int taskPerformerID, int[] taskPerformerArray) {
        for (int tpID:taskPerformerArray) {
            if (taskPerformerID == tpID) {
                return true;
            }
        }
        return false;
    }
    private static boolean isTaskPerformerInList(int taskPerformerID, List<TaskPerformer> taskPerformerArray) {
        for (TaskPerformer tp:taskPerformerArray) {
            if (taskPerformerID == tp.getID()) {
                return true;
            }
        }
        return false;
    }

    private int testTPTHF(Task task, int[] taskPerformersThatShouldHaveFunctionalityIDs) {
        TaskPerformerAssignmentSystem TPAS = this.enterprise.getTaskPerformerAssignmentSystem();
        List<TaskPerformer> taskPerformersTharApparentlyHaveTheFunctionality = TPAS.getTaskPerformersThatHaveTheFunctionality(task);
        for (TaskPerformer tp : TPAS.getRecords()) {
            if (
                    (
                            (isTaskPerformerInList(tp.getID(), taskPerformersTharApparentlyHaveTheFunctionality) &&
                            isTaskPerformerInArray(tp.getID(), taskPerformersThatShouldHaveFunctionalityIDs))
                                                        ||
                            (!isTaskPerformerInList(tp.getID(), taskPerformersTharApparentlyHaveTheFunctionality) &&
                            !isTaskPerformerInArray(tp.getID(), taskPerformersThatShouldHaveFunctionalityIDs))
                    )
                                                     == false
            ) {
                return tp.getID();
            }
        }
        return -1;
    }

    @Test
    void TaskPerformersThatHaveTheFunctionalitiesTest() {
        TaskPerformerAssignmentSystem TPAS = this.enterprise.getTaskPerformerAssignmentSystem();
        assertEquals(-1, testTPTHF(new Clean(),                         new int[]{ Robot.K311Yfl, Robot.P011H, Robot.K311Ya }));
        assertEquals(-1, testTPTHF(new Shine(Surface.FLOOR),            new int[]{ Robot.P011H, Robot.K311Ya }));
        assertEquals(-1, testTPTHF(new Shine(Surface.FURNITURE),        new int[]{ Robot.K311Yfu, Robot.K311Ya }));
        assertEquals(-1, testTPTHF(new Sort(),                          new int[]{ Robot.K311Ya, Robot.S031RTY }));
        assertEquals(-1, testTPTHF(new RepairElectricity(4),   this.taskPerformersElectricity));
        assertEquals(-1, testTPTHF(new RepairGas(4),           this.taskPerformersGas));
    }
}
