package com.itravel.app.data.local.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

@Dao
public interface UserDao {
    @Insert
    long insert(UserEntity user);

    @Update
    void update(UserEntity user);

    @Query("SELECT * FROM users WHERE email = :email LIMIT 1")
    UserEntity getUserByEmail(String email);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    LiveData<UserEntity> getUserById(long id);

    @Query("SELECT * FROM users WHERE id = :id LIMIT 1")
    UserEntity getUserByIdSync(long id);

    @Query("SELECT COUNT(*) FROM users WHERE email = :email")
    int countByEmail(String email);
}
