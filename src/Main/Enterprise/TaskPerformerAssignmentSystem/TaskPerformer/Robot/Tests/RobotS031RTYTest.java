package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.*;
import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.*;


import static org.junit.jupiter.api.Assertions.*;

class RobotS031RTYTest {
    Enterprise enterprise;
    RobotS031RTY robot;
    Order order;
    Client client;

    @BeforeEach
    void setUp(){
        enterprise = new Enterprise(new Client[] {
                new EconomicClient(),
                new ClassicClient(),
                new PlatinumClient(),
        });
        robot = (RobotS031RTY) enterprise.getTaskPerformerAssignmentSystem().get(Robot.S031RTY);
    }

    @Test
    void getAmountOfPendingOrdersTest() {
        assertEquals(0, robot.getNumberOfPendingOrders());
    }

    @Test
    void hasTheFunctionalitieTest(){
        assertFalse(robot.hasTheFunctionality(Task.CLEAN));
        assertFalse(robot.hasTheFunctionality(Task.SHINE));
        assertTrue(robot.hasTheFunctionality(Task.SORT));
    }

    @Test
    void addOrder(){
        client = enterprise.getClients().get(2);
        order = new Order(client.getID(), new Task[]{ new Sort() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            enterprise.processOrder(order); // <-- se asigna al K311Yfu
            enterprise.processOrder(order);
        } catch (Exception e) {}
        assertEquals(1, robot.getNumberOfPendingOrders());
    }

    @Test
    void getPricePerHourTest(){
        assertEquals(2700, robot.getPrice());
    }

}