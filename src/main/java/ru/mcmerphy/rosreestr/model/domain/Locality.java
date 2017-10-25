package ru.mcmerphy.rosreestr.model.domain;

/**
 * Represents {@link Address} locality.
 * Holds type and value of apartment. e.g. Type = "city" Value = "Washington".
 */
public class Locality extends TypeValueTuple {

    public Locality() {
    }

    public Locality(String type, String value) {
        super(type, value);
    }

    @Override
    public String getNoTypeMessage() {
        return "Тип местности не задан";
    }

    @Override
    public String getNoValueMessage() {
        return "Значение местности не задано";
    }

}
