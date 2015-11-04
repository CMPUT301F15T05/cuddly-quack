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
package ca.ualberta.cs.swapmyride;

/**
 * Created by Garry on 2015-10-26.
 */

/*Class based on tutorial from
  http://javarevisited.blogspot.ca/2011/08/enum-in-java-example-tutorial.html, Oct 26 2015
 */
public enum VehicleCategory {
    // TODO: add more categories
    NONE("None",0), SEDAN("Sedan",1), COUPE("Coupe",2), SUV("SUV",3), CROSSOVER("Crossover",4), TRUCK("Truck",5);

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
