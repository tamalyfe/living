package com.example.living.data.remote.retrofit.project;

import com.example.living.data.remote.response.project.ResponseProject;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceProject {
    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseProject> createProject(
            @Field("identifier_project") String identifier_project,
            @Field("name_project") String name_project,
            @Field("type_project") String type_project,
            @Field("access_project") String access_project,
            @Field("status_project") String status_project,
            @Field("location_project") String location_project,
            @Field("price_list_project_cash") String price_list_project_cash,
            @Field("price_list_project_credit") String price_list_project_credit,
            @Field("promo") String promo,
            @Field("description_project") String description_project,
            @Field("bedroom") String bedroom,
            @Field("bathroom") String bathroom,
            @Field("carport") String carport,
            @Field("building_area") String building_area,
            @Field("surface_area") String surface_area,
            @Field("facility") String facility,
            @Field("name_developer") String name_developer,
            @Field("contact_developer") String contact_developer,
            @Field("loan_bank") String loan_bank,
            @Field("handover") String handover
    );

    @GET("read.php")
    Call<ResponseProject> readProject();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseProject> updateProject(
            @Field("identifier_project") String identifier_project,
            @Field("name_project") String name_project,
            @Field("type_project") String type_project,
            @Field("access_project") String access_project,
            @Field("status_project") String status_project,
            @Field("location_project") String location_project,
            @Field("price_list_project_cash") String price_list_project_cash,
            @Field("price_list_project_credit") String price_list_project_credit,
            @Field("promo") String promo,
            @Field("description_project") String description_project,
            @Field("bedroom") String bedroom,
            @Field("bathroom") String bathroom,
            @Field("carport") String carport,
            @Field("building_area") String building_area,
            @Field("surface_area") String surface_area,
            @Field("facility") String facility,
            @Field("name_developer") String name_developer,
            @Field("contact_developer") String contact_developer,
            @Field("loan_bank") String loan_bank,
            @Field("handover") String handover
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseProject> deleteProject(
            @Field("identifier_project") String identifier_project
    );

    @FormUrlEncoded
    @POST("search.php")
    Call<ResponseProject> searchProject(
            @Field("search_project") String search_project
    );
}
