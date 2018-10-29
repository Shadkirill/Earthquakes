package com.kirill.earthquake.mvp.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.greenrobot.greendao.annotation.Generated;

@Entity
public class EarthquakeDatabasePresentation {
    @Id
    private Long id;
    //earthquake

    private String type;

    //geometry
    @Convert(converter = GreenConverter.class, columnType = String.class)
    private List<String> coordinates = null;

    //property

    private String magnitude;

    private String place;

    private long time;

    @Generated(hash = 1960669822)
    public EarthquakeDatabasePresentation(Long id, String type, List<String> coordinates,
            String magnitude, String place, long time) {
        this.id = id;
        this.type = type;
        this.coordinates = coordinates;
        this.magnitude = magnitude;
        this.place = place;
        this.time = time;
    }

    @Generated(hash = 1727544501)
    public EarthquakeDatabasePresentation() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<String> getCoordinates() {
        return this.coordinates;
    }

    public void setCoordinates(List<String> coordinates) {
        this.coordinates = coordinates;
    }

    public String getMagnitude() {
        return this.magnitude;
    }

    public void setMagnitude(String magnitude) {
        this.magnitude = magnitude;
    }

    public String getPlace() {
        return this.place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public long getTime() {
        return this.time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public static class GreenConverter implements PropertyConverter<List<String>, String> {


        @Override
        public List<String> convertToEntityProperty(String databaseValue) {
            if (databaseValue == null) {
                return null;
            }
            else {
                List<String> lista = Arrays.asList(databaseValue.split(","));
                return lista;
            }
        }

        @Override
        public String convertToDatabaseValue(List<String> entityProperty) {
            if(entityProperty==null){
                return null;
            }
            else{
                StringBuilder sb= new StringBuilder();
                for(String link : (List<String>)entityProperty){
                    sb.append(link + "");
                    sb.append(",");
                }
                return sb.toString();
            }
        }
    }





}
