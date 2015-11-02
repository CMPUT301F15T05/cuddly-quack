package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */

/*Class based on tutorial from
  http://javarevisited.blogspot.ca/2011/08/enum-in-java-example-tutorial.html, Oct 26 2015
 */
public enum VehicleCategory {
    // TODO: add more categories
    SEDAN("Sedan"), COUPE("Coupe"), SUV("SUV"), CROSSOVER("Cross Over"), TRUCK("Truck"), NONE("None");

    private String category;

    VehicleCategory(String category){
        this.category = category;
    }

    public String getCategory() {
        return category;
    }
}
