package ca.ualberta.cs.swapmyride;

import android.graphics.Bitmap;
import android.graphics.Picture;
import android.test.ActivityInstrumentationTestCase2;

import java.io.File;

/**
 * Created by Conner on 15-10-08.
 */
public class PhotoTest extends ActivityInstrumentationTestCase2 {
    public Picture picture = new Picture();
    public Picture tooLargePicture = new Picture();

    public PhotoTest(){
        super(MainMenu.class);
    }

    // Use Case 23: Add Photo
    public void testAddPhotoToItem(){
        Vehicle vehicle = new Vehicle();
        vehicle.setPhoto(picture);
        assertTrue(vehicle.getPhoto().equals(picture));
    }

    // Use Case 24: View Photo
    public void testViewPhoto(){
        Vehicle vehicle = new Vehicle();

        Boolean thrown = false;
        try {
            vehicle.getPhoto();  // no photo set yet
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        vehicle.setPhoto(picture);
        Boolean thrown2 = false;
        try {
            vehicle.getPhoto();  // shouldn't throw an error, photo is added
        } catch (Exception e){
            thrown2 = true;
        }
        assertFalse(thrown2);
    }

    // Use Case 25: Delete Photo
    public void testDeletePhoto(){
        Vehicle vehicle = new Vehicle();

        Boolean thrown = false;
        try {
            vehicle.deletePhoto();  // no photo, error
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        vehicle.setPhoto(picture);
        assertTrue(vehicle.getPhoto().equals(picture));
        Boolean thrown2 = false;
        try {
            vehicle.deletePhoto();  // shouldn't throw an error, photo is added
        } catch (Exception e){
            thrown2 = true;
        }
        assertFalse(thrown2);

    }

    // Use Case 26: Restrict Image Size
    public void testTooLargePhoto(){
        Vehicle vehicle = new Vehicle();

        Boolean thrown = false;
        try {
            vehicle.setPhoto(tooLargePicture);
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    // Use Case 27: Download Photo
    public void testDownloadPhoto(){
        // I'll be honest, not really sure how to test this one!

        // turn network off here some how
        Vehicle vehicle = new Vehicle();  // need to be an vehicle stored on remote

        Boolean thrown = false;
        try {
            vehicle.getPhoto();
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}

