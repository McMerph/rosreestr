package ru.mcmerphy.rosreestr.model.domain;

/**
 * Represents {@link Address} street.
 * Holds type and value of apartment. e.g. Type = "avenue" Value = "5".
 */
public class Street extends TypeValueTuple {

    public Street() {
    }

    public Street(String type, String value) {
        super(type, value);
    }

    @Override
    public String getNoTypeMessage() {
        return "Тип улицы не задан";
    }

    @Override
    public String getNoValueMessage() {
        return "Значение улицы не задано";
    }

}
