package com.XDev.Sandesh;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import fragment.BlankFragment;
import fragment.Wall_of_college;
import fragment.homefragment;

public class myadapter extends FragmentStateAdapter {

    public myadapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 1:
                return new BlankFragment();
            case 3:
                return new Wall_of_college();
            default:
                return new homefragment();
        }
    }

    @Override
    public int getItemCount() {
        return 3;
    }
}
