package com.example.vocabmemory;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "verbs")
public class Verbs {

    @PrimaryKey
    private int id;

    @ColumnInfo(name = "word")
    private String word;

    @ColumnInfo(name = "meaning")
    private String meaning;

    @ColumnInfo(name = "example")
    private String example;

    @ColumnInfo(name = "learningStatus")
    private int learningStatus = 0;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public String getMeaning() {
        return meaning;
    }

    public void setMeaning(String meaning) {
        this.meaning = meaning;
    }

    public String getExample() {
        return example;
    }

    public void setExample(String example) {
        this.example = example;
    }

    public int getLearningStatus() {
        return learningStatus;
    }

    public void setLearningStatus(int learningStatus) {
        this.learningStatus = learningStatus;
    }
}