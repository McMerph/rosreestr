package ru.mcmerphy.rosreestr.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents {@link Flat} right.
 * Holds owner and registration.
 */
public class Right {

    private boolean noOwner = false;
    private boolean noRegistration = false;
    private String owner;
    private String registration;

    /**
     * @return inconsistency messages.
     */
    public List<String> getInconsistency() {
        List<String> inconsistency = new ArrayList<>();
        if (Objects.isNull(owner) && !noOwner) {
            inconsistency.add("Правообладатель не задан");
        }
        if (Objects.isNull(registration) && !noRegistration) {
            inconsistency.add("Регистрация не задана");
        }

        return inconsistency;
    }

    /**
     * @return user-friendly owner representation.
     */
    public String getOwnerRepresentation() {
        String representation = "Правообладатель не задан";
        if (noOwner) {
            representation = "Данные о правообладателе отсутствуют";
        }
        if (Objects.nonNull(owner)) {
            representation = getOwner();
        }

        return representation;
    }

    /**
     * @return user-friendly registration representation.
     */
    public String getRegistrationRepresentation() {
        String representation = "Регистрация не задана";
        if (noRegistration) {
            representation = "Не зарегистрировано";
        }
        if (Objects.nonNull(registration)) {
            representation = getRegistration();
        }

        return representation;
    }

    public boolean isNoOwner() {
        return noOwner;
    }

    public void setNoOwner() {
        this.noOwner = true;
    }

    public boolean isNoRegistration() {
        return noRegistration;
    }

    public void setNoRegistration() {
        this.noRegistration = true;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Right right = (Right) o;

        if (noOwner != right.noOwner) return false;
        if (noRegistration != right.noRegistration) return false;
        if (owner != null ? !owner.equals(right.owner) : right.owner != null) return false;
        return registration != null ? registration.equals(right.registration) : right.registration == null;
    }

    @Override
    public int hashCode() {
        int result = (noOwner ? 1 : 0);
        result = 31 * result + (noRegistration ? 1 : 0);
        result = 31 * result + (owner != null ? owner.hashCode() : 0);
        result = 31 * result + (registration != null ? registration.hashCode() : 0);
        return result;
    }

}
