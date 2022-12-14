package com.dbsh.skup.views;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.app.ActivityCompat;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;

import com.dbsh.skup.R;
import com.dbsh.skup.databinding.SplashFormBinding;
import com.dbsh.skup.dto.ResponseStationItem;
import com.dbsh.skup.viewmodels.SplashViewModel;

import java.io.File;
import java.io.IOException;
import java.util.List;


public class SplashActivity extends AppCompatActivity {
    private static final int PERMISSION_REQUEST_CODE = 22;
    String[] permissions = {Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};
    Animation animFadeIn;
    ConstraintLayout constraintLayout;

	SplashFormBinding binding;
	SplashViewModel viewModel;

	// for Json File
    final String file1164 = "1164.json";
    final String file2115 = "2115.json";
    int routeUpdate;
    boolean fileExist = false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

	    /* DataBinding */
	    binding = DataBindingUtil.setContentView(this, R.layout.splash_form);
	    binding.setLifecycleOwner(this);
	    viewModel = new SplashViewModel(getApplication());
	    binding.setViewModel(viewModel);
	    binding.executePendingBindings();	// 바인딩 강제 즉시실행

        constraintLayout = binding.splashLayout;
        animFadeIn = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.anim_splash_fadein);
        animFadeIn.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                if(hasPermissions(permissions) && fileExist) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                } else {
                    checkPermissions();
                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });

        routeUpdate = 0;

        File f1164 = new File(getFilesDir(), file1164);
        File f2115 = new File(getFilesDir(), file2115);

        if(!f1164.exists() && !f2115.exists()) {
            viewModel.getStaton1164();
            viewModel.getStaton2115();
        } else if(!f1164.exists() && f2115.exists()) {
            routeUpdate++;
            viewModel.getStaton1164();
        } else if(f1164.exists() && !f2115.exists()) {
            routeUpdate++;
            viewModel.getStaton2115();
        } else {
            fileExist = true;
        }
        constraintLayout.startAnimation(animFadeIn);

        viewModel.station1164.observe(binding.getLifecycleOwner(), new Observer<List<ResponseStationItem>>() {
            @Override
            public void onChanged(List<ResponseStationItem> responseStationItems) {
                if(responseStationItems == null) {
                    Toast.makeText(getApplicationContext(), "1164번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        viewModel.write1164File(viewModel.makeJson(responseStationItems, "1164"));
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "1164번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewModel.station2115.observe(binding.getLifecycleOwner(), new Observer<List<ResponseStationItem>>() {
            @Override
            public void onChanged(List<ResponseStationItem> responseStationItems) {
                if(responseStationItems == null) {
                    Toast.makeText(getApplicationContext(), "2115번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                } else {
                    try {
                        viewModel.write2115File(viewModel.makeJson(responseStationItems, "2115"));
                    } catch (IOException e) {
                        Toast.makeText(getApplicationContext(), "2115번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        viewModel.file1164State.observe(binding.getLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                routeUpdate++;
                if(s.equals("F")) {
                    Toast.makeText(getApplicationContext(), "1164번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                }
                if(routeUpdate == 2) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        });

        viewModel.file2115State.observe(binding.getLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(String s) {
                routeUpdate++;
                if(s.equals("F")) {
                    Toast.makeText(getApplicationContext(), "2115번 노선정보를 불러오지 못했습니다 마이페이지를 통해 갱신해주세요", Toast.LENGTH_SHORT).show();
                }
                if(routeUpdate == 2) {
                    startActivity(new Intent(SplashActivity.this, MainActivity.class));
                }
            }
        });
    }

    public boolean checkPermissions() {
        boolean permissionGranted = false;

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            permissionGranted = hasPermissions(permissions);

            if(!permissionGranted) {
                ActivityCompat.requestPermissions(this, permissions, PERMISSION_REQUEST_CODE);
            }
        } else {
            permissionGranted = true;
        }
        return permissionGranted;
    }

    public boolean hasPermissions(String[] permissions) {
        // 필수 권한을 가지고 있는지 확인한다.
        for (String permission : permissions) {
            if (checkCallingOrSelfPermission(permission) != PackageManager.PERMISSION_GRANTED) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if(requestCode == PERMISSION_REQUEST_CODE) {
            boolean checkFlag = false;
            for(int g : grantResults) {
                if (g == -1) {
                    checkFlag = true;
                    break;
                }
            }
        }
        if(fileExist) {
            startActivity(new Intent(SplashActivity.this, MainActivity.class));
        }
    }
}
