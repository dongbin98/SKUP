package com.dbsh.skup.tmpstructure.viewmodels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbsh.skup.tmpstructure.Service.PortalService;
import com.dbsh.skup.tmpstructure.api.PortalApi;
import com.dbsh.skup.tmpstructure.model.RequestTuitionData;
import com.dbsh.skup.tmpstructure.model.RequestTuitionParameterData;
import com.dbsh.skup.tmpstructure.model.ResponseTuition;
import com.dbsh.skup.tmpstructure.model.ResponseTuitionMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TuitionViewModel extends ViewModel {

	public MutableLiveData<ResponseTuitionMap> tuitionLiveData = new MutableLiveData<>();
	public PortalApi portalApi;

	public void getTuition(String token, String id, String year, String term) {
		PortalService portalService = PortalService.getInstance(token);
		portalApi = PortalService.getPortalApi();
		RequestTuitionParameterData parameter = new RequestTuitionParameterData(id, year, term, id);
		portalApi.getTuitionData(new RequestTuitionData(
				"education.urg.URG_02012_V.SELECT",
				"AL",
				token,
				"common/selectOne",
				"URG_02012_V",
				id,
				parameter)).enqueue(new Callback<ResponseTuition>() {
			@Override
			public void onResponse(Call<ResponseTuition> call, Response<ResponseTuition> response) {
				if (response.isSuccessful()) {
					tuitionLiveData.setValue(response.body().getMap());
				}
			}

			@Override
			public void onFailure(Call<ResponseTuition> call, Throwable t) {
				Log.d("Failure", t.getLocalizedMessage());
			}
		});
	}
}
