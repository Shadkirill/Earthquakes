package com.kirill.earthquake.mvp.presenters;

import android.os.AsyncTask;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.kirill.earthquake.AppCore;
import com.kirill.earthquake.mvp.models.DaoSession;
import com.kirill.earthquake.mvp.models.EarthquakeDatabasePresentation;
import com.kirill.earthquake.mvp.models.EarthquakeDatabasePresentationDao;
import com.kirill.earthquake.utils.ConvertUtils;
import com.kirill.earthquake.utils.EarthquakeApiService;
import com.kirill.earthquake.mvp.models.ApiResponse;
import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.views.EarthquakesLoadView;
import com.kirill.earthquake.utils.TimeUtils;

import java.util.List;

import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

@InjectViewState
public class EarthquakesLoadPresenter extends MvpPresenter<EarthquakesLoadView> {
    EarthquakeApiService mApiService;



    public EarthquakesLoadPresenter() {
        getViewState().earthquakesLoading();
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(EarthquakeApiService.base_url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        mApiService = retrofit.create(EarthquakeApiService.class);

        onUpdate();
    }

    public void onUpdate() {
        getViewState().earthquakesLoading();
        AsyncTask<Void, Void, List<Earthquake>> asyncTask = new AsyncTask<Void, Void, List<Earthquake>>() {
            @Override
            protected List<Earthquake> doInBackground(Void... voids) {
                List<Earthquake> earthquakes = null;
                try {
                    String nowTime = TimeUtils.getCurrentTimeIso();
                    String middleNightTime = TimeUtils.getTodayMiddleNightTimeIso();
                    Response<ApiResponse> response = mApiService.getEarthQuakes(middleNightTime, nowTime).execute();
                    earthquakes = response.body().getEarthquakes();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                DaoSession daoSession = AppCore.getDaoSession();
                EarthquakeDatabasePresentationDao databasePresentationDao = daoSession.getEarthquakeDatabasePresentationDao();
                if (earthquakes != null && earthquakes.size() > 0) {
                    databasePresentationDao.deleteAll();
                    databasePresentationDao.insertInTx(ConvertUtils.fromEarthQuake(earthquakes));
                    return earthquakes;
                } else {
                    List<EarthquakeDatabasePresentation> databasePresentations = databasePresentationDao.loadAll();
                    return ConvertUtils.fromPresentations(databasePresentations);
                }
            }

            @Override
            protected void onPostExecute(List<Earthquake> earthquakes) {
                if (earthquakes != null  && earthquakes.size() > 0) {
                    getViewState().earthquakesLoaded(earthquakes);
                } else {
                    getViewState().loaddingError();
                }
            }


        };
        asyncTask.execute();
    }

}
