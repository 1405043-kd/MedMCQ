package com.example.sakib.myapplication.models;

import android.support.annotation.NonNull;

public class ExamHistory implements Comparable<ExamHistory>{

    private String UserId;
    private String UserName;
    private int QuestionId;
    private String QuestionName;

    public void setUserId(String userId) {
        UserId = userId;
    }

    public String getQuestionName() {

        return QuestionName;
    }

    private String TableName;
    private float Marks;

    public void setUserName(String userName) {
        UserName = userName;
    }

    public String getUserName() {

        return UserName;
    }

    private int Position;

    public ExamHistory() {
    }

    public ExamHistory(String userId, String userName, int questionId, String  questionName, String tableName, float marks) {
        this.UserId = userId;
        this.UserName = userName;
        this.QuestionId = questionId;
        this.QuestionName = questionName;
        this.TableName = tableName;
        this.Marks = marks;
    }

    public String getUserId() {
        return UserId;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public String getTableName() {
        return TableName;
    }

    public float getMarks() {
        return Marks;
    }

    public int getPosition() {
        return Position;
    }


    @Override
    public int compareTo(@NonNull ExamHistory examHistory) {
        return -Float.compare(Marks, examHistory.Marks);
    }
}