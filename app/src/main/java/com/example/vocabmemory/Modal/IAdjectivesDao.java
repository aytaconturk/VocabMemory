package com.example.vocabmemory.Modal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IAdjectivesDao {

    @Insert
    public void addWord(Adjectives adjectives);

    @Delete
    public void deleteWord(Adjectives adjectives);

    @Update
    public void updateWord(Adjectives adjectives);

    @Query("SELECT * FROM adjectives")
    public List<Adjectives> getAdjectives();

    @Query("SELECT word FROM adjectives")
    public List<String> getAdjectivesWords();

    @Query("SELECT COUNT(*) FROM adjectives")
    public int getAdjectivesLength();

    @Query("SELECT * FROM adjectives WHERE id =:id")
    public Adjectives getAdjectiveById(int id);

    @Query("SELECT * FROM adjectives WHERE word =:word")
    public Adjectives getAdjectiveByWord(String word);

    @Query("SELECT COUNT(*) FROM adjectives WHERE learningStatus = 0")
    public int getDontKnowWordNumber();

    @Query("SELECT COUNT(*) FROM adjectives WHERE learningStatus = 1")
    public int getKnowWordNumber();
}
