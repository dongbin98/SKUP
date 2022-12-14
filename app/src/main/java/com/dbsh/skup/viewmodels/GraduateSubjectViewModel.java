package com.dbsh.skup.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbsh.skup.client.PortalClient;
import com.dbsh.skup.api.PortalApi;
import com.dbsh.skup.dto.RequestGraduateBasicData;
import com.dbsh.skup.dto.RequestGraduateBasicParameterData;
import com.dbsh.skup.dto.RequestGraduateSubjectData;
import com.dbsh.skup.dto.RequestGraduateSubjectParameterData;
import com.dbsh.skup.dto.ResponseGraduateBasic;
import com.dbsh.skup.dto.ResponseGraduateBasicMap;
import com.dbsh.skup.dto.ResponseGraduateSubject;
import com.dbsh.skup.dto.ResponseGraduateSubjectList;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class GraduateSubjectViewModel extends ViewModel {
    public MutableLiveData<ResponseGraduateBasicMap> graduateBasicLiveData = new MutableLiveData<>();
    public MutableLiveData<ArrayList<ResponseGraduateSubjectList>> graduateSubjectLiveData = new MutableLiveData<>();
    public PortalApi portalApi;

    public void getGraduateBasic(String token, String id) {
        PortalClient portalClient = PortalClient.getInstance(token);
        portalApi = PortalClient.getPortalApi();
        RequestGraduateBasicParameterData parameter = new RequestGraduateBasicParameterData(id, id);
        portalApi.getGraduateBasicData(new RequestGraduateBasicData(
                "education.ugd.UGD_03002_T.SELECT",
                "AL",
                token,
                "common/selectOne",
                "UGD_03002_T",
                id,
                parameter)).enqueue(new Callback<ResponseGraduateBasic>() {
            @Override
            public void onResponse(Call<ResponseGraduateBasic> call, Response<ResponseGraduateBasic> response) {
                // 졸업생의 경우 response.body() == null
                // System.out.println(response.body());
                if (response.isSuccessful()) {
                    if (response.body().getRtnStatus().equals("S")) {
                        ResponseGraduateBasicMap responseGraduateBasicMap = response.body().getResponseGraduateBasicMap();
                        graduateBasicLiveData.setValue(responseGraduateBasicMap);
                    }
                }
            }
            @Override
            public void onFailure(Call<ResponseGraduateBasic> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
    }

    public void getGraduateSubject(String token, String id) {
        PortalClient portalClient = PortalClient.getInstance(token);
        portalApi = PortalClient.getPortalApi();
        RequestGraduateSubjectParameterData parameter = new RequestGraduateSubjectParameterData(id);
        portalApi.getGraduateSubjectData(new RequestGraduateSubjectData(
                "education.ugd.UGD_03031_T.SELECT_UCS_AREASUBJECT",
                "AL",
                token,
                "common/selectList",
                "UGD_03031_T",
                id,
                parameter)).enqueue(new Callback<ResponseGraduateSubject>() {
            @Override
            public void onResponse(Call<ResponseGraduateSubject> call, Response<ResponseGraduateSubject> response) {
                // 졸업생의 경우 response.body() == null
                // System.out.println(response.body());
                if (response.isSuccessful()) {
                    if (response.body().getRtnStatus().equals("S")) {
						graduateSubjectLiveData.setValue(response.body().getResponseGraduateSubjectLists());
                    }
                }
            }

            @Override
            public void onFailure(Call<ResponseGraduateSubject> call, Throwable t) {
                Log.d("Failure", t.getLocalizedMessage());
            }
        });
    }
}
