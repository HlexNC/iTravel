package com.itravel.app.data.local.db;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "users", indices = {@Index(value = "email", unique = true)})
public class UserEntity {
    @PrimaryKey(autoGenerate = true)
    public long id;

    public String email;
    public String name;
    public String passwordHash;
    public String passwordSalt;
    public String bio;
    public String travelerType;
    public String profileImageUri;
}
