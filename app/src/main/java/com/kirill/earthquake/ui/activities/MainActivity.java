package com.kirill.earthquake.ui.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.kirill.earthquake.R;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.presenters.EarthquakesLoadPresenter;
import com.kirill.earthquake.mvp.views.EarthquakesLoadView;
import com.kirill.earthquake.ui.adapters.EarthquakesFragmentAdapter;

import java.util.List;

public class MainActivity extends MvpAppCompatActivity implements EarthquakesLoadView {
    @InjectPresenter
    EarthquakesLoadPresenter mPresenter;

    private ProgressBar mProgressBar;
    private ViewPager mViewPager;
    private List<Earthquake> mEarthquakes;
    private EarthquakesFragmentAdapter mEarthquakesFragmentAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mProgressBar = findViewById(R.id.progress_bar);
        mViewPager = findViewById(R.id.view_pager);
    }

    @Override
    public void earthquakesLoading() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void loaddingError() {
        mProgressBar.setVisibility(View.GONE);
        showErrorDialog();
    }

    private void showErrorDialog() {
        AlertDialog updateDialog = new AlertDialog.Builder(this)
                .setTitle("Loading error.")
                .setCancelable(false)
                .setPositiveButton("Try more",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int whichButton) {
                                mPresenter.onUpdate();
                            }
                        }
                )
                .create();
        updateDialog.show();
    }

    @Override
    public void earthquakesLoaded(@NonNull  List<Earthquake> earthquakes) {
        mProgressBar.setVisibility(View.GONE);
        mEarthquakes = earthquakes;
        Toast.makeText(this, earthquakes.size() + "", Toast.LENGTH_SHORT).show();
        showEarthquakes();
    }

    private void showEarthquakes() {
        mEarthquakesFragmentAdapter = new EarthquakesFragmentAdapter(getSupportFragmentManager(), mEarthquakes);
        mViewPager.setAdapter(mEarthquakesFragmentAdapter);
        mEarthquakesFragmentAdapter.notifyDataSetChanged();
        initActionBar();
        mViewPager.setOnPageChangeListener(
                new ViewPager.SimpleOnPageChangeListener() {
                    @Override
                    public void onPageSelected(int position) {
                        // When swiping between pages, select the
                        // corresponding tab.
                        getSupportActionBar().setSelectedNavigationItem(position);
                    }
                });
    }

    private void initActionBar() {
        final ActionBar actionBar = getSupportActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
        ActionBar.TabListener tabListener = new ActionBar.TabListener() {

            @Override
            public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }

            @Override
            public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {

            }
        };
        actionBar.addTab(
                actionBar.newTab()
                        .setText("LIST")
                        .setTabListener(tabListener));
        actionBar.addTab(
                actionBar.newTab()
                        .setText("MAP")
                        .setTabListener(tabListener));
    }
}
