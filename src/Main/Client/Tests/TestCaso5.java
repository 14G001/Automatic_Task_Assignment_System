package Main.Client.Tests;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Robot;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.*;




class TestCaso5 {

    PlatinumClient cliente;

    @BeforeEach
    void setUp() {
        cliente = new PlatinumClient();
    }

    @Test
    void asignarRobotK3LLYa(){
        Enterprise E = new Enterprise(new Client[]{ cliente });
        Order O = new Order(this.cliente.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            E.processOrder(O); ///< @todo: n4hw3ll:realizar makeOrder con parametros
        } catch (Exception e) {}
        Assertions.assertTrue(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfu).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.P011H).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Ya).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.S031RTY).containsOrder(O.getID()));
    }
}