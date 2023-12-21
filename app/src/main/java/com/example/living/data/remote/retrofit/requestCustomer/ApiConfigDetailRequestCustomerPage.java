package com.example.living.data.remote.retrofit.requestCustomer;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ApiConfigDetailRequestCustomerPage {
    private static final String BASE_URL = "https://tamalyfe.000webhostapp.com/db_tama_lyfe/tb_request_customer/";

    public static ApiServiceRequestCustomer getApiService() {
        OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        httpClient.addInterceptor(logging);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(httpClient.build())
                .build();

        return retrofit.create(ApiServiceRequestCustomer.class);
    }
}
