package com.example.quiz_game_project.data.local.entities;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.quiz_game_project.ui.adapters.AdapterData;

@Entity(tableName = "games")
//НОВО - IMPLEMEMNTS+++++++++++++++++++++++++++++++++++++++++++++++++
public class GameEntity implements AdapterData {

    @PrimaryKey(autoGenerate = true)
    public int uid;

    @ColumnInfo(name = "name")
    public String name;

    @ColumnInfo(name = "icon")
    public String icon;

    @ColumnInfo(name = "category1")
    public String category1;

    @ColumnInfo(name = "category2")
    public String category2;

    @ColumnInfo(name = "difficulty")
    public int difficulty;

    @ColumnInfo(name = "lastplayed")
    public String lastplayed;

    @ColumnInfo(name = "topplayer")
    public String topplayer;

    @ColumnInfo(name = "source")
    public String source;

    @ColumnInfo(name = "questiondeley")
    public int questiondeley;

    @ColumnInfo(name = "firstroundquestionanswer")
    public int firstroundquestionanswer;

    @ColumnInfo(name = "secondroundquestionanswer")
    public int secondroundquestionanswer;

    @ColumnInfo(name = "secondroundduration")
    public int secondroundduration;

    @ColumnInfo(name = "flag")
    public String flag;




    public int getUid() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public String getIcon() {
        return icon;
    }

    public String getCategory1() {
        return category1;
    }

    public String getCategory2() {
        return category2;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public String getLastplayed() {
        return lastplayed;
    }

    public String getTopplayer() {
        return topplayer;
    }

    public String getSource() {
        return source;
    }

    public int getQuestiondeley() {
        return questiondeley;
    }

    public int getFirstRoundQuestionAnswer() {
        return firstroundquestionanswer;
    }

    public int getSecondRoundQuestionAnswer() {
        return secondroundquestionanswer;
    }

    public int getSecondRoundDuration() {
        return secondroundduration;
    }

    public String getFlag() {
        return flag;
    }



    public void setName(String name) {
        this.name = name;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public void setCategory1(String category1) {
        this.category1 = category1;
    }

    public void setCategory2(String category2) {
        this.category2 = category2;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }

    public void setLastplayed(String lastplayed) {
        this.lastplayed = lastplayed;
    }

    public void setTopplayer(String topplayer) {
        this.topplayer = topplayer;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setQuestiondeley(int questiondeley) {
        this.questiondeley = questiondeley;
    }

    public void setFirstRoundQuestionAnswer(int firstroundquestionanswer) {
        this.firstroundquestionanswer = firstroundquestionanswer;
    }

    public void setSecondRoundQuestionAnswer(int secondroundquestionanswer) {
        this.secondroundquestionanswer = secondroundquestionanswer;
    }

    public void setSecondroundduration(int secondroundduration) {
        this.secondroundduration = secondroundduration;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }
}
