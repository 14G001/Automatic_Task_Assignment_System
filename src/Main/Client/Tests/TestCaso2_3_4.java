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
import Main.Order.Task.Shine.*;


class TestCaso2_3_4 {

    ClassicClient cliente;
    Enterprise E;
    Order O;

    @BeforeEach
    void setUp() {
        cliente = new ClassicClient();
        E = new Enterprise(new Client[]{ cliente });
    }

    @Test
    void realizarLimpiezaYOrdenamiento(){
        O = new Order(this.cliente.getID(), new Task[]{ new Clean(), new Sort() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            E.processOrder(O); ///< @todo: n4hw3ll:realizar makeOrder con parametros
        } catch (Exception e) {}
        Assertions.assertTrue(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfu).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.P011H).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Ya).containsOrder(O.getID()));
        Assertions.assertTrue(E.getTaskPerformerAssignmentSystem().get(Robot.S031RTY).containsOrder(O.getID()));
    }

    @Test
    void realizarLimpiezaYLustrado(){
        O = new Order(this.cliente.getID(), new Task[]{ new Clean(), new Shine(Surface.FLOOR) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            E.processOrder(O); ///< @todo: n4hw3ll:realizar makeOrder con parametros
        } catch (Exception e) {}
        Assertions.assertTrue(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Yfu).containsOrder(O.getID()));
        Assertions.assertTrue(E.getTaskPerformerAssignmentSystem().get(Robot.P011H).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.K311Ya).containsOrder(O.getID()));
        Assertions.assertFalse(E.getTaskPerformerAssignmentSystem().get(Robot.S031RTY).containsOrder(O.getID()));
    }

    @Test
    void realizarPedidoConMora(){
        E.getPaymentModule().update(this.cliente.getID(),2000.0);
        O = new Order(this.cliente.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            E.processOrder(O); ///< @todo: n4hw3ll:realizar makeOrder con parametros
        } catch (Exception e) {}
        ///< @todo: Make processOrder method throw ClientsDebtIsTooHigh exception
        // assertThrows(ClientsDebtIsTooHigh.class, () -> S.processOrder(O));
    }
}