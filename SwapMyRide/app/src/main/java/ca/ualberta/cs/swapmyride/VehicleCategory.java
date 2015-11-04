package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */

/*Class based on tutorial from
  http://javarevisited.blogspot.ca/2011/08/enum-in-java-example-tutorial.html, Oct 26 2015
 */
public enum VehicleCategory {
    // TODO: add more categories
    NONE("None",0), SEDAN("Sedan",1), COUPE("Coupe",2), SUV("SUV",3), CROSSOVER("Cross Over",4), TRUCK("Truck",5);

    private String category;
    private int position;

    VehicleCategory(String category, int position){
        this.category = category;
        this.position = position;
    }

    public String getCategory() {
        return category;
    }

    public int getPosition(){
        return position;
    }
}
