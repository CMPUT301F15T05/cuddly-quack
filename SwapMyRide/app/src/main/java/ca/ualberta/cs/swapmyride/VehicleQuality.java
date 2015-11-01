package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */
/*Class based on tutorial from
  http://javarevisited.blogspot.ca/2011/08/enum-in-java-example-tutorial.html, Oct 26 2015
*/
public enum VehicleQuality {
    RUSTBUCKET("Rust Bucket"), POOR("Poor"), OKAY("Okay"), GOOD("Good"), PRIME("Prime"), SHOWROOM("Show Room"), NONE("None");

    private String quality;

    VehicleQuality(String quality){
        this.quality = quality;
    }

    public String getQuality() {
        return quality;
    }
}
