package com.example.vocabmemory;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IVerbsDao {

    @Insert
    public void addVerb(Verbs verb);

    @Query("SELECT * FROM verbs")
    public List<Verbs> getVerbs();

    @Delete
    public void deleteVerb(Verbs verb);

    @Update
    public void updateVerb(Verbs verb);

}
