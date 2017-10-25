package ru.mcmerphy.rosreestr.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.format;

public abstract class TypeValueTuple {

    protected String type;
    protected String value;

    public TypeValueTuple() {
    }

    public TypeValueTuple(String type, String value) {
        this.type = type;
        this.value = value;
    }

    public abstract String getNoTypeMessage();

    public abstract String getNoValueMessage();

    /**
     * @return inconsistency messages.
     */
    public List<String> getInconsistency() {
        List<String> inconsistency = new ArrayList<>();
        if (Objects.isNull(type)) {
            inconsistency.add(getNoTypeMessage());
        }
        if (Objects.isNull(value)) {
            inconsistency.add(getNoValueMessage());
        }

        return inconsistency;
    }

    /**
     * @return <tt>true</tt> if has no defined properties.
     */
    public boolean isEmpty() {
        return Objects.isNull(type) && Objects.isNull(value);
    }

    /**
     * @return user-friendly representation.
     */
    public String getRepresentation() {
        return format(". ", type, value);
    }


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TypeValueTuple typeValueTuple = (TypeValueTuple) o;

        if (type != null ? !type.equals(typeValueTuple.type) : typeValueTuple.type != null) return false;
        return value != null ? value.equals(typeValueTuple.value) : typeValueTuple.value == null;
    }

    @Override
    public int hashCode() {
        int result = type != null ? type.hashCode() : 0;
        result = 31 * result + (value != null ? value.hashCode() : 0);
        return result;
    }

}
