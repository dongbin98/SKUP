package com.dbsh.skup.views;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;

import com.dbsh.skup.R;
import com.dbsh.skup.databinding.HomeCenterContainerBinding;
import com.dbsh.skup.viewmodels.HomeCenterContainerViewModel;

import java.util.List;

public class HomeCenterContainer extends Fragment implements OnBackPressedListener{

    private HomeCenterContainerBinding binding;
    private HomeCenterContainerViewModel viewModel;
	private long time = 0;

    private static final String noticeUrl = "https://skuniv.ac.kr/notice";
    private static final String majorNoticeUrl = "https://ce.skuniv.ac.kr/ce_notice";

	// this Fragment
	private Fragment HomeCenterFragment;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        /* Data Binding */
        binding = DataBindingUtil.inflate(inflater, R.layout.home_center_container, container, false);
        viewModel = new HomeCenterContainerViewModel();
        binding.setViewModel(viewModel);
        binding.executePendingBindings();

		HomeCenterFragment = new HomeCenterFragment();
		getChildFragmentManager().beginTransaction().add(binding.homeCenterContainer.getId(), HomeCenterFragment).commit();

        return binding.getRoot();
    }

	public void replaceFragment(Fragment afterFragment, Bundle bundle) {
		if (bundle != null) {
			afterFragment.setArguments(bundle);
		}
		if (!afterFragment.isAdded()) {
			getChildFragmentManager().beginTransaction().add(binding.homeCenterContainer.getId(), afterFragment).commit();
		} else {
			getChildFragmentManager().beginTransaction().replace(binding.homeCenterContainer.getId(), afterFragment).commit();
		}
		getChildFragmentManager().beginTransaction().show(afterFragment).addToBackStack(null).commit();
	}

	@Override
	public void onBackPressed() {
		List<Fragment> childFragments = getChildFragmentManager().getFragments();
		for(int i = childFragments.size()-1; i >= 0; i--) {
			if(childFragments.get(i) instanceof OnBackPressedListener) {
				((OnBackPressedListener) childFragments.get(i)).onBackPressed();
				return;
			}
		}
		if (System.currentTimeMillis() - time >= 2000) {
			time = System.currentTimeMillis();
			Toast.makeText(getContext(), "한번 더 누르면 로그인창으로 이동합니다.", Toast.LENGTH_SHORT).show();
		} else if (System.currentTimeMillis() - time < 2000) {
			((HomeActivity)getActivity()).finish();
		}
	}

	@Override
	public void onAttach(@NonNull Context context) {
		super.onAttach(context);
		((HomeActivity)context).setOnBackPressedListner(this);
	}
}