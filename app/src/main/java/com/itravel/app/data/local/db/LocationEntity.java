package com.itravel.app.data.local.db;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "locations")
public class LocationEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String name;
    public String category;
    public String description;
    public String address;
    public double latitude;
    public double longitude;
    public String imageUrl;
    public String country;
}
