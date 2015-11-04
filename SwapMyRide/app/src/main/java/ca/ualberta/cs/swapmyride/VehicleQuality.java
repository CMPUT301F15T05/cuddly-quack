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
