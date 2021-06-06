package com.example.vocabmemory.Modal;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.vocabmemory.Modal.Verbs;

import java.util.List;

@Dao
public interface IVerbsDao {

    @Insert
    public void addVerb(Verbs verb);

    @Delete
    public void deleteVerb(Verbs verb);

    @Update
    public void updateVerb(Verbs verb);

    @Query("SELECT * FROM verbs")
    public List<Verbs> getVerbs();

    @Query("SELECT word FROM verbs")
    public List<String> getVerbWords();

    @Query("SELECT COUNT(*) FROM verbs")
    public int getVerbsLength();

    @Query("SELECT * FROM verbs WHERE id =:id")
    public Verbs getVerbById(int id);

    @Query("SELECT * FROM verbs WHERE word =:word")
    public Verbs getVerbByWord(String word);

    @Query("SELECT id FROM verbs WHERE id = (SELECT MAX(id) FROM verbs)")
    public int getLastId();

    @Query("SELECT COUNT(*) FROM verbs WHERE learningStatus = 0")
    public int getDontKnowWordNumber();

    @Query("SELECT COUNT(*) FROM verbs WHERE learningStatus = 1")
    public int getKnowWordNumber();
}
