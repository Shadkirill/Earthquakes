package com.kirill.earthquake.utils;

import com.kirill.earthquake.mvp.models.Earthquake;
import com.kirill.earthquake.mvp.models.EarthquakeDatabasePresentation;
import com.kirill.earthquake.mvp.models.Geometry;
import com.kirill.earthquake.mvp.models.Properties;

import java.util.ArrayList;
import java.util.List;

public class ConvertUtils {
    public static final Earthquake fromPresentation(EarthquakeDatabasePresentation presentation) {
        Earthquake earthquake = new Earthquake();
        Geometry geometry = new Geometry();
        Properties properties = new Properties();
        geometry.setCoordinates(presentation.getCoordinates());
        properties.setTime(presentation.getTime());
        properties.setMagnitude(presentation.getMagnitude());
        properties.setPlace(presentation.getPlace());
        earthquake.setType(presentation.getType());
        earthquake.setGeometry(geometry);
        earthquake.setProperties(properties);
        return earthquake;
    }

    public static final List<Earthquake> fromPresentations(List<EarthquakeDatabasePresentation> presentations) {
        List<Earthquake> earthquakes = new ArrayList<>();
        if (presentations != null) {
            for (EarthquakeDatabasePresentation presentation: presentations) {
                earthquakes.add(fromPresentation(presentation));
            }
        }
        return earthquakes;
    }


    public static final EarthquakeDatabasePresentation fromEarthQuake(Earthquake earthquake) {
        EarthquakeDatabasePresentation presentation = new EarthquakeDatabasePresentation();
        presentation.setCoordinates(earthquake.getGeometry().getCoordinates());
        presentation.setMagnitude(earthquake.getProperties().getMagnitude());
        presentation.setPlace(earthquake.getProperties().getPlace());
        presentation.setType(earthquake.getType());
        presentation.setTime(earthquake.getProperties().getTime());
        return presentation;
    }

    public static final List<EarthquakeDatabasePresentation> fromEarthQuake(List<Earthquake> earthquakes) {
        List<EarthquakeDatabasePresentation> presentatioins = new ArrayList<>();
        if (earthquakes != null) {
            for (Earthquake earthquake: earthquakes) {
                presentatioins.add(fromEarthQuake(earthquake));
            }
        }
        return presentatioins;
    }
}
