package ca.ualberta.cs.swapmyride.Misc;

/**
 * Created by adrianomarini on 15-11-26.
 */
public enum DistanceOption {
    ONE_HUNDRED_KM("100",0), FIFTY_KM("50", 1), TEN_KM("10",2), FIVE_KM("5", 3), TWO_KM("2", 4), ONE_KM("1",5);

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

