package com.example.living.userInterface.mainPage.featurePage.requestCustomerPage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.requestCustomer.ResponseRequestCustomer;
import com.example.living.data.remote.retrofit.requestCustomer.ApiConfigCreateRequestCustomerPage;
import com.example.living.data.remote.retrofit.requestCustomer.ApiServiceRequestCustomer;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelCreateRequestCustomerPage extends ViewModel {
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public MutableLiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void createRequestCustomer(
            String identifierRequestCustomer, String nameCustomer, String contactCustomer, String domicileCustomer,
            String descriptionRequestCustomer, String locationProject, String priceListProjectCash,
            String priceListProjectCredit, String statusProject
    ) {
        ApiServiceRequestCustomer apiService = ApiConfigCreateRequestCustomerPage.getService();
        Call<ResponseRequestCustomer> call = apiService.createRequestCustomer(
                identifierRequestCustomer, nameCustomer, contactCustomer, domicileCustomer,
                descriptionRequestCustomer, locationProject, priceListProjectCash, priceListProjectCredit,
                statusProject
        );

        call.enqueue(new Callback<ResponseRequestCustomer>() {
            @Override
            public void onResponse(Call<ResponseRequestCustomer> call, Response<ResponseRequestCustomer> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    toastMessage.setValue(message);
                } else {
                    toastMessage.setValue(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseRequestCustomer> call, Throwable throwable) {
                throwable.printStackTrace();
                toastMessage.setValue("onFailure");
            }
        });
    }
}
