package com.example.sakib.myapplication.models;

public class ExamHistory {

    private int UserId;
    private int QuestionId;
    private String TableName;
    private int marks;
    private int position;

    public ExamHistory() {
    }

    public ExamHistory(int userId, int questionId, String tableName, int marks, int position) {
        UserId = userId;
        QuestionId = questionId;
        TableName = tableName;
        marks = marks;
        position = position;
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

    public int getMarks() {
        return marks;
    }

    public int getPosition() {
        return position;
    }


}