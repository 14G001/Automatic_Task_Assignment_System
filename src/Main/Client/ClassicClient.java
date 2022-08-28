package Main.Client;
import Main.Order.Order;
import Main.Order.Task.Task;


public class ClassicClient extends Client {
    public boolean isMakingALimitedOrder(Order order) {
        return (order.getTasks().containsKey(Task.SORT));
    }
    public boolean isDebtor(double debt) {
        return (debt >= 2000);
    }
}
