package com.itravel.app.data.local.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "reviews",
    foreignKeys = {
        @ForeignKey(entity = LocationEntity.class, parentColumns = "id", childColumns = "locationId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE)
    },
    indices = {
        @Index("locationId"),
        @Index("userId")
    }
)
public class ReviewEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long locationId;
    public long userId;
    public float rating;
    public String text;
    public String imageUris;
    public long timestamp;
}
