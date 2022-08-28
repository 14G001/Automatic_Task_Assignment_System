package Main.Order;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import Main.Client.Exceptions.OrderWithoutTasksException;
import Main.Form.Form;
import Main.Order.Place.Residue;


class DirectorTest {

    OrderBuilder builder = new OrderBuilder();
    Director director = new Director(builder);
    Form form = new Form(1234);

    @BeforeEach
    void setUp() {
        form.setPlace("Presidente Lemmy Kilmister 1980");
        form.setClean(true);
        form.setLastCleaningDate(20220623); // chequear si funciona así con AAAAMMDD
        form.setResidue(Residue.DUST);
        form.setPets(2);
        form.setSort(true);
        form.setShine(false);
        form.setFloor(false);
        form.setFurniture(true);
        form.setRepair(true);
        form.setElectrical(true);
        form.setElectricalComplexity(3);
        form.setGas(false);
    }

    @Test
    void orderAcleanWithDustWith2PetsWithSortFurnitureElectricalRepair(){
        director.make(form);
        builder.setResult();
        assertEquals(Order.class, builder.getResult().getClass() );
    }

    @Test
    void orderWithoutTasks(){
        form.setPlace("Presidente Lemmy Kilmister 1980");
        form.setClean(false);
        form.setLastCleaningDate(20220623); // chequear si funciona así con AAAAMMDD
        form.setResidue(Residue.DUST);
        form.setPets(2);
        form.setSort(false);
        form.setShine(false);
        form.setFloor(false);
        form.setFurniture(false);
        form.setRepair(false);
        form.setElectrical(false);
        form.setElectricalComplexity(0);
        form.setGas(false);
        director.make(form);
        builder.setResult();
        assertThrows(OrderWithoutTasksException.class, () -> director.make(form) );
    }
    @AfterEach
    void tearDown() {
    }
}