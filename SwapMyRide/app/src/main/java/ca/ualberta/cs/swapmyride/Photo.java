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
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Photo is the enclosed object that implements
 * Bitmap functionality to create, set, return,
 * and delete images from an item
 *
 * @author Garry on 2015-11-03.
 */
public class Photo{
    //private Bitmap image;
    private byte image[];

    /**
     * Constructor for a Photo if an image exists.
     * @param image
     */

    Photo(Bitmap image){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.image = stream.toByteArray();
    }

    /**
     * Constructor for a Photo if an image does not exist.
     * Taking the context, the image is set to the default.
     * @param context
     */

    Photo(Context context){
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), R.drawable.ic_default_car);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.image = stream.toByteArray();
    }

    /**
     * Returns the image that is located in the object
     * @return Bitmap of Image
     */

    public Bitmap getImage() {
        int size = image.length;
        Bitmap map;
        map = BitmapFactory.decodeByteArray(image, 0, size);
        return map;
    }

    /**
     * With an image as input, sets the stored image as said input
     * @param image
     */

    public void setImage(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 100, stream);
        this.image = stream.toByteArray();
    }

    /**
     * Deletes any image other than the default by setting it to the
     * default image.
     * @param context
     */

    public void deleteImage(Context context){
        Photo photo = new Photo(context);
        this.setImage(photo.getImage());
    }
}
