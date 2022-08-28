package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot;
import java.util.HashMap;
import java.util.HashSet;

import Main.Order.Order;
import Main.Order.Task.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;


public abstract class Robot extends TaskPerformer {
    // Robot type positions on TaskPerformers database by models:
    public static final int K311Yfl = 0;
    public static final int K311Yfu = 1;
    public static final int P011H = 2;
    public static final int K311Ya = 3;
    public static final int S031RTY = 4;

    // -- Atributes -- \\
    private double pricePerHour;
    private HashSet<Integer> functions;
    private HashSet<Integer> availableSurfaces; // null == NOT_APPLICABLE == (Sin limpieza ni lustre) | 0 == GROUND | 1 == FURNITURE


    public Robot(double pricePerHour, int[] availableTaskIDs, int availableSurfaceTypes[]) {
        super();
        this.pricePerHour = pricePerHour;
        this.functions = new HashSet<Integer>(); // Contains task type IDs
        for (int taskID:availableTaskIDs) { this.functions.add(taskID); }
        this.availableSurfaces = new HashSet<Integer>();
        for (int surfaceTypeID:availableSurfaceTypes) { this.availableSurfaces.add(surfaceTypeID); }
    }

    @Override
    public boolean hasTheFunctionality(int taskTypeID) {
        return this.functions.contains(taskTypeID);
    }
    @Override
    public double getPrice() { return pricePerHour; }
    @Override
    public HashSet<Integer> getAvailableSurfaces() { return availableSurfaces; }

    ///< Hardcoded:
    private double calculateNumberOfHours(HashMap<Integer, Task> tasks, String place) {
        /** @brief:
         * It would do something like:
         * calcularHorasXDimensionesLugar(direccionLugar, tareas);
         * @return: number of hours that tasks would take.
          */
        ///< Hardcoded
        return 3;
    }
    @Override
    public double getOrderPrice(Order order) {
        return ( this.pricePerHour * this.calculateNumberOfHours(order.getTasks(), order.getPlace().getDirection()) );
    }
}
