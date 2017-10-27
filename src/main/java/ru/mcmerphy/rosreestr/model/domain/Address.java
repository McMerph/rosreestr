package ru.mcmerphy.rosreestr.model.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.NO_CONTENT;
import static ru.mcmerphy.rosreestr.controllers.util.Formatter.format;

/**
 * Represents {@link Flat} address.
 * Holds region, locality, street, building and {@link Apartment} instance.
 */
public class Address {

    private String region;

    /**
     * Optional district.
     */
    private District district = new District();

    private Locality locality = new Locality();
    private Street street = new Street();
    private Building building = new Building();
    private Apartment apartment = new Apartment();

    /**
     * @return user-friendly building representation.
     */
    public String getBuildingRepresentation() {
        return format(" ",
                getDistrictRepresentation(),
                locality.getRepresentation(),
                street.getRepresentation(),
                building.getRepresentation()
        );
    }

    /**
     * @return user-friendly representation.
     */
    public String getRepresentation() {
        if (isEmpty()) {
            return NO_CONTENT;
        } else {
            return String.join("\n",
                    format(" ", getDistrictRepresentation()),
                    locality.getRepresentation(),
                    format(" ",
                            street.getRepresentation(),
                            building.getRepresentation(),
                            apartment.getRepresentation())
            );
        }
    }

    /**
     * @return inconsistency messages.
     */
    public List<String> getInconsistency() {
        List<String> inconsistency = new ArrayList<>();
        if (Objects.isNull(region)) {
            inconsistency.add("Регион не задан");
        }
        if (Objects.isNull(locality)) {
            inconsistency.add("Город не задан");
        }
        if (Objects.isNull(street)) {
            inconsistency.add("Улица не задана");
        }
        if (Objects.isNull(building)) {
            inconsistency.add("Здание не задано");
        }
        inconsistency.addAll(apartment.getInconsistency());

        return inconsistency;
    }

    /**
     * @return true if consistent.
     */
    public boolean isConsistent() {
        return getInconsistency().isEmpty();
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public District getDistrict() {
        return district;
    }

    public void setDistrict(District district) {
        this.district = district;
    }

    public Locality getLocality() {
        return locality;
    }

    public void setLocality(Locality locality) {
        this.locality = locality;
    }

    public Street getStreet() {
        return street;
    }

    public void setStreet(Street street) {
        this.street = street;
    }

    public Building getBuilding() {
        return building;
    }

    public void setBuilding(Building building) {
        this.building = building;
    }

    public Apartment getApartment() {
        return apartment;
    }

    public void setApartment(Apartment apartment) {
        this.apartment = apartment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Address address = (Address) o;

        if (region != null ? !region.equals(address.region) : address.region != null) return false;
        if (district != null ? !district.equals(address.district) : address.district != null)
            return false;
        if (locality != null ? !locality.equals(address.locality) : address.locality != null) return false;
        if (street != null ? !street.equals(address.street) : address.street != null) return false;
        if (building != null ? !building.equals(address.building) : address.building != null) return false;
        return apartment != null ? apartment.equals(address.apartment) : address.apartment == null;
    }

    @Override
    public int hashCode() {
        int result = region != null ? region.hashCode() : 0;
        result = 31 * result + (district != null ? district.hashCode() : 0);
        result = 31 * result + (locality != null ? locality.hashCode() : 0);
        result = 31 * result + (street != null ? street.hashCode() : 0);
        result = 31 * result + (building != null ? building.hashCode() : 0);
        result = 31 * result + (apartment != null ? apartment.hashCode() : 0);
        return result;
    }

    /**
     * @return true if has no defined properties.
     */
    private boolean isEmpty() {
        return Objects.isNull(region)
                && Objects.isNull(locality)
                && Objects.isNull(street)
                && Objects.isNull(building)
                && apartment.isEmpty();
    }

    private String getDistrictRepresentation() {
        String representation;
        if (Objects.isNull(region)) {
            representation = NO_CONTENT;
        } else {
            representation = region.equals("27") ? "Хабаровский край" : region + " регион";
        }

        if (!district.isEmpty()) {
            representation = String.join(" ", representation, district.getRepresentation());
        }
        return representation;
    }

}
