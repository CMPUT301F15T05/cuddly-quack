package ca.ualberta.cs.swapmyride.Misc;

import android.content.Context;
import android.graphics.Bitmap;

import ca.ualberta.cs.swapmyride.Model.Photo;

/**
 * Created by Garry on 2015-11-30.
 */
public class DefaultPhotoSingleton {
    private static DefaultPhotoSingleton ourInstance = null;
    private Bitmap image;
    private Photo photo;

    public void init(Context context){
        photo = new Photo(context);
        image = photo.getImage();
    }

    public Bitmap getImage(){return image;}

    public Photo getDefaultPhoto(){return photo; }

    public static DefaultPhotoSingleton getInstance() {
        if(ourInstance == null){
            ourInstance = new DefaultPhotoSingleton();
        }
        return ourInstance;
    }

    private DefaultPhotoSingleton() {
    }
}
