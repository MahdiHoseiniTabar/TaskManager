package com.example.caspian.taskmanager.model;

import org.greenrobot.greendao.converter.PropertyConverter;

import java.util.UUID;

public class UUIDConverter implements PropertyConverter<UUID,String> {
    @Override
    public UUID convertToEntityProperty(String databaseValue) {
        return UUID.fromString(databaseValue);
    }

    @Override
    public String convertToDatabaseValue(UUID entityProperty) {
        return entityProperty.toString();
    }
}
