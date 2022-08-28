package Main.Order;
import java.util.ArrayList;

import Main.Client.Exceptions.OrderWithoutTasksException;
import Main.Order.Place.Place;
import Main.Order.Task.Task;


public interface Builder {
    public void setClientID(Integer clientID);
    public void addTasks(ArrayList<Task> arrayList) throws OrderWithoutTasksException;
    public void addPlace(Place place);
}
