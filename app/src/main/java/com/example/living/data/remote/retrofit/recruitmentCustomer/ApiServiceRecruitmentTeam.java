package com.example.living.data.remote.retrofit.recruitmentCustomer;

import com.example.living.data.remote.response.recruitmentCustomer.ResponseRecruitmentTeam;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceRecruitmentTeam {
    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseRecruitmentTeam> createRecruitmentTeam(
            @Field("identifier_recruitment_team") String identifier_recruitment_team,
            @Field("name_team") String name_team,
            @Field("post_team") String post_team,
            @Field("domicile_team") String domicile_team,
            @Field("job_description") String job_description,
            @Field("experience") String experience,
            @Field("certificate") String certificate
    );

    @GET("read.php")
    Call<ResponseRecruitmentTeam> readRecruitmentTeam();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseRecruitmentTeam> updateRecruitmentTeam(
            @Field("identifier_recruitment_team") String identifier_recruitment_team,
            @Field("name_team") String name_team,
            @Field("post_team") String post_team,
            @Field("domicile_team") String domicile_team,
            @Field("job_description") String job_description,
            @Field("experience") String experience,
            @Field("certificate") String certificate
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseRecruitmentTeam> deleteRecruitmentTeam(
            @Field("identifier_recruitment_team") String identifier_recruitment_team
    );

    @FormUrlEncoded
    @POST("search.php")
    Call<ResponseRecruitmentTeam> searchRecruitmentTeam(
            @Field("search_recruitment_team") String search_recruitment_team
    );
}
