package Main.Order;
import java.util.ArrayList;

import Main.Client.Exceptions.OrderWithoutTasksException;
import Main.Order.Place.Place;
import Main.Order.Task.Task;


public class OrderBuilder implements Builder{
    private Task tasks[];
    private Place place;
    private int clientID;
    private Order result;

    public void reset(){
        result = new Order();
    }

    @Override
    public void setClientID(Integer clientID) {
        this.clientID=clientID;
    }
    @Override
    public void addTasks(ArrayList<Task> arrayList) throws OrderWithoutTasksException{
        if (arrayList.size()>0){
            this.tasks= new Task[arrayList.size()];
            int i=0;
            for (Task t:arrayList) {
                this.tasks[i]=t;
                i++;
            }
        }
        else{
            throw new OrderWithoutTasksException();
        }
    }

    @Override
    public void addPlace(Place place ) {
        this.place=place;
    }

    public void setResult(){
        this.result = new Order(this.clientID,this.tasks,this.place);
    }

    public Order getResult() {
            return this.result;
        }
}
