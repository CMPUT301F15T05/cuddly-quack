package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

/**
 * Created by Conner on 15-10-08.
 */
public class PhotoTest extends ActivityInstrumentationTestCase2 {
    public PhotoTest(){
        super(MainMenu.class);
    }

    public void testAddPhotoToItem(){
        Item item = new Item();
        item.setPhoto(picture);
        assertTrue(item.getPhoto().equals(photo));

    }

    public void testViewPhoto(){
        Item item = new Item();

        Boolean thrown = false;
        try {
            item.getPhoto();  // no photo set yet
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        item.setPhoto(picture);
        Boolean thrown2 = false;
        try {
            item.getPhoto();  // shouldn't throw an error, photo is added
        } catch (Exception e){
            thrown2 = true;
        }
        assertFalse(thrown2);
    }

    public void testDeletePhoto(){
        Item item = new Item();

        Boolean thrown = false;
        try {
            item.deletePhoto();  // no photo, error
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);

        item.setPhoto(picture);
        assertTrue(item.getPhoto().equals(photo));
        Boolean thrown2 = false;
        try {
            item.deletePhoto();  // shouldn't throw an error, photo is added
        } catch (Exception e){
            thrown2 = true;
        }
        assertFalse(thrown2);

    }

    public void testTooLargePhoto(){
        Item item = new Item();

        Boolean thrown = false;
        try {
            item.setPhoto(tooLargePicture);
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }

    public void testDownloadPhoto(){
        // I'll be honest, not really sure how to test this one!

        Network.off(); // turn network off here some how
        Item item = new Item();  // need to be an item stored on remote

        Boolean thrown = false;
        try {
            item.downloadPhoto();
        } catch (Exception e){
            thrown = true;
        }
        assertTrue(thrown);
    }
}

