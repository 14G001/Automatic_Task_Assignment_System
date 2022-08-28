package Main.Client;
import java.util.HashMap;

import Main.Order.Order;
import Main.Order.Task.Task;


public class EconomicClient extends Client {
    @Override
    public boolean canMakeAnOrderWithThatTasksTypes(HashMap<Integer, Task> tasks) {
        return !tasks.containsKey(Task.SORT);
    }
    public boolean isMakingALimitedOrder(Order order) {
        return (order.getTasks().containsKey(Task.CLEAN));
    }
    public boolean isDebtor(double debt) {
        return (debt > 0);
    }
}