package Main.Client.Strategy;
import Main.Client.*;

public class Context {
    private Strategy strategy;
    public void setStrategy(Client client){
        if(client instanceof PlatinumClient){
            strategy = new PlatinumStrategy();
        }
        if(client instanceof ClassicClient){
            strategy = new ClassicStrategy();
        }
        if(client instanceof EconomicClient){
            strategy = new EconomicStrategy();
        }
    }
    public Strategy getStrategy(){
        return this.strategy;
    }
}
