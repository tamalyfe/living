package com.example.living.userInterface.mainPage.featurePage.recruitmentTeamPage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.recruitmentCustomer.ResponseRecruitmentTeam;
import com.example.living.data.remote.retrofit.recruitmentCustomer.ApiConfigDetailRecruitmentTeamPage;
import com.example.living.data.remote.retrofit.recruitmentCustomer.ApiServiceRecruitmentTeam;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetailRecruitmentTeamPage extends ViewModel {
    private MutableLiveData<ResponseRecruitmentTeam> responseLiveData = new MutableLiveData<>();

    public void deleteRecruitmentTeam(String identifierRecruitmentTeam) {
        ApiServiceRecruitmentTeam apiService = ApiConfigDetailRecruitmentTeamPage.getApiService();
        Call<ResponseRecruitmentTeam> call = apiService.deleteRecruitmentTeam(identifierRecruitmentTeam);

        call.enqueue(new Callback<ResponseRecruitmentTeam>() {
            @Override
            public void onResponse(Call<ResponseRecruitmentTeam> call, Response<ResponseRecruitmentTeam> response) {
                responseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseRecruitmentTeam> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public MutableLiveData<ResponseRecruitmentTeam> getResponseLiveData() {
        return responseLiveData;
    }
}
