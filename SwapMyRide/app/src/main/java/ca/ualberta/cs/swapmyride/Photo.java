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
public class Photo implements Serializable{
    private Bitmap image;

    Photo(Bitmap image){
        this.image = image;
    }

    public Bitmap getImage() {
        return image;
    }

    public void setImage(Bitmap image) {
        this.image = image;
    }

    //http://stackoverflow.com/questions/6002800/android-serializable-problem
    private void writeObject(java.io.ObjectOutputStream out) throws IOException{
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        image.compress(Bitmap.CompressFormat.PNG, 0, stream);
        byte bitmap[] = stream.toByteArray();
        int length = bitmap.length;
        //write out how many bytes we are going to send
        out.write(length);
        //write the byte stream out
        out.write(bitmap, 0, bitmap.length);
    }

    private void readObject(java.io.ObjectInputStream in)
            throws IOException, ClassNotFoundException{
        int length = in.read();
        byte bitmap[] = new byte[length];
        in.read(bitmap, 0, length);

        image = BitmapFactory.decodeByteArray(bitmap, 0, length);

    }
}
