package ca.ualberta.cs.swapmyride;

import android.location.Address;
import android.test.ActivityInstrumentationTestCase2;

import ca.ualberta.cs.swapmyride.Model.Geolocation;
import ca.ualberta.cs.swapmyride.View.MainMenu;

/**
 * Created by adrianomarini on 15-11-22.
 */
public class GeolocationTest extends ActivityInstrumentationTestCase2 {

    public GeolocationTest() {
        super(MainMenu.class);
    }

    public void testGetLocation(){
        Address address;
        Geolocation geolocation = new Geolocation();
        address = geolocation.getCurrentLocation(getActivity(), super.getActivity());
        assertEquals(address.getCountryName().toLowerCase(), "canada");
        assertEquals(address.getLocality().toLowerCase(), "edmonton");
    }

    public void testSetCustomLocation(){
        Address address;
        Geolocation geolocation = new Geolocation();
        address = geolocation.setSpecificLocation(getActivity(), super.getActivity(), "T5Y2V8");
        assertEquals(address.getCountryName().toLowerCase(), "canada");
        assertEquals(address.getLocality().toLowerCase(), "edmonton");
    }

}
