package com.itravel.app.data.local.db;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(
    tableName = "likes",
    foreignKeys = {
        @ForeignKey(entity = UserEntity.class, parentColumns = "id", childColumns = "userId", onDelete = ForeignKey.CASCADE),
        @ForeignKey(entity = LocationEntity.class, parentColumns = "id", childColumns = "locationId", onDelete = ForeignKey.CASCADE)
    },
    indices = {
        @Index(value = {"userId", "locationId"}, unique = true),
        @Index("locationId")
    }
)
public class LikeEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public long userId;
    public long locationId;
}
