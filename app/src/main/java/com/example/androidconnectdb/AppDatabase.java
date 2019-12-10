package com.example.androidconnectdb;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {BoyFriend.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract BoyFriendDao boyFriendDao();
}
