package com.example.androidconnectdb;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface BoyFriendDao {
    @Query("SELECT * FROM boyfriend")
    List<BoyFriend> getAll();

    @Query("SELECT * FROM boyfriend WHERE uid IN (:userIds)")
    List<BoyFriend> loadAllByIds(int[] userIds);

    @Query("SELECT * FROM boyfriend WHERE name LIKE :first AND " +
            "date LIKE :last LIMIT 1")
    BoyFriend findByName(String first, String last);

    @Insert
    void insert(BoyFriend boyFriend);

    @Delete
    void delete(BoyFriend boyFriend);
}
