package Main.Client.Tests;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import Main.Client.*;
import Main.Order.Order;
import Main.Order.Place.Place;
import Main.Order.Task.*;
import Main.Enterprise.Enterprise;


class TestCaso1 {

    ClassicClient cliente;
    Enterprise E;

    @BeforeEach
    void setUp() {
        cliente = new ClassicClient();
        E = new Enterprise(new Client[]{ cliente });
    }

    @Test
    void realizarPedidoConOrdenamiento(){
        try {
            E.processOrder(new Order(this.cliente.getID(), new Task[]{ new Clean() }, new Place("Presidente Lemmy Kilmister 1980", new int[]{}, 0)));
        } catch (Exception e) {}
    }
}