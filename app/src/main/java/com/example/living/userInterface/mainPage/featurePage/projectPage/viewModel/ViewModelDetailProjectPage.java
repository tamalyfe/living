package com.example.living.userInterface.mainPage.featurePage.projectPage.viewModel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.living.data.remote.response.project.ResponseProject;
import com.example.living.data.remote.retrofit.project.ApiConfigDetailProjectPage;
import com.example.living.data.remote.retrofit.project.ApiServiceProject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ViewModelDetailProjectPage extends ViewModel {
    private MutableLiveData<ResponseProject> responseLiveData = new MutableLiveData<>();

    public void deleteProject(String identifierProject) {
        ApiServiceProject apiService = ApiConfigDetailProjectPage.getApiService();
        Call<ResponseProject> call = apiService.deleteProject(identifierProject);

        call.enqueue(new Callback<ResponseProject>() {
            @Override
            public void onResponse(Call<ResponseProject> call, Response<ResponseProject> response) {
                responseLiveData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<ResponseProject> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    public MutableLiveData<ResponseProject> getResponseLiveData() {
        return responseLiveData;
    }
}
