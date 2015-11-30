package ca.ualberta.cs.swapmyride.Misc;

import android.content.Context;

import ca.ualberta.cs.swapmyride.Model.Photo;

/**
 * Created by Garry on 2015-11-30.
 */
public class DefaultPhotoSingleton {
    private static DefaultPhotoSingleton ourInstance = null;
    private Photo defaultPhoto;

    public void init(Context context){
        this.defaultPhoto = new Photo(context);
    }

    public Photo getDefaultPhoto(){
        return defaultPhoto;
    }

    public static DefaultPhotoSingleton getInstance() {
        if(ourInstance == null){
            ourInstance = new DefaultPhotoSingleton();
        }
        return ourInstance;
    }

    private DefaultPhotoSingleton() {
    }
}
