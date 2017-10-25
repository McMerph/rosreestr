package ru.mcmerphy.rosreestr.model.domain;

import java.util.Objects;

/**
 * Represents {@link Address} apartment.
 * Holds type and value of apartment. e.g. Type = "room" Value = "42".
 */
public class Apartment extends TypeValueTuple {

    public Apartment() {
    }

    public Apartment(String type, String value) {
        super(type, value);
    }

    @Override
    public String getNoTypeMessage() {
        return "Тип апартаментов не задан";
    }

    @Override
    public String getNoValueMessage() {
        return "Значение апартаментов не задано";
    }

    /**
     * @return <tt>true</tt> if apartment is regular house room.
     */
    public boolean isRegularHouseRoom() {
        return Objects.equals(type, "кв") || Objects.equals(type, "к");
    }

}
