package com.example.vocabmemory.Modal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vocabmemory.Modal.Adverbs;

import java.util.List;

@Dao
public interface IAdverbsDao {

    @Insert
    public void addWord(Adverbs adverbs);

    @Delete
    public void deleteWord(Adverbs adverbs);

    @Update
    public void updateWord(Adverbs adverbs);

    @Query("SELECT * FROM adverbs")
    public List<Adverbs> getAdverbs();

    @Query("SELECT word FROM adverbs")
    public List<String> getAdverbWords();

    @Query("SELECT COUNT(*) FROM adverbs")
    public int getAdverbsLength();

    @Query("SELECT * FROM adverbs WHERE id =:id")
    public Adverbs getAdverbById(int id);

    @Query("SELECT * FROM adverbs WHERE word =:word")
    public Adverbs getAdverbByWord(String word);

    @Query("SELECT COUNT(*) FROM adverbs WHERE learningStatus = 0")
    public int getDontKnowWordNumber();

    @Query("SELECT COUNT(*) FROM adverbs WHERE learningStatus = 1")
    public int getKnowWordNumber();
}
