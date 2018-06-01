package com.example.sakib.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Questions implements Serializable{
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

//    @Override
//    public int describeContents() {
//        return this.hashCode();
//    }
//
//    @Override
//    public void writeToParcel(Parcel parcel, int i) {
//        parcel.writeInt(QuestionId);
//        parcel.writeString(Question);
//        parcel.writeString(Option1);
//        parcel.writeString(Option2);
//        parcel.writeString(Option3);
//        parcel.writeString(Option4);
//        parcel.writeString(CorrectAns);
//
//    }
}
