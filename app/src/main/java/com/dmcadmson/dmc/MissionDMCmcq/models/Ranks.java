package com.dmcadmson.dmc.MissionDMCmcq.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Ranks implements Serializable{
    //   private int id;
    //   private String MVD;
    private String UserRank;
    private String UserName;
    private String UserNumberExam;


    public Ranks() {
    }

//    public int getId() {
//

    public String getRank() {
        return UserRank;
    }

//        return id;
//    }
//    public String getMVD(){
//        return MVD;
//    }

    public String getUserName() {
        return UserName;
    }

    public String getUserNumberExam() {
        return UserNumberExam;
    }


}
