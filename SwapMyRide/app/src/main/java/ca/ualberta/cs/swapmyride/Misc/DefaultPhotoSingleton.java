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

import android.content.Context;
import android.graphics.Bitmap;

import ca.ualberta.cs.swapmyride.Model.Photo;

/**
 *  Keep the stock photo in memory so network and disk have
 *  less reads and writes.
 *
 *  Created by Garry on 2015-11-30.
 */
public class DefaultPhotoSingleton {
    private static DefaultPhotoSingleton ourInstance = null;
    private Bitmap image;
    private Photo photo;

    private DefaultPhotoSingleton() {
    }

    /**
     * Returns the instance of the singleton
     *
     * @return DefaultPhotoSingleton
     */
    public static DefaultPhotoSingleton getInstance() {
        if (ourInstance == null) {
            ourInstance = new DefaultPhotoSingleton();
        }
        return ourInstance;
    }

    /**
     * Initialize the photo based on the current context
     *
     * @param context
     */
    public void init(Context context) {
        photo = new Photo(context);
        image = photo.getImage();
    }

    public Bitmap getImage() {
        return image;
    }

    public Photo getDefaultPhoto() {
        return photo;
    }
}
