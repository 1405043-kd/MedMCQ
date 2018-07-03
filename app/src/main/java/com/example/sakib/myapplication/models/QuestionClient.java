package com.example.sakib.myapplication.models;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface QuestionClient {
    @GET("/c_q/")
    Call<List<Questions>> cqAll();

    @GET("/c_q/{sub}/")
    Call<List<Questions>> cqSub(@Path("sub") String sub);

    @GET("/c_q/{qid}/")
    Call<List<Questions>> cqID(@Path("qid") String qid);

    @GET("/c_q/{sub}/{chapter}/")
    Call<List<Questions>> cqSubChap(@Path("sub") String sub, @Path("chapter") String chapter);

    @GET("/x_q/")
    Call<List<Questions>> xqAll();

    @GET("/x_q/{year}/")
    Call<List<Questions>> xqYear(@Path("year") String year);

    @GET("/x_q/{year}/{MVD}/")
    Call<List<Questions>> xqYearMVD(@Path("year") String year, @Path("MVD") String MVD);

    @GET("/q_set/")
    Call<List<QuestionSet>> q_set();

    @GET("/e_history/")
    Call<List<ExamHistory>> e_history();

    @GET("/e_history/{user_id}/")
    Call<List<ExamHistory>> e_history_user(@Path("user_id") String user_id);

    @GET("/e_history/e/{exam_name}/")
    Call<List<ExamHistory>> get_history_daily(@Path("exam_name") String exam_name);

    @POST("/e_history/")
    Call<ExamHistory> post_e_history(@Body ExamHistory examHistory);

    @POST("/profile_mod/")
    Call<Users> post_users(@Body Users users);

    @GET("/profile_mod/{user_id}/")
    Call<Users> get_user(@Path("user_id") String user_id);

}
