package Main.Order.Task.Shine;
import Main.Order.Task.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;


public class Shine extends Task { // Segun los tests descritos en el enunciado, el tipo de superficie aplica unicamente a los lustres.
    int surfaceTypeID;

    public Shine(int surfaceTypeID) { this.surfaceTypeID = surfaceTypeID; }

    public int getSurfaceTypeID() { return surfaceTypeID; }
    public int getTypeID() { return Task.SHINE; }

    public void setSurfaceTypeID(int surfaceTypeID) {
        this.surfaceTypeID = surfaceTypeID;
    }

    @Override
    public boolean hasTaskPerformerTheFunctionality(TaskPerformer taskPerformer) {
        return (taskPerformer.hasTheFunctionality(this.getTypeID()) && taskPerformer.getAvailableSurfaces().contains(this.surfaceTypeID));
    }
}
