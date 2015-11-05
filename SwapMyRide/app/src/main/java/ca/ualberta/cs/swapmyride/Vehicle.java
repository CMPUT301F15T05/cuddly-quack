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

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Picture;
import android.provider.ContactsContract;

public class Vehicle {
    private Photo photo;
    private String name;
    private Integer quantity;
    private String comments;
    private VehicleCategory category;
    private VehicleQuality quality;
    private boolean isPublic;

    public Vehicle(){
        //add default constructor for photo
        name = "";
        quantity = 0;
        comments = "";
        category = VehicleCategory.NONE;
        quality = VehicleQuality.NONE;
        isPublic = true;
    }


    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public VehicleCategory getCategory() {
        return category;
    }

    public void setCategory(VehicleCategory category) {
        this.category = category;
    }

    public VehicleQuality getQuality() {
        return quality;
    }

    public void setQuality(VehicleQuality quality) {
        this.quality = quality;
    }


    public boolean getPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public void setPhoto(Photo photo){
        this.photo = photo;
    }

    public Photo getPhoto(){
        return this.photo;
    }

    public void deletePhoto(Context context){
        this.photo = new Photo(BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_search));
    }

}
