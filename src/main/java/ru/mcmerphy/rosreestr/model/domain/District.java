package ru.mcmerphy.rosreestr.model.domain;

/**
 * Represents {@link Address} district.
 * Holds type and value of district. e.g. Type = "district" Value = "Columbia".
 */
public class District extends TypeValueTuple {

    public District() {
    }

    public District(String type, String value) {
        super(type, value);
    }

    @Override
    public String getNoTypeMessage() {
        return "Тип района не задан";
    }

    @Override
    public String getNoValueMessage() {
        return "Значение района не задано";
    }

}
