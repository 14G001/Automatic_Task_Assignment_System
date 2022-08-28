package Main.Client.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.Exceptions.NoAvailableLimitedOrdersException;
import Main.Client.EconomicClient;


class EconomicClientTest {

    EconomicClient cliente1;
    EconomicClient cliente2;
    @BeforeEach
    void setUp() {
        cliente1 = new EconomicClient();
        cliente2 = new EconomicClient();
    }

    @Test
    void getNumberOfLimitedOrdersDoneThisMonthTest() {
        assertEquals(0, cliente1.getNumberOfLimitedOrdersDoneThisMonth());
    }

    @Test
    void useALimitedOrderACliente1PeroACliente2NoTest() {
        try {
            cliente1.useALimitedOrder();
            cliente1.useALimitedOrder();
        }
        catch (NoAvailableLimitedOrdersException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // do stuff...
        }
        assertEquals(2, cliente1.getNumberOfLimitedOrdersDoneThisMonth());
        assertEquals(0, cliente2.getNumberOfLimitedOrdersDoneThisMonth());
    }

    @Test
    void noQuedanLimpiezasRestantesTest(){
        try {
            cliente1.useALimitedOrder();
            cliente1.useALimitedOrder();
            cliente1.useALimitedOrder();
        }
        catch (NoAvailableLimitedOrdersException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // do stuff...
        }

        assertThrows(NoAvailableLimitedOrdersException.class, () -> cliente1.useALimitedOrder());

    }



    @Test
    void quedanLimpiezasRestantesTest(){
        assertDoesNotThrow(() -> cliente1.useALimitedOrder());

    }

    @Test
    void economicClientNoPuedePedirOrdenamientosTest(){
        assertEquals(0, cliente1.getNumberOfLimitedOrdersDoneThisMonth());
    }

}