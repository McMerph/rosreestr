package ru.mcmerphy.rosreestr.controllers.util;

import javafx.collections.ObservableList;
import ru.mcmerphy.rosreestr.model.domain.*;

import java.util.Collection;
import java.util.Comparator;

/**
 * Holds comparators.
 */
public class Comparators {

    public static final AlphanumComparator ALPANUM_COMPARATOR = new AlphanumComparator();

    private static final Comparator<Address> ADDRESS_COMPARATOR_BY_APARTMENT = Comparator
            .<Address, Boolean>comparing(
                    address -> address.getApartment().isRegularHouseRoom(),
                    (isHouseRoom1, isHouseRoom2) -> Boolean.compare(isHouseRoom2, isHouseRoom1))
            .thenComparing(
                    address -> address.getApartment().getValue(),
                    ALPANUM_COMPARATOR);

    /**
     * Compare inconsistency messages by size.
     */
    public static final Comparator<ObservableList<String>> INCONSISTENCY_COMPARATOR =
            Comparator.<ObservableList<String>>comparingInt(Collection::size).reversed();

    /**
     * Compare rights by size.
     */
    public static final Comparator<ObservableList<Right>> RIGHTS_COMPARATOR =
            Comparator.comparingInt(Collection::size);

    /**
     * Compare address consequently by region, {@link Locality}, {@link Street},
     * {@link Building}, {@link Apartment}.
     */
    public static final Comparator<Address> ADDRESS_COMPARATOR = Comparator
            .comparing(Address::getRegion, Comparator.nullsFirst(ALPANUM_COMPARATOR))
            .thenComparing(
                    address -> address.getLocality().getType(),
                    Comparator.nullsFirst(String::compareToIgnoreCase))
            .thenComparing(
                    address -> address.getLocality().getValue(),
                    Comparator.nullsFirst(ALPANUM_COMPARATOR))
            .thenComparing(
                    address -> address.getStreet().getType(),
                    Comparator.nullsFirst(String::compareToIgnoreCase))
            .thenComparing(
                    address -> address.getStreet().getValue(),
                    Comparator.nullsFirst(ALPANUM_COMPARATOR))
            .thenComparing(
                    address -> address.getBuilding().getType(),
                    Comparator.nullsFirst(String::compareToIgnoreCase))
            .thenComparing(
                    address -> address.getBuilding().getValue(),
                    Comparator.nullsFirst(ALPANUM_COMPARATOR))
            .thenComparing(ADDRESS_COMPARATOR_BY_APARTMENT);

    /**
     * Compare flats by {@link Apartment}.
     */
    public static final Comparator<Flat> FLAT_COMPARATOR_BY_APARTMENT = Comparator
            .comparing(Flat::getAddress, ADDRESS_COMPARATOR_BY_APARTMENT);

}
