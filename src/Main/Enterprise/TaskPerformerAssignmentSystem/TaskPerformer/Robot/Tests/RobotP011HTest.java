package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.*;
import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.Shine.*;
import Main.Order.Task.*;


import static org.junit.jupiter.api.Assertions.*;

class RobotP011HTest {
    Enterprise enterprise;
    RobotP011H robot;
    Order order;
    Client client;

    @BeforeEach
    void setUp(){
        enterprise = new Enterprise(new Client[] {
                new EconomicClient(),
                new ClassicClient(),
                new PlatinumClient(),
        });
        robot = (RobotP011H) enterprise.getTaskPerformerAssignmentSystem().get(Robot.P011H);
    }

    @Test
    void getAmountOfPendingOrdersTest() {
        assertEquals(0, robot.getNumberOfPendingOrders());
    }

    @Test
    void hasTheFunctionalitieTest(){
        assertTrue(robot.hasTheFunctionality(Task.CLEAN));
        assertTrue(robot.hasTheFunctionality(Task.SHINE));
        assertFalse(robot.hasTheFunctionality(Task.SORT));
    }

    @Test
    void addOrder(){
        client = enterprise.getClients().get(2);
        order = new Order(client.getID(), new Task[]{ new Clean(), new Shine(Surface.FURNITURE) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            enterprise.processOrder(order); // <-- Se le asigna a otro robot que se encuentra menos ocupado
            enterprise.processOrder(order);
        } catch (Exception e) {}
        assertEquals(1, robot.getNumberOfPendingOrders());
    }

    @Test
    void getPricePerHourTest(){
        assertEquals(1500, robot.getPrice());
    }
}