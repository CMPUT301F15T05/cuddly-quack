package ca.ualberta.cs.swapmyride.Misc;

/**
 * Created by adrianomarini on 15-11-26.
 */
public enum DistanceOption {
    HUNDRED("100",0), FIFTY("50", 1), TWENTY("20",2), TEN("10",3), FIVE("5", 4), TWO("2", 5), ONE("1",6);

    private String distance;
    private int position;

    DistanceOption(String distance, int position) {
        this.distance = distance;
        this.position = position;
    }

    public String getDistance() {
            return distance;
    }

    public int getPosition(){
        return position;
    }
}

