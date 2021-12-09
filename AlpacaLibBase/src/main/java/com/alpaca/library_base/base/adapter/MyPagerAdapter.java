package com.alpaca.library_base.base.adapter;


import com.alpaca.library_base.base.fragment.MvpBaseFragment;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.viewpager.widget.PagerAdapter;

public class MyPagerAdapter extends FragmentStatePagerAdapter {
    private ArrayList<MvpBaseFragment> fragments = null;
    private List<String> title = new ArrayList<>();

    public MyPagerAdapter(FragmentManager fm, ArrayList<MvpBaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    public MyPagerAdapter(FragmentManager fm, ArrayList<MvpBaseFragment> fragments, List<String> title) {
        super(fm);
        this.fragments = fragments;
        this.title = title;
    }


    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return title.get(position);
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return PagerAdapter.POSITION_NONE;
    }
}