package com.example.caspian.taskmanager;

import android.net.Uri;

import org.greenrobot.greendao.converter.PropertyConverter;

public class UriConverter implements PropertyConverter<Uri,String> {
    @Override
    public Uri convertToEntityProperty(String databaseValue) {
        return Uri.parse(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(Uri entityProperty) {
        return entityProperty.toString();
    }
}
