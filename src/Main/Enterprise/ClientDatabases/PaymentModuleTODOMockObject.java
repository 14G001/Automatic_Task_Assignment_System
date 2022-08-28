package Main.Enterprise.ClientDatabases;
import java.util.HashMap;
import Utilities.RAMDataBase;

public class PaymentModuleTODOMockObject extends RAMDataBase<Double> { // @todo: make this a MockObject
    public PaymentModuleTODOMockObject(HashMap<Integer, Double> clientsDebt) {
        super(clientsDebt);
        ///< @todo: check how to get Platinum clients monthly fee.
    }
    @Override
    public int add(Double newDBRecord) {
        return super.add(0.0);
    }
}