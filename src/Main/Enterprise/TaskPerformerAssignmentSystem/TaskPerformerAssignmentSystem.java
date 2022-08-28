package Main.Enterprise.TaskPerformerAssignmentSystem;
import java.util.HashMap;
import java.util.List;
import static java.util.stream.Collectors.toList;
import Utilities.RAMDataBase;

import Main.Enterprise.Exceptions.OrderRejectedByClientByItsPrice;
import Main.Client.Client;
import Main.Order.Order;
import Main.Enterprise.OrderProcessingSystem;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Order.Task.Task;



public class TaskPerformerAssignmentSystem extends RAMDataBase<TaskPerformer> {
    // private HashMap<Integer, TaskPerformer> taskPerformers; // Key: taskPerformerID (we know 0 - 4 including are robot by types).
    private HashMap<String, Integer> lastCleanDates; // Key: place direction - Value: last clean date by enterprise YYYYMMDD

    public TaskPerformerAssignmentSystem(HashMap<Integer, TaskPerformer> taskPerformersDB, TaskPerformer[] taskPerformers) {
        super(taskPerformersDB, taskPerformers);
        this.lastCleanDates = new HashMap<String, Integer>();
        // We know there is just one robot by model by the statement:
    }

    @Override
    public int add(TaskPerformer taskPerformer) {
        int assignedID = super.add(taskPerformer);
        taskPerformer.setID(assignedID);
        return assignedID;
    }

    // This should be private but will be public just for tests:
    public List<TaskPerformer> getTaskPerformersThatHaveTheFunctionality(Task task) {
        return super.getRecords().stream().filter(taskPerformer -> task.hasTaskPerformerTheFunctionality(taskPerformer)).collect(toList());
    }

    ///< Hardcoded:
    private boolean showPriceToClient(double taskPrice) {
        /** @brief: Here it sends task price to client transaction media and
         * gives selected option by client.
         * @return: true: if client accepted continuing making that task with that
         *          price or its type's price shouldn't be accepted by client first.
         *          false: otherwise.
         */
        ///< Hardcoded
        return true;
    }
    private boolean clientAcceptsConditions(Task task, double taskPrice) {
        if (!task.hasToShowPriceToClient()) { return true; }
        return (this.showPriceToClient(taskPrice));
    }

    public double assignTaskPerformersForTheOrder(OrderProcessingSystem orderProcessingSystem, Order order, Client client) throws OrderRejectedByClientByItsPrice {
        HashMap<Integer, Task> tasks = order.getTasks();
        double ordersPrice = 0;

        for (Integer taskTypeID : tasks.keySet()) {
            Task task = tasks.get(taskTypeID);
            List<TaskPerformer> taskPerformersThatHaveTheFunctionality = this.getTaskPerformersThatHaveTheFunctionality(task);
            TaskPerformer taskPerformerToAssign = client.getTheMostAppropiateTaskPerformer(taskPerformersThatHaveTheFunctionality);
            Order orderWithIndividualTask = order.getOrderWithIndividualTask(task, orderProcessingSystem); // This saves a copy of the order object without all tasks except for the current processing task object
            double singleOrderPrice = taskPerformerToAssign.getOrderPrice(orderWithIndividualTask);
            if (this.clientAcceptsConditions(task, singleOrderPrice)) {
                ordersPrice += singleOrderPrice;
                taskPerformerToAssign.addOrder(orderProcessingSystem, orderWithIndividualTask);
            } // else; order won't be made.

        }
        // If ordersPrice == 0 here, no task order will be done:
        // Client had rejected order by its price (or task requested isn't available but that shouldn't happen)
        if (ordersPrice == 0) { throw new OrderRejectedByClientByItsPrice(); }
        return ordersPrice;
    }
}
