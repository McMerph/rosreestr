package ru.mcmerphy.rosreestr.model.domain;

/**
 * Represents {@link Address} building.
 * Holds type and value of apartment. e.g. Type = "house" Value = "42".
 */
public class Building extends TypeValueTuple {

    public Building() {
    }

    public Building(String type, String value) {
        super(type, value);
    }

    @Override
    public String getNoTypeMessage() {
        return "Тип здания не задан";
    }

    @Override
    public String getNoValueMessage() {
        return "Значение здания не задано";
    }

}
