package com.dbsh.skup.adapter;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.dbsh.skup.views.GraduateNoneSubjectFragment;
import com.dbsh.skup.views.GraduateSubjectFragment;

public class GraduatePagerAdapter extends FragmentStateAdapter {

    public int mCount;
    public GraduatePagerAdapter(FragmentActivity fa, int count) {
        super(fa);
        mCount = count;
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        if (getRealPosition(position) == 0) {
            GraduateSubjectFragment fg = new GraduateSubjectFragment();
            return fg;
        } else {
            GraduateNoneSubjectFragment fg = new GraduateNoneSubjectFragment();
            return fg;
        }
    }

    @Override
    public int getItemCount() {
        return 2;
    }

    public int getRealPosition(int position) {
        return position % mCount;
    }
}
