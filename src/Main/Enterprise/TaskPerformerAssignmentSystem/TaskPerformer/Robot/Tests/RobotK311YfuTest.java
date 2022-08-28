package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.*;
import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.Shine.*;
import Main.Order.Task.*;


class RobotK311YfuTest {
    Enterprise enterprise;
    RobotK311Yfu robot;
    Order order;
    Client client;

    @BeforeEach
    void setUp(){
        enterprise = new Enterprise(new Client[] {
                new EconomicClient(),
                new ClassicClient(),
                new PlatinumClient(),
        });
        robot = (RobotK311Yfu) enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Yfu);
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
        order = new Order(client.getID(),new Task[]{ new Clean(), new Shine(Surface.FURNITURE) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            enterprise.processOrder(order);
        } catch (Exception e) {}
        assertEquals(1, robot.getNumberOfPendingOrders());
    }

    @Test
    void getPricePerHourTest(){
        assertEquals(500, robot.getPrice());
    }
}