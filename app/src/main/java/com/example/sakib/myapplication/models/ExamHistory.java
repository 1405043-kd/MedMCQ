package com.example.sakib.myapplication.models;

public class ExamHistory {

    private int UserId;
    private int QuestionId;
    private String TableName;
    private float marks;
    private int position;

    public ExamHistory() {
    }

    public ExamHistory(int userId, int questionId, String tableName, float marks) {
        this.UserId = userId;
        this.QuestionId = questionId;
        this.TableName = tableName;
        this.marks = marks;
    }

    public int getUserId() {
        return UserId;
    }

    public int getQuestionId() {
        return QuestionId;
    }

    public String getTableName() {
        return TableName;
    }

    public float getMarks() {
        return marks;
    }

    public int getPosition() {
        return position;
    }


}