package com.kirill.earthquake.ui.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.ui.fragments.EarthquakesListFragment;
import com.kirill.earthquake.ui.fragments.EarthquakesMapFragment;

import java.util.List;

public class EarthquakesFragmentAdapter extends FragmentPagerAdapter {

    private List<Earthquake> mData;

    public EarthquakesFragmentAdapter(FragmentManager fm, List<Earthquake> mData) {
        super(fm);
        this.mData = mData;
    }

    @Override
    public Fragment getItem(int i) {
        if (i == 0) {
            return EarthquakesListFragment.getInstance(mData);
        } else {
            return EarthquakesMapFragment.getInstance(mData);
        }
    }

    @Override
    public int getCount() {
        return 2;
    }
}
