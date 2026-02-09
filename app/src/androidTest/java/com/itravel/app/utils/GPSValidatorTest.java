package com.itravel.app.utils;

import android.location.Location;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import org.junit.Test;
import org.junit.runner.RunWith;
import static org.junit.Assert.*;

@RunWith(AndroidJUnit4.class)
public class GPSValidatorTest {

   @Test
   public void isUserNearby_whenWithinRange_returnsTrue() {
       // Deggendorf Center
       double targetLat = 48.829;
       double targetLng = 12.961;

       Location userLocation = new Location("provider");
       userLocation.setLatitude(48.829);
       userLocation.setLongitude(12.961); // Exact same location

       assertTrue(GPSValidator.isUserNearby(userLocation, targetLat, targetLng));
   }

   @Test
   public void isUserNearby_whenFarAway_returnsFalse() {
       // Deggendorf
       double targetLat = 48.829;
       double targetLng = 12.961;

       Location userLocation = new Location("provider");
       userLocation.setLatitude(48.000); // Far away
       userLocation.setLongitude(12.000);

       assertFalse(GPSValidator.isUserNearby(userLocation, targetLat, targetLng));
   }
}
