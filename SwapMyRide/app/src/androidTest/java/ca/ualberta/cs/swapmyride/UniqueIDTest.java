package ca.ualberta.cs.swapmyride;

import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.swapmyride.Misc.UniqueID;

/**
 * Created by Conner on 2015-11-19.
 */
public class UniqueIDTest extends ActivityInstrumentationTestCase2 {
    public UniqueIDTest() {
        super(ActivityInstrumentationTestCase2.class);
    }

    public void testGetID() {
        UniqueID a = new UniqueID();
        assertTrue(a.getID() instanceof String);
    }

    public void testDuplicateID() {
        UniqueID a = new UniqueID();
        UniqueID b = a.duplicateID();

        assertTrue(a.getID().equals(b.getID()));
    }

    public void testIsEqual() {
        UniqueID aID = new UniqueID();
        UniqueID aCopy = aID.duplicateID();
        UniqueID diffID = new UniqueID();

        assertTrue(aID.getID().equals(aCopy.getID()));
        assertFalse(aID.getID().equals(diffID.getID()));

        assertTrue(aID.isEqualID(aCopy));
        assertFalse(aID.isEqualID(diffID));
    }
}
