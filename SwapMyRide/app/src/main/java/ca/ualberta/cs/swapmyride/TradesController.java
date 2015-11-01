package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-11-01.
 */
public class TradesController {

    public TradeList getActiveTrades(){
        return UserSingleton.getCurrentUser().getPendingTrades();
    }

    public TradeList getPastTrades(){
        return UserSingleton.getCurrentUser().getPastTrades();
    }

    
}
