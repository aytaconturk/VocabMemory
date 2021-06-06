package com.example.vocabmemory.Modal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IPhrasesAndIdiomsDao {

    @Insert
    public void addWord(PhrasesAndIdioms phrasesAndIdioms);

    @Delete
    public void deleteWord(PhrasesAndIdioms phrasesAndIdioms);

    @Update
    public void updateWord(PhrasesAndIdioms phrasesAndIdioms);

    @Query("SELECT * FROM phrasesAndIdioms")
    public List<PhrasesAndIdioms> getPhrasesAndIdioms();

    @Query("SELECT word FROM phrasesAndIdioms")
    public List<String> getPhrasesAndIdiomsWords();

    @Query("SELECT COUNT(*) FROM phrasesAndIdioms")
    public int getPhrasesAndIdiomsLength();

    @Query("SELECT * FROM phrasesAndIdioms WHERE id =:id")
    public PhrasesAndIdioms getPhrasesAndIdiomsById(int id);

    @Query("SELECT * FROM phrasesAndIdioms WHERE word =:word")
    public PhrasesAndIdioms getPhrasesAndIdiomByWord(String word);

    @Query("SELECT COUNT(*) FROM phrasesAndIdioms WHERE learningStatus = 0")
    public int getDontKnowWordNumber();

    @Query("SELECT COUNT(*) FROM phrasesAndIdioms WHERE learningStatus = 1")
    public int getKnowWordNumber();
}
