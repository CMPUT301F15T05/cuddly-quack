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

package ca.ualberta.cs.swapmyride.Misc;

/**
 * Created by adrianomarini on 15-11-26.
 */
public enum DistanceOption {
    NONE("0", 0), ONE_HUNDRED_KM("100", 1), FIFTY_KM("50", 2), TEN_KM("10", 3), FIVE_KM("5", 4), TWO_KM("2", 5), ONE_KM("1", 6);

    private String distance;
    private int position;

    DistanceOption(String distance, int position) {
        this.distance = distance;
        this.position = position;
    }

    public String getDistance() {
        return distance;
    }

    public int getPosition() {
        return position;
    }

    // http://stackoverflow.com/questions/7758313/java-enum-overriding-tostring
    // Accessed: 11/28/2015 Author: Lazy Beard
    @Override
    public String toString() {
        String name = "";
        switch (this) {
            case ONE_HUNDRED_KM:
                name = "100KM";
                break;
            case FIFTY_KM:
                name = "50KM";
                break;
            case TEN_KM:
                name = "10KM";
                break;
            case FIVE_KM:
                name = "5KM";
                break;
            case TWO_KM:
                name = "2KM";
                break;
            case ONE_KM:
                name = "1KM";
                break;
            case NONE:
                name = "NONE";
                break;
        }
        return name;
    }
}

