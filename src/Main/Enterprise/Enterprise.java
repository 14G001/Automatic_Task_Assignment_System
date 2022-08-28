package Main.Enterprise;
import java.util.HashMap;

import Main.Client.Client;
import Main.Enterprise.ClientDatabases.ClientsDB;
import Main.Enterprise.ClientDatabases.PaymentModuleTODOMockObject;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.*;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformerAssignmentSystem;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Report.Administration;




public class Enterprise {
    private ClientsDB clients;
    private HashMap<Integer, Client> clientsDB;
    private PaymentModuleTODOMockObject clientsDebt;
    private HashMap<Integer, Double> clientsDebtDB;
    private TaskPerformerAssignmentSystem taskPerformers;
    private HashMap<Integer, TaskPerformer> taskPerformersDB;
    private OrderProcessingSystem orderProcessingSystem;
    private Administration administrator;
    private HashMap<String, Integer> lastCleanDates; // Key: place direction - Values: int (YYYYMMDD)


    private void initClients(Client clients[]) {
        this.clientsDebtDB = new HashMap<Integer, Double>();
        this.clientsDebt = new PaymentModuleTODOMockObject(this.clientsDebtDB);
        this.clientsDB = new HashMap<Integer, Client>();
        this.clients = new ClientsDB(this.clientsDB, clients, this.clientsDebt);
    }

    public Enterprise(Client clients[]) {
        this.initClients(clients);
        this.taskPerformersDB = new HashMap<Integer, TaskPerformer>();

        this.taskPerformers = new TaskPerformerAssignmentSystem(
                this.taskPerformersDB,
                new Robot[] {
                        new RobotK311Yfl(),
                        new RobotK311Yfu(),
                        new RobotP011H(),
                        new RobotK311Ya(),
                        new RobotS031RTY()
                }
        );
        this.orderProcessingSystem = new OrderProcessingSystem();
        this.administrator = new Administration(this.clientsDB);
        this.lastCleanDates = new HashMap<String, Integer>();
    }


    // For tests:
    public ClientsDB getClients() {
        return clients;
    }
    public HashMap<Integer, Client> getClientsDB() { return clientsDB; }
    public PaymentModuleTODOMockObject getPaymentModule() {
        return clientsDebt;
    }
    public TaskPerformerAssignmentSystem getTaskPerformerAssignmentSystem() { return taskPerformers; }
    public OrderProcessingSystem getOrderProcessingSystem() { return orderProcessingSystem; }
    // end For tests


    public void processOrder(Order order) throws Exception {
        double ordersPrice;
        Client client = this.clients.get(order.getClientID());

        try {
            this.orderProcessingSystem.validateOrder(order, client, this.clientsDebt.get(client.getID()), this.clientsDB);
            client.addOrder(order);
            this.lastCleanDates.put(order.getPlace().getDirection(), Place.NEVER_CLEANED);
            order.setID(orderProcessingSystem); // <--- ADVERTISEMENT: Do not use this method on another part of the code
            ordersPrice = this.taskPerformers.assignTaskPerformersForTheOrder(this.orderProcessingSystem, order, client);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }

        if (client.isMakingALimitedOrder(order)) {
            client.increaseLimitedOrdersDoneThisMonth();
        }

        this.clientsDebt.update(client.getID(), (this.clientsDebt.get(client.getID()) + ordersPrice));
        order.setCost(ordersPrice);
        administrator.updateCleaningsCounters(order, this.lastCleanDates.get(order.getPlace().getDirection()));
    }

}
