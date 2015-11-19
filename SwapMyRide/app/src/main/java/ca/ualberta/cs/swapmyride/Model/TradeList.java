/*
 * Copyright 2015 Adriano Marini, Carson McLean, Conner Dunn, Daniel Haberstock, Garry Bullock
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ca.ualberta.cs.swapmyride.Model;

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

    /**
     * Gets all of a users past and pending trades
     * @param user
     * @return ArrayList of all trades
     */
    public ArrayList<Trade> getUserTrades(User user){
        TradeList pendingTrades = user.getPendingTrades();
        TradeList pastTrades = user.getPastTrades();
        ArrayList<Trade> miniTradeList = new ArrayList<>();
        miniTradeList.addAll(pendingTrades.getTrades());
        miniTradeList.addAll(pastTrades.getTrades());
        return miniTradeList;
    }

}
