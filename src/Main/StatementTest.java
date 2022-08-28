package Main;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Enterprise.OrderProcessingSystem;
import Main.Client.*;
import Main.Enterprise.TaskPerformerAssignmentSystem.TaskPerformer.Robot.Robot;
import Main.Order.Order;
import Main.Order.Task.Shine.*;
import Main.Order.Task.*;
import Main.Order.Place.Place;
import Main.Client.Exceptions.*;
import Main.Enterprise.Enterprise;




public class StatementTest { // Statement == consigna/enunciado
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

        // Se agrega un pedido que se indicaba que tenia al principio mencionado en la consigna al robot K311Yfl.
        // El pedido esta disenado para ser asignado al robot mencionado:
        try {
            // Se asigna el K311Yfl Y NO EL K311Yfu PORQUE EL K311Yfu NO TIENE LA SUPERFICIE DE PISO A PESAR DE QUE ES MAS BARATO.
            this.enterprise.processOrder(
                    new Order(this.classicClient.getID(), new Task[]{new Clean()}, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0))
            );
        } catch (Exception e) {}

        this.enterprise.getPaymentModule().update(this.economicClient.getID(), 0.0);
        this.enterprise.getPaymentModule().update(this.classicClient.getID(), 0.0);
    }

    // First statement test
    @Test
    void economicClientRequestACleanAndSortOrder() {
        OrderProcessingSystem orderProcessingSystem = this.enterprise.getOrderProcessingSystem();
        assertThrows(ClientCantDoThatRequestByTaskTypeRequested.class, () ->
                orderProcessingSystem.validateOrder(
                        new Order(
                                this.economicClient.getID(),
                                new Task[]{
                                        new Clean(),
                                        new Sort()
                                },
                                new Place(
                                        "Presidente Lemmy Kilmister 1980",
                                        new int[]{},
                                        0)
                        ),
                        this.economicClient,
                        0.0,this.enterprise.getClientsDB())
        );
    }

    // Second statement test
    @Test
    void AClassicClientWithoutDebtMakesACleanAndSortOrder() {
        Order order = new Order(this.classicClient.getID(), new Task[]{ new Clean(), new Sort() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try { this.enterprise.processOrder(order); } catch (Exception e) { assertEquals("Si se ejecuto esto", "Es porque se tiro una excepcion en una parte en la que no debia"); }
        assertTrue(this.enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl).containsOrder(order.getID()));
        assertTrue(this.enterprise.getTaskPerformerAssignmentSystem().get(Robot.S031RTY).containsOrder(order.getID()));
    }

    // Third statement test
    @Test
    void ClassicClientRequestsACleanAndFurnitureShineOrder() {
        Order order = new Order(this.classicClient.getID(), new Task[]{ new Clean(), new Shine(Surface.FURNITURE) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try { this.enterprise.processOrder(order); } catch (Exception e) { assertEquals("Si se ejecuto esto", "Es porque se tiro una excepcion en una parte en la que no debia"); }
        assertTrue(enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Yfl).containsOrder(order.getID()));
        assertTrue(enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Yfu).containsOrder(order.getID()));
    }

    // Fourth statement test
    @Test
    void ClassicDebtorClientMakesAnOrder() {
        this.enterprise.getPaymentModule().update(this.classicClient.getID(), 2000.1);
        OrderProcessingSystem orderProcessingSystem = this.enterprise.getOrderProcessingSystem();
        assertThrows(ClientsDebtIsTooHigh.class, () ->
                orderProcessingSystem.validateOrder( // Al realizar el inputOrder, dentro de si, al entrar a assignRobots, se setea el ID de Order POR SEGURIDAD, por eso es importante guardarlo en el objeto order.
                        new Order(this.classicClient.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0)),
                        this.classicClient,
                        this.enterprise.getPaymentModule().get(this.classicClient.getID()),this.enterprise.getClientsDB())
                );
    }

    // Fifth statement test
    @Test
    void PlatinumClientMakesAnOrderAndK311YaRobotIsAssigned() {
        // Inicializo el siguiente pedido para que al pasarlo por el siguiente procesamiento de orden se le asigne el ID.
        Order order = new Order(this.platinumClient.getID(), new Task[]{ new Sort() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));
        try {
            this.enterprise.getPaymentModule().update(this.classicClient.getID(), 0.0);
            // Se hace que se asignen tareas a los robots K311Yfl y S031RTY de la misma forma que en el segundo caso de prueba:
            this.enterprise.processOrder(
                    new Order(this.classicClient.getID(), new Task[]{ new Clean(), new Sort() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0))
            );
            this.enterprise.getPaymentModule().update(this.classicClient.getID(), 0.0);
            // Se hace que se asigne una tarea al robot K311fu:
            this.enterprise.processOrder(
                    new Order(this.classicClient.getID(), new Task[]{ new Shine(Surface.FURNITURE) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0))
            );
            this.enterprise.getPaymentModule().update(this.classicClient.getID(), 0.0);
            // Se hace que se asigne una tarea al robot P011H:
            this.enterprise.processOrder(
                    new Order(this.classicClient.getID(), new Task[]{ new Shine(Surface.FLOOR) }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0))
            );
            // Se hace que se asigne una tarea al robot P011H y se guarda el pedido:
            this.enterprise.processOrder(order);
        } catch (Exception e) { assertEquals("Si se ejecuto esto", "Es porque se tiro una excepcion en una parte en la que no debia"); }

        // Preguntamos si el cliente tiene el pedido con el ID mencionado (Order order declarado arriba).
        assertTrue(enterprise.getTaskPerformerAssignmentSystem().get(Robot.K311Ya).containsOrder(order.getID()));
    }

}