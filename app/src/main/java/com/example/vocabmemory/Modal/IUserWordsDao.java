package com.example.vocabmemory.Modal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface IUserWordsDao {

    @Insert
    public void addWord(UserWords userWords);

    @Delete
    public void deleteWord(UserWords userWords);

    @Update
    public void updateWord(UserWords userWords);

    @Query("SELECT * FROM userWords")
    public List<UserWords> getUserWords();

    @Query("SELECT * FROM userWords ORDER BY id DESC")
    public List<UserWords> getUserWordsReverseOrder();

    @Query("SELECT COUNT(*) FROM userWords")
    public int getUserWordsLength();

    @Query("SELECT * FROM userWords WHERE id =:id")
    public UserWords getUserWordById(int id);

    @Query("SELECT id FROM userWords WHERE id = (SELECT MAX(id) FROM userWords)")
    public int getLastId();

    @Query("SELECT COUNT(*) FROM userWords WHERE learningStatus = 0")
    public int getDontKnowWordNumber();

    @Query("SELECT COUNT(*) FROM userWords WHERE learningStatus = 1")
    public int getKnowWordNumber();
}
