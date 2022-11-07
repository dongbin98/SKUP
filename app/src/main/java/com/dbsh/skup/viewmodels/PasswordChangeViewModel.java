package com.dbsh.skup.viewmodels;

import android.widget.EditText;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.dbsh.skup.R;
import com.dbsh.skup.api.LoginApi;
import com.dbsh.skup.model.RequestPasswordChangeData;
import com.dbsh.skup.model.ResponsePassword;
import com.dbsh.skup.repository.LoginRepository;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PasswordChangeViewModel extends ViewModel {

    public LoginApi loginApi;
    public MutableLiveData<String> changeState = new MutableLiveData<>();

    public EditText.OnFocusChangeListener editTextFocusListener() {
        return (v, hasFocus) -> {
            if (hasFocus)
                v.setBackgroundResource(R.drawable.edittext_white_focused_background);
            else
                v.setBackgroundResource(R.drawable.edittext_white_background);
        };
    }

    public void getPasswordChangeData(String id, String pw, String pw2, String code) {
        LoginRepository loginRepository = new LoginRepository();
        loginApi = loginRepository.getLoginApi();
        loginApi.getPasswordChange(new RequestPasswordChangeData(id, pw, pw2, code, "sku")).enqueue(new Callback<ResponsePassword>() {
            @Override
            public void onResponse(Call<ResponsePassword> call, Response<ResponsePassword> response) {
                if (response.isSuccessful()) {
                    if (response.body().getRtnStatus().equals("S")) {
                        changeState.setValue("S");
                    } else {
                        changeState.setValue("F");
                    }
                } else {
                    changeState.setValue("F");
                }
            }

            @Override
            public void onFailure(Call<ResponsePassword> call, Throwable t) {
                changeState.setValue("N");
            }
        });
    }
}
