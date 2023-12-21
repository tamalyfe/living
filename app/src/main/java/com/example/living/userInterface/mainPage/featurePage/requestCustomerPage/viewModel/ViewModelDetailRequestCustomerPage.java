package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;
import com.example.living.data.remote.retrofit.requestCustomer.ApiConfigDetailRequestCustomerPage;
import com.example.living.data.remote.retrofit.requestCustomer.ApiServiceRequestCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetailRequestCustomerPage extends ViewModel {
    private MutableLiveData<ResponseRequestCustomer> responseLiveData = new MutableLiveData<>();

    public void deleteRequestCustomer(String identifierRequestCustomer) {
        ApiServiceRequestCustomer apiService = ApiConfigDetailRequestCustomerPage.getApiService();
        Call<ResponseRequestCustomer> call = apiService.deleteRequestCustomer(identifierRequestCustomer);

        call.enqueue(new Callback<ResponseRequestCustomer>() {
            @Override
            public void onResponse(Call<ResponseRequestCustomer> call, Response<ResponseRequestCustomer> response) {
                responseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseRequestCustomer> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public MutableLiveData<ResponseRequestCustomer> getResponseLiveData() {
        return responseLiveData;
    }
}
