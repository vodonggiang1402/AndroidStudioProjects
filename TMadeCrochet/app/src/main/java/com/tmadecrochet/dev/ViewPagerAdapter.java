package com.tmadecrochet.dev;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class ViewPagerAdapter extends FragmentStateAdapter {
    public ViewPagerAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @Override
    public Fragment createFragment(int position) {
        switch (position) {
            case 0: return new TabSymbol();
            case 1: return new TabCounter();
            case 2: return new TabTutorial();
            case 3: return new TabSetting();
            default: return new TabSymbol();
        }
    }

    @Override
    public int getItemCount() {
        return 4;
    }
}
