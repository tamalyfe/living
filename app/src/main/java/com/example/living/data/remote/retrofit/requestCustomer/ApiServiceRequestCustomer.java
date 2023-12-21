package com.example.living.data.remote.retrofit.requestCustomer;

import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiServiceRequestCustomer {
    @FormUrlEncoded
    @POST("create.php")
    Call<ResponseRequestCustomer> createRequestCustomer(
            @Field("identifier_request_customer") String identifier_request_customer,
            @Field("name_customer") String name_customer,
            @Field("contact_customer") String contact_customer,
            @Field("domicile_customer") String domicile_customer,
            @Field("description_request_customer") String description_request_customer,
            @Field("location_project") String location_project,
            @Field("price_list_project_cash") String price_list_project_cash,
            @Field("price_list_project_credit") String price_list_project_credit,
            @Field("status_project") String status_project
    );

    @GET("read.php")
    Call<ResponseRequestCustomer> readRequestCustomer();

    @FormUrlEncoded
    @POST("update.php")
    Call<ResponseRequestCustomer> updateRequestCustomer(
            @Field("identifier_request_customer") String identifier_request_customer,
            @Field("name_customer") String name_customer,
            @Field("contact_customer") String contact_customer,
            @Field("domicile_customer") String domicile_customer,
            @Field("description_request_customer") String description_request_customer,
            @Field("location_project") String location_project,
            @Field("price_list_project_cash") String price_list_project_cash,
            @Field("price_list_project_credit") String price_list_project_credit,
            @Field("status_project") String status_project
    );

    @FormUrlEncoded
    @POST("delete.php")
    Call<ResponseRequestCustomer> deleteRequestCustomer(
            @Field("identifier_request_customer") String identifier_request_customer
    );

    @FormUrlEncoded
    @POST("search.php")
    Call<ResponseRequestCustomer> searchRequestCustomer(
            @Field("search_request_customer") String search_request_customer
    );
}
