package ca.ualberta.cs.swapmyride;

import java.lang.reflect.Array;
import java.util.ArrayList;

/**
 * Created by adrianomarini on 2015-10-26.
 */
public class TradeList {
    private ArrayList<Trade> trades = new ArrayList<Trade>();

    public TradeList() {}

    public ArrayList<Trade> getTrades() {
        return trades;
    }

    public void setTrades(ArrayList<Trade> trades) {
        this.trades = trades;
    }

    public void add(Trade trade){
        trades.add(trade);
    }

    public int getSize(){
        return trades.size();
    }

    public Trade get(int index){
        return trades.get(index);
    }

    public void delete(Trade trade){
        int index = this.trades.indexOf(trade);
        this.trades.remove(index);
    }

    public ArrayList<Trade> getUserTrades(User user){
        TradeList pendingTrades = user.getPendingTrades();
        TradeList pastTrades = user.getPastTrades();
        ArrayList<Trade> miniTradeList = new ArrayList<>();
        miniTradeList.addAll(pendingTrades.getTrades());
        miniTradeList.addAll(pastTrades.getTrades());
        return miniTradeList;
    }

}
