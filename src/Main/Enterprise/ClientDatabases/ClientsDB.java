package Main.Enterprise.ClientDatabases;
import java.util.HashMap;
import Utilities.RAMDataBase;

import Main.Client.Client;


public class ClientsDB extends RAMDataBase<Client> {
    private PaymentModuleTODOMockObject observerPaymentModule;
    public ClientsDB(HashMap<Integer, Client> clientsDB, Client[] clients, PaymentModuleTODOMockObject observerPaymentModule) {
        super(clientsDB);
        this.observerPaymentModule = observerPaymentModule;
        for (Client client:clients) {
            int assignedID = this.add(client);
            client.setID(assignedID);
        }
    }
    @Override
    public int add(Client client) {
        this.observerPaymentModule.add(0.0);
        int assignedID = super.add(client);
        client.setID(assignedID);
        return assignedID;
    }
}
