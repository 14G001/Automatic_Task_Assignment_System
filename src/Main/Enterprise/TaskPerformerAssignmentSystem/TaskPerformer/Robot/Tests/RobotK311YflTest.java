package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Robot;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.RobotK311Yfl;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.*;


class RobotK311YflTest {
    Enterprise enterprise;
    RobotK311Yfl robot;
    Order order;
    Client client;

    @BeforeEach
    void setUp(){
        enterprise = new Enterprise(new Client[] {
                new EconomicClient(),
                new ClassicClient(),
                new PlatinumClient(),
        });
        robot = (RobotK311Yfl) enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl);
    }

    @Test
    void getAmountOfPendingOrdersTest() {
        assertEquals(0, robot.getNumberOfPendingOrders());
    }

    @Test
    void hasTheFunctionalitieTest(){
        assertTrue(robot.hasTheFunctionality(Task.CLEAN));
        assertFalse(robot.hasTheFunctionality(Task.SHINE));
        assertFalse(robot.hasTheFunctionality(Task.SORT));
    }

    @Test
    void addOrder(){
        client = enterprise.getClients().get(2);
        order = new Order(client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            enterprise.processOrder(order);
        } catch (Exception e) {}
        assertEquals(1, robot.getNumberOfPendingOrders());
    }

    @Test
    void getPricePerHourTest(){
        assertEquals(1000, robot.getPrice());
    }
}