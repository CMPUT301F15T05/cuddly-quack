package ca.ualberta.cs.swapmyride;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.google.gson.JsonDeserializer;
import com.google.gson.JsonSerializer;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by Garry on 2015-11-03.
 */
public class Photo{
    //private Bitmap image;
    byte image[];

    Photo(Bitmap image){
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        this.image = stream.toByteArray();
    }

    public Bitmap getImage() {
        int size = image.length;
        Bitmap map = BitmapFactory.decodeByteArray(image,0,size);
        return map;
    }

    public void setImage(Bitmap image) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.JPEG, 0, stream);
        this.image = stream.toByteArray();
    }

}
