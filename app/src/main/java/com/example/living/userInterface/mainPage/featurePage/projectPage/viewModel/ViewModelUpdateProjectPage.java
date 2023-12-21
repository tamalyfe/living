package com.example.living.userInterface.mainPage.featurePage.projectPage.viewModel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.retrofit.project.ApiConfigUpdateProjectPage;
import com.example.living.data.remote.retrofit.project.ApiServiceProject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelUpdateProjectPage extends ViewModel {
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public LiveData<String> getUpdateResult() {
        return toastMessage;
    }

    public void updateProject(
            String identifierProject, String nameProject, String typeProject, String accessProject,
            String statusProject, String locationProject, String priceListProjectCash,
            String priceListProjectCredit, String promo, String descriptionProject, String bedroom, String bathroom,
            String carport, String buildingArea, String surfaceArea, String facility, String nameDeveloper,
            String contactDeveloper, String loanBank, String handover
    ) {
        ApiServiceProject apiService = ApiConfigUpdateProjectPage.getApiService();
        Call<ResponseProject> call = apiService.updateProject(
                identifierProject, nameProject, typeProject, accessProject,
                statusProject, locationProject, priceListProjectCash, priceListProjectCredit,
                promo, descriptionProject, bedroom, bathroom, carport, buildingArea, surfaceArea, facility, nameDeveloper,
                contactDeveloper, loanBank, handover
        );

        call.enqueue(new Callback<ResponseProject>() {
            @Override
            public void onResponse(Call<ResponseProject> call, Response<ResponseProject> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    toastMessage.setValue(message);
                } else {
                    toastMessage.setValue(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
                throwable.printStackTrace();
                toastMessage.setValue("onFailure");
            }
        });
    }
}
