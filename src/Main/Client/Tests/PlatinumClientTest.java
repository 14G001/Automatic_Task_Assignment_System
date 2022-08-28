package Main.Client.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.PlatinumClient;


class PlatinumClientTest {
    PlatinumClient cliente;
    @BeforeEach
    void setUp() {
        cliente = new PlatinumClient();
    }

    @Test
    void puedePedirOrdenamientoTest(){
        assertDoesNotThrow(()-> cliente.haveLimitedOrdersAvailable() );
    }

    @Test
    void puedePedirLimpiezaTest(){
        assertDoesNotThrow(()-> cliente.haveLimitedOrdersAvailable());
    }
}