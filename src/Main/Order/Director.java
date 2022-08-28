package Main.Order;
import java.util.ArrayList;

import Main.Client.Exceptions.OrderWithoutTasksException;
import Main.Form.Form;
import Main.Order.Place.Place;
import Main.Order.Task.Repair.*;
import Main.Order.Task.Shine.Shine;
import Main.Order.Task.*;


public class Director {
    Builder builder;

    public Director(Builder builder){
        this.builder=builder;
    }

    public void make(Form form){
        ArrayList<Task> array2 = new ArrayList<>();
        int count=0;
        Task[] array = new Task[5];
        builder.setClientID(form.getClientID());
        Place p = new Place(form.getPlace(), new int[]{ form.getResidue() } , form.getPets());
        builder.addPlace(p);
        if(form.isClean()){
            array[count]=new Clean();
            count++;
        }
        if(form.isSort()){
            Sort a = new Sort();
            array2.add(a);
        }
        if(form.isShine()){
            Shine a = new Shine(0);
            if(form.isFloor()){
                a.setSurfaceTypeID(0);
            }
            if(form.isFurniture()){
                a.setSurfaceTypeID(1);
            }
            array2.add(a);
        }
        if(form.isRepair()){
            Task e = null;
            if(form.isElectrical()){
                e = new RepairElectricity(form.getElectricalComplexity());
            }
            if(form.isGas()){
                e = new RepairGas(form.getGasComplexity());
            }
            array2.add(e);
        }
        try {
            builder.addTasks(array2);
        }
        catch(OrderWithoutTasksException e){
            e.printStackTrace();
        }
    }
}
