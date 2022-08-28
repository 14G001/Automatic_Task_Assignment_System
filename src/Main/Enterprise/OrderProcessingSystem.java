package Main.Enterprise;
import java.util.HashMap;

import Main.Client.Client;
import Main.Order.Order;
import Main.Client.Exceptions.*;


public class OrderProcessingSystem {
    private int nextOrderIDToAssign; ///< ADVERTISEMENT: Do not use this atribute out of the method assignOrderID

    public OrderProcessingSystem() {
        this.nextOrderIDToAssign = 0;
    }

    ///< ADVERTISEMENT: Do not use the following method out of the method setOrder
    public int assignOrderID() {
        this.nextOrderIDToAssign++;
        return this.nextOrderIDToAssign - 1;
    }

    public void validateOrder(Order order, Client client, double clientDebt, HashMap<Integer, Client> clientsDB) throws UnregisteredClient, ClientCantDoThatRequestByTaskTypeRequested, ClientsDebtIsTooHigh, NoAvailableLimitedOrdersException {
        if (client == null) {
            throw new UnregisteredClient();
        }
        if (!clientsDB.containsKey(client.getID())){
            throw new UnregisteredClient();
        }
        if (!client.canMakeAnOrderWithThatTasksTypes(order.getTasks())) {
            throw new ClientCantDoThatRequestByTaskTypeRequested();
        }
        if (client.isDebtor(clientDebt)) {
            throw new ClientsDebtIsTooHigh();
        }
        if (client.isMakingALimitedOrder(order) && !client.haveLimitedOrdersAvailable()) {
            throw new NoAvailableLimitedOrdersException();
        }
        // If gets here; order was approved
    }

}
