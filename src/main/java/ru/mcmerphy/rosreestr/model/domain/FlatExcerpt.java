package ru.mcmerphy.rosreestr.model.domain;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

import java.io.File;
import java.util.Objects;

/**
 * Flat excerpt.
 * Includes file and flat associated with each other.
 */
public class FlatExcerpt {

    private File file;
    private final ObjectProperty<Flat> flat;

    /**
     * Constructs a flat excerpt from specified {@link FlatParser}.
     *
     * @param parser {@link FlatParser} to construct from
     */
    public FlatExcerpt(FlatParser parser) {
        file = parser.getFile();
        flat = new SimpleObjectProperty<>(parser.getFlat());
    }

    /**
     * @param file specified file
     * @return true if associated with specified file
     */
    public boolean isSameFile(File file) {
        return Objects.equals(this.file, file);
    }

    public File getFile() {
        return file;
    }

    public void setFile(File file) {
        this.file = file;
    }

    public Flat getFlat() {
        return flat.get();
    }

    public ObjectProperty<Flat> flatProperty() {
        return flat;
    }

    public void setFlat(Flat flat) {
        this.flat.set(flat);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        FlatExcerpt that = (FlatExcerpt) o;

        if (file != null ? !file.equals(that.file) : that.file != null) return false;
        return flat != null ? flat.equals(that.flat) : that.flat == null;
    }

    @Override
    public int hashCode() {
        int result = file != null ? file.hashCode() : 0;
        result = 31 * result + (flat != null ? flat.hashCode() : 0);
        return result;
    }

}
