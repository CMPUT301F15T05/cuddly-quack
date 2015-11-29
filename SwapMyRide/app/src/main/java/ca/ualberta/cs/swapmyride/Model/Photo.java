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
package ca.ualberta.cs.swapmyride.Model;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Base64;
import android.util.Log;

import java.io.ByteArrayOutputStream;

import ca.ualberta.cs.swapmyride.Misc.UniqueID;
import ca.ualberta.cs.swapmyride.R;

/**
 * Photo is the enclosed object that implements
 * Bitmap functionality to create, set, return,
 * and delete images from an item
 *
 * @author Garry on 2015-11-03.
 */
public class Photo{
    //private Bitmap image;
    UniqueID uid;
    private String encodedImage;

    /**
     * Builds a Photo object based on a given bitmap
     * @param image the image to store
     */

    public Photo(Bitmap image){
        byte temp[];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        temp = stream.toByteArray();
        encodedImage = Base64.encodeToString(temp, Base64.DEFAULT);
        uid = new UniqueID();
        Log.i("Size", Integer.toString(encodedImage.length()));
    }

    /**
     * Builds a default photo
     * @param context
     */

    public Photo(Context context){
        byte temp[];
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_default_car);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        temp = stream.toByteArray();
        encodedImage = Base64.encodeToString(temp, Base64.DEFAULT);
        uid = new UniqueID();
        Log.i("Size", Integer.toString(temp.length));
    }

    /**
     * Decodes and returns the stored image
     * @return A bitmap of the stored image
     */

    public Bitmap getImage() {
        byte image[];
        image = Base64.decode(encodedImage, Base64.DEFAULT);
        int size = image.length;
        Bitmap map;
        map = BitmapFactory.decodeByteArray(image, 0, size);
        return map;
    }

    /**
     * Encodes the given bitmap and stores it in the photo object
     * @param image bitmap image to store
     */

    public void setImage(Bitmap image) {
        byte temp[];
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        temp = stream.toByteArray();
        encodedImage = Base64.encodeToString(temp, Base64.DEFAULT);
    }

    /**
     * Removes the current image and sets it to the default
     * @param context
     */
    public void deleteImage(Context context){
        Photo photo = new Photo(context);
        this.setImage(photo.getImage());
    }

    public boolean equals(Photo other) {
        return this.getImage().sameAs(other.getImage());
    }
}
