package Main.Report;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.*;
import Main.Enterprise.Enterprise;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.*;


class ReportTest {
    Report r;
    Report r2;
    Report r3;
    Client economicClient, classicClient, platinumClient;
    Enterprise enterprise;
    Order order;

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

        try {
            // Se asigna el K311Yfl Y NO EL K311Yfu PORQUE EL K311Yfu NO TIENE LA SUPERFICIE DE PISO A PESAR DE QUE ES MAS BARATO.
            order = new Order(this.classicClient.getID(), new Task[]{new Clean()}, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0));

            this.enterprise.processOrder(order);
        } catch (Exception e) {}

        this.enterprise.getPaymentModule().update(this.economicClient.getID(), 0.0);
        this.enterprise.getPaymentModule().update(this.classicClient.getID(), 0.0);
        r = new Report(null, 10500.0);
        r2 = new Report(platinumClient.toString(), 500.0);
        r3 = new Report(order.toString(), order.getCost());
    }

    @Test
    void makingAGeneralReportTest() {
        assertEquals("General Report\ntotalCost = 10500.0\n", r.toString());
    }
    @Test
    void makingAClientReportReturnFalseTest() {
        assertNotEquals("General Report\ntotalCost = 10500.0\n", r2.toString());
    }
    @Test
    void makingAClientReportReturnTrueTest() {
        assertEquals("ClientID: 2, totalCost = 500.0\n", r2.toString());
    }
    @Test
    void makingAnOrderReportReturnTrueTest() {
        assertEquals("OrderID: 0, clientID: 1, totalCost = 3000.0\n", r3.toString());
    }

}