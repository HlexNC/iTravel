package com.itravel.app.util;

import android.location.Location;

public class GPSValidator {

    public static boolean isUserNearby(Location userLocation, double targetLat, double targetLng) {
        if (userLocation == null)
            return false;

        Location targetLocation = new Location("");
        targetLocation.setLatitude(targetLat);
        targetLocation.setLongitude(targetLng);

        float distance = userLocation.distanceTo(targetLocation);
        return distance <= Constants.MAX_REVIEW_DISTANCE_METERS;
    }
}
