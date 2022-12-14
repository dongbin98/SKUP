package com.dbsh.skup.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbsh.skup.client.PortalClient;
import com.dbsh.skup.api.PortalApi;
import com.dbsh.skup.dto.RequestGraduateNoneSubjectData;
import com.dbsh.skup.dto.RequestGraduateNoneSubjectParameterData;
import com.dbsh.skup.dto.ResponseGraduateNoneSubject;
import com.dbsh.skup.dto.ResponseGraduateNoneSubjectMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraduateNoneSubjectViewModel extends ViewModel {
    public MutableLiveData<ResponseGraduateNoneSubjectMap> graduateNoneSubjectLiveData = new MutableLiveData<>();
    public PortalApi portalApi;

    public void getGraduateNoneSubject(String token, String id) {
        PortalClient portalClient = PortalClient.getInstance(token);
        portalApi = PortalClient.getPortalApi();
        RequestGraduateNoneSubjectParameterData parameter = new RequestGraduateNoneSubjectParameterData(id);
        portalApi.getGraduateNoneSubjectData(new RequestGraduateNoneSubjectData(
                "education.ugd.UGD_03037.SELECT_UGD_NONDEPARTMENT_SEARCH",
                "AL",
                token,
                "common/selectOne",
                "UGD_03031_T",
                id,
                parameter)).enqueue(new Callback<ResponseGraduateNoneSubject>() {
            @Override
            public void onResponse(Call<ResponseGraduateNoneSubject> call, Response<ResponseGraduateNoneSubject> response) {
                // 졸업생의 경우 response.body() == null
                // System.out.println(response.body());
                if (response.isSuccessful()) {
                    if (response.body().getRtnStatus().equals("S")) {
                        ResponseGraduateNoneSubjectMap responseGraduateNoneSubjectMap = response.body().getResponseGraduateNoneSubjectMap();
                        graduateNoneSubjectLiveData.setValue(responseGraduateNoneSubjectMap);
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGraduateNoneSubject> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
    }
}
