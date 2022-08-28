package Main.Client;
import java.util.Comparator;
import java.util.List;

import Main.Order.Order;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;


public class PlatinumClient extends Client {
    ///< The statement doesn't explain where you have to get the monthly fee from so we have a local atribute in PlatinumClient:
    private double monthlyFee;

    public PlatinumClient() {
        super();
        this.monthlyFee = 0;
    }

    @Override
    public boolean isDebtor(double debt) {
        ///< @todo: check how to get monthly fee
        double monthlyFee = 999999;
        return (debt >= monthlyFee);
    }
    @Override
    public boolean isMakingALimitedOrder(Order order) {
        return false; // they can order whatever they want at least the debt wasn't enough low
    }
    @Override
    public TaskPerformer getTheMostAppropiateTaskPerformer(List<TaskPerformer> robotsThatHaveTheFunctionalities) {
        return robotsThatHaveTheFunctionalities.stream().min(Comparator.comparing(TaskPerformer::getNumberOfPendingOrders)).get();
    }
}
