package com.kirill.earthquake.ui.adapters;

import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.kirill.earthquake.R;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.utils.TimeUtils;

import java.util.Date;
import java.util.List;

public class EarthquakesRecyclerAdapter extends RecyclerView.Adapter<EarthquakesRecyclerAdapter.ViewHolder> {
    private List<Earthquake> mData;

    public void setData(List<Earthquake> mData) {
        this.mData = mData;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public TextView mTitleTextView, mTimeTextView, mMagnitudeTextView;
        public ViewHolder(View view) {
            super(view);
            mTitleTextView = view.findViewById(R.id.text_view_title);
            mTimeTextView = view.findViewById(R.id.text_view_time);
            mMagnitudeTextView = view.findViewById(R.id.text_view_magnitude);
        }
    }

    public EarthquakesRecyclerAdapter(List<Earthquake> data) {
        mData = data;
    }

    @Override
    public EarthquakesRecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent,
                                                               int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.eaarthquakes_list_item, parent,false);
        EarthquakesRecyclerAdapter.ViewHolder vh = new EarthquakesRecyclerAdapter.ViewHolder(view);
        return vh;
    }

    @Override
    public void onBindViewHolder(EarthquakesRecyclerAdapter.ViewHolder holder, int position) {
        holder.mTitleTextView.setText(mData.get(position).getProperties().getPlace());
        holder.mTimeTextView.setText(TimeUtils.getTimeIso(new Date(mData.get(position).getProperties().getTime())) + "");
        holder.mMagnitudeTextView.setText(mData.get(position).getProperties().getMagnitude());
    }


    @Override
    public int getItemCount() {

        return mData.size();
    }
}
