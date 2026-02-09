package com.itravel.app.data.local.db;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.itravel.app.util.Constants;

@Database(entities = {UserEntity.class, LocationEntity.class, ReviewEntity.class, LikeEntity.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static volatile AppDatabase INSTANCE;

    public abstract UserDao userDao();
    public abstract LocationDao locationDao();
    public abstract ReviewDao reviewDao();
    public abstract LikeDao likeDao();

    public static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            synchronized (AppDatabase.class) {
                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            Constants.DB_NAME
                    )
                    .addCallback(new RoomDatabase.Callback() {
                        @Override
                        public void onCreate(@NonNull SupportSQLiteDatabase db) {
                            super.onCreate(db);
                            DatabaseSeeder.seed(INSTANCE);
                        }
                    })
                    .build();
                }
            }
        }
        return INSTANCE;
    }
}
