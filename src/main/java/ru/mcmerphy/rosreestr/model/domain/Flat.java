package ru.mcmerphy.rosreestr.model.domain;

import javafx.beans.binding.ListBinding;
import javafx.beans.property.ListProperty;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleListProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Represents flat. Holds total area, {@link Address} instance and list of {@link Right}'s instances.
 */
public class Flat {

    private final ObjectProperty<BigDecimal> totalArea = new SimpleObjectProperty<>();
    private final ObjectProperty<Address> address = new SimpleObjectProperty<>(new Address());
    private final ListProperty<Right> rights = new SimpleListProperty<>(FXCollections.observableArrayList());

    private final ListProperty<String> inconsistency = new SimpleListProperty<>(FXCollections.observableArrayList());

    public Flat() {
        inconsistency.bind(new ListBinding<String>() {

            {
                super.bind(totalArea, address, rights);
            }

            @Override
            protected ObservableList<String> computeValue() {
                List<String> inconsistency = new ArrayList<>();
                if (Objects.isNull(totalArea.get())) {
                    inconsistency.add("Общая площадь не задана");
                }
                inconsistency.addAll(address.get().getInconsistency());
                if (rights.get().isEmpty()) {
                    inconsistency.add("Права отсутствуют");
                }
                rights.forEach(right -> inconsistency.addAll(right.getInconsistency()));

                return FXCollections.observableArrayList(inconsistency);
            }

        });
    }

    /**
     * @return true if consistent.
     */
    public boolean isConsistent() {
        return inconsistency.get().isEmpty();
    }

    public BigDecimal getTotalArea() {
        return totalArea.get();
    }

    public ObjectProperty<BigDecimal> totalAreaProperty() {
        return totalArea;
    }

    public void setTotalArea(BigDecimal totalArea) {
        this.totalArea.set(totalArea);
    }

    public Address getAddress() {
        return address.get();
    }

    public ObjectProperty<Address> addressProperty() {
        return address;
    }

    public void setAddress(Address address) {
        this.address.set(address);
    }

    public ObservableList<Right> getRights() {
        return rights.get();
    }

    public ListProperty<Right> rightsProperty() {
        return rights;
    }

    public void setRights(ObservableList<Right> rights) {
        this.rights.set(rights);
    }

    public ObservableList<String> getInconsistency() {
        return inconsistency.get();
    }

    public ListProperty<String> inconsistencyProperty() {
        return inconsistency;
    }

    public void setInconsistency(ObservableList<String> inconsistency) {
        this.inconsistency.set(inconsistency);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BigDecimal totalArea = this.totalArea.get();
        Address address = this.address.get();
        ObservableList<Right> rights = this.rights.get();

        Flat flat = (Flat) o;
        BigDecimal otherTotalArea = flat.getTotalArea();
        Address otherAddress = flat.getAddress();
        ObservableList<Right> otherRights = flat.getRights();

        if (totalArea != null ? !totalArea.equals(otherTotalArea) : otherTotalArea != null) return false;
        if (address != null ? !address.equals(otherAddress) : otherAddress != null) return false;
        return rights != null ? rights.equals(otherRights) : otherRights == null;
    }

    @Override
    public int hashCode() {
        BigDecimal totalArea = this.totalArea.get();
        Address address = this.address.get();
        ObservableList<Right> rights = this.rights.get();

        int result = totalArea != null ? totalArea.hashCode() : 0;
        result = 31 * result + (address != null ? address.hashCode() : 0);
        result = 31 * result + (rights != null ? rights.hashCode() : 0);
        return result;
    }

}
