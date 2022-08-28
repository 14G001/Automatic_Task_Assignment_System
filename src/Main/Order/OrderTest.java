package Main.Order;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Main.Client.*;
import Main.Order.Task.*;
import Main.Order.Place.Place;
import Main.Enterprise.Enterprise;
import Main.Client.Exceptions.NoAvailableLimitedOrdersException;


class OrderTest {

    Client client = new EconomicClient();
    Client client2 = new ClassicClient();
    Enterprise enterprise;

    @BeforeEach
    void setUp() {
        this.enterprise = new Enterprise(new Client[]{
                this.client,
                this.client2,
        });
    }

    @Test
    void validarUnClassicConOrdenamientosOK() throws NoAvailableLimitedOrdersException {
        try {
            enterprise.processOrder(new Order(this.client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", null, 0)));
        } catch (Exception e) {}
        ///< @todo: unhardcode method:
        // assertEquals(true, o.validate());
    }

    @Test
    void validarUnClassicSinOrdenamientos() throws NoAvailableLimitedOrdersException {
        try {
            enterprise.processOrder(new Order(this.client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", null, 0)));
        } catch (Exception e) {}
        ///< @todo: set the following on the constructor:
        // client.setRemainingMonthlyOrderings(0);
        ///< @todo: make validate method throw NoAvailableLimitedOrdersException
        // assertThrows(NoAvailableLimitedOrdersException.class,()-> o.validate());
    }

    @Test
    void validarUnEconomicPidiendoOrdenamiento() throws NoAvailableLimitedOrdersException {
        try {
            enterprise.processOrder(new Order(this.client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", null, 0)));
        } catch (Exception e) {}
        ///< @todo: make validate method throw NoAvailableLimitedOrdersException
        // assertThrows(NoAvailableLimitedOrdersException.class,()-> o.validate());
    }

    @Test
    void validarUnEconomicSinLimpiezas() throws NoAvailableLimitedOrdersException {
        try {
            enterprise.processOrder(new Order(this.client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", null, 0)));
        } catch (Exception e) {}
        ///< @todo: set the following on the constructor:
        // client2.setRemainingMonthlyCleanings(0);
        ///< @todo: make validate method throw NoAvailableLimitedOrdersException
        // assertThrows(NoAvailableLimitedOrdersException.class,()-> o.validate());
    }

    @Test
    void validarUnEconomicConLimpiezasOK() throws NoAvailableLimitedOrdersException {
        try {
            enterprise.processOrder(new Order(this.client.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", null, 0)));
        } catch (Exception e) {}
        ///< @todo: set the following on the constructor:
        // client2.setRemainingMonthlyCleanings(2);
        ///< @todo: unhardcode method:
        // assertEquals(true, o.validate());
    }
}