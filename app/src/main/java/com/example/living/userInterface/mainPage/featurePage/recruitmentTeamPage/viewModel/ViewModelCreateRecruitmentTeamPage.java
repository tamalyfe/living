package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.recruitmentCustomer.ResponseRecruitmentTeam;
import com.example.living.data.remote.retrofit.recruitmentCustomer.ApiConfigCreateRecruitmentTeamPage;
import com.example.living.data.remote.retrofit.recruitmentCustomer.ApiServiceRecruitmentTeam;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelCreateRecruitmentTeamPage extends ViewModel {
    private final MutableLiveData<String> toastMessage = new MutableLiveData<>();

    public MutableLiveData<String> getToastMessage() {
        return toastMessage;
    }

    public void createRecruitmentTeam(
            String identifierRecruitmentTeam, String nameTeam, String postTeam, String domicileTeam,
            String jobDescription, String experience, String certificate
    ) {
        ApiServiceRecruitmentTeam apiService = ApiConfigCreateRecruitmentTeamPage.getService();
        Call<ResponseRecruitmentTeam> call = apiService.createRecruitmentTeam(
                identifierRecruitmentTeam, nameTeam, postTeam, domicileTeam,
                jobDescription, experience, certificate
        );

        call.enqueue(new Callback<ResponseRecruitmentTeam>() {
            @Override
            public void onResponse(Call<ResponseRecruitmentTeam> call, Response<ResponseRecruitmentTeam> response) {
                String value = response.body().getValue();
                String message = response.body().getMessage();

                if (value.equals("1")) {
                    toastMessage.setValue(message);
                } else {
                    toastMessage.setValue(message);
                }
            }

            @Override
            public void onFailure(Call<ResponseRecruitmentTeam> call, Throwable throwable) {
                throwable.printStackTrace();
                toastMessage.setValue("onFailure");
            }
        });
    }
}
