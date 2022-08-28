package Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Repairer.*;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.TaskPerformer;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.Repair.*;
import Main.Order.Task.Task;



public class RepairerTest {
    Client economicClient, classicClient, platinumClient;
    Enterprise enterprise;

    @BeforeEach
    void setUp() {
        this.economicClient = new EconomicClient();
        this.classicClient = new ClassicClient();
        this.platinumClient = new PlatinumClient();

        this.enterprise = new Enterprise(
                new Client[]{                   // THE FOLLOWING IDS WILL BE ASSIGNED BY SYSTEM CLASS CONSTRUCTOR:
                        this.economicClient,    // <--- Assigned ID = 0
                        this.classicClient,     // <--- Assigned ID = 1
                        this.platinumClient     // <--- Assigned ID = 2
                }
        );
        this.enterprise.getTaskPerformerAssignmentSystem().add(
                new TaskPerformer[]{
                        // primeros 5 robots                    ID: 0 - 4
                        new RepairerElectricity(30000), // ID: 5
                        new RepairerGas(25000),         // ID: 6
                        new RepairerGas(40000),         // ID: 7
                        new RepairerElectricity(20000), // ID: 8
                        new RepairerElectricity(50000), // ID: 9
                        new RepairerGas(30000),         // ID: 10
                        new RepairerElectricity(45000)  // ID: 11
                }
        );
    }

    @Test
    void MakeElectricityRepairerRecieveOrder() {
        Order order = new Order(this.classicClient.getID(), new Task[]{ new RepairElectricity(3) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try { this.enterprise.processOrder(order); } catch (Exception e) {}
        // El pedido se le debe asignara este realizador de tareas porque es el mas barato y a los clientes classic y economic se les asigna el realizador de tareas mas barato de los que puedan cumplir con la misma:
        assertTrue(this.enterprise.getTaskPerformerAssignmentSystem().get(8).containsOrder(order.getID()));
        // Tiene que dar 4948 que equivale a: 4573 + (20000 / 160) * 3
        assertEquals(4948, order.getCost());
    }

    @Test
    void MakeGasRepairerRecieveOrder() {
        Order order = new Order(this.classicClient.getID(), new Task[]{ new RepairGas(3) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try { this.enterprise.processOrder(order); } catch (Exception e) {}
        // El pedido se le debe asignara este realizador de tareas porque es el mas barato y a los clientes classic y economic se les asigna el realizador de tareas mas barato de los que puedan cumplir con la misma:
        assertTrue(this.enterprise.getTaskPerformerAssignmentSystem().get(6).containsOrder(order.getID()));
        // Tiene que dar 3998.75 que equivale a: 3530 + (25000 / 160) * 3
        assertEquals(3998.75, order.getCost());
    }
}
