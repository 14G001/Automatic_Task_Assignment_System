package Main.Client.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.ClassicClient;
import Main.Client.Exceptions.NoAvailableLimitedOrdersException;


class ClassicClientTest {
    ClassicClient cliente;
    @BeforeEach
    void setUp() {
        cliente = new ClassicClient();
    }

    @Test
    void getNumberOfLimitedOrdersDoneThisMonthTest() {
        assertEquals(0, cliente.getNumberOfLimitedOrdersDoneThisMonth());
    }

    @Test
    void useALimitedOrderTest() {
        try {
            cliente.useALimitedOrder();
            cliente.useALimitedOrder();
            cliente.useALimitedOrder();

        }
        catch (NoAvailableLimitedOrdersException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // do stuff...
        }
        assertEquals( 3, cliente.getNumberOfLimitedOrdersDoneThisMonth());
    }

    @Test
    void quedanOrdenamientosRestantesTest() {
        try{
            cliente.useALimitedOrder();
            cliente.useALimitedOrder();
        }catch (NoAvailableLimitedOrdersException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // do stuff...
        }

        assertDoesNotThrow(() -> cliente.useALimitedOrder());
    }

    @Test
    void noQuedanOrdenamientosRestantesTest() {
        try{
            cliente.useALimitedOrder();
            cliente.useALimitedOrder();
            cliente.useALimitedOrder();
        }catch (NoAvailableLimitedOrdersException e) {
            e.printStackTrace();
        }
        catch (Exception e) {
            // do stuff...
        }

        assertThrows(NoAvailableLimitedOrdersException.class,() -> cliente.useALimitedOrder());
    }

    @Test
    void puedePedirLimpiezasTest(){
        assertDoesNotThrow(() -> cliente.haveLimitedOrdersAvailable());

    }

}