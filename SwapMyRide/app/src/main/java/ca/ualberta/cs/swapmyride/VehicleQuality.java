package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */
/*Class based on tutorial from
  http://javarevisited.blogspot.ca/2011/08/enum-in-java-example-tutorial.html, Oct 26 2015
*/
public enum VehicleQuality {
    NONE("None",0), RUSTBUCKET("Rust Bucket",1), POOR("Poor",2), OKAY("Okay",3), GOOD("Good",4),
    PRIME("Prime",5), SHOWROOM("Show Room",6);

    private String quality;
    private int position;

    VehicleQuality(String quality,int position){
        this.quality = quality;
        this.position = position;
    }

    public String getQuality() {
        return quality;
    }
    public int getPosition(){return position; }
}
