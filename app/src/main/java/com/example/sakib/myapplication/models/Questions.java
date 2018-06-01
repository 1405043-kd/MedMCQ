package com.example.sakib.myapplication.models;

public class Questions {
    //   private int id;
    //   private String MVD;
    private int QuestionId;
    private String Question;
    private String Option1;
    private String Option2;
    private String Option3;
    private String Option4;
    private String CorrectAns;


    public Questions() {
    }

//    public int getId() {
//

    public int getQuestionId() {
        return QuestionId;
    }

//        return id;
//    }
//    public String getMVD(){
//        return MVD;
//    }

    public String getQuestion() {
        return Question;
    }

    public String getOption1() {
        return Option1;
    }

    public String getOption2() {
        return Option2;
    }

    public String getOption3() {
        return Option3;
    }

    public String getOption4() {
        return Option4;
    }

    public String getCorrectAns() { return CorrectAns; }
}
