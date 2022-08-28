package Main.Report;
import java.util.HashMap;

import Main.Client.Client;
import Main.Order.Order;
import Main.Order.Task.Task;


public class Administration<DBRegType> {
    private int simpleCleaning;
    private int complexCleaning;
    private  HashMap<Integer, Client> clients;
    public Administration(HashMap<Integer, Client> clients){
        this.simpleCleaning = 0;
        this.complexCleaning = 0;
        this.clients = clients;
    }

    public Report makeReport(Integer clientID) {
        Double cost;
        Client client = this.clients.get(clientID);
        cost = client.getCost();

        return new Report(client.toString(), cost);
    }

    public Report makeGeneralReport() {
        Double generalCost = 0.0;
        for(Client value: clients.values()){
            generalCost+= value.getCost();
        }

        return new Report(null, generalCost);
    }

    public Double getCost(Affordable a) {
        return a.getCost();
    }

    public void getSimpleCleaningsVSComplexCleanings(){
        System.out.println(
                "Simple cleanings: "+this.simpleCleaning +"\n"+
                "Complex cleanigs: "+this.complexCleaning +"\n");
    };

    public void updateCleaningsCounters(Order order, int lastCleanDate){
        if (order.getTasks().containsKey(Task.CLEAN)) {
            if(order.isSimple(lastCleanDate)){
                this.simpleCleaning++;
            } else {
                this.complexCleaning++;
            }
        }

    }

}
