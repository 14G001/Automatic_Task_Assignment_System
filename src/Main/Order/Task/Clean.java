package Main.Order.Task;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Order.Task.Shine.Surface;


public class Clean extends Task {
   public int getTypeID() { return Task.CLEAN; }

   // Asumiendo que al ser limpieza el robot debera poder trabajar con el tipo de superficie piso (tambien teniendo en cuenta el caso de prueba 3 del enunciado, que si no significa esto, significa que hay que asignar si o si mas de un robot en caso de que el pedido tenga mas de 2 tipos de tarea distintos.
   @Override
   public boolean hasTaskPerformerTheFunctionality(TaskPerformer taskPerformer) {
      return (taskPerformer.hasTheFunctionality(this.getTypeID()) && taskPerformer.getAvailableSurfaces().contains(Surface.FLOOR));
   }
}
