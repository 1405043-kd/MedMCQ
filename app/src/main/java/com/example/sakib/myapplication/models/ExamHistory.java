package com.example.sakib.myapplication.models;

public class ExamHistory {

    private String UserId;
    private int QuestionId;
    private String TableName;
    private float Marks;
    private int Position;

    public ExamHistory() {
    }

    public ExamHistory(String userId, int questionId, String tableName, float marks) {
        this.UserId = userId;
        this.QuestionId = questionId;
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


}