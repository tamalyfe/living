package com.example.living.data.remote.retrofit.recruitmentCustomer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfigDetailRecruitmentTeamPage {
    private static final String BASE_URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_recruitment_team/";

    public static ApiServiceRecruitmentTeam getApiService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(ApiServiceRecruitmentTeam.class);
    }
}
