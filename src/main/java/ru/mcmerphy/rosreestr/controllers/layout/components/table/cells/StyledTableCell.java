package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import javafx.geometry.Insets;
import javafx.scene.control.TableCell;

public abstract class StyledTableCell<S, T> extends TableCell<S, T> {

    protected boolean isDangerStyle(T item) {
        return false;
    }

    protected boolean isSuccessStyle(T item) {
        return false;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        setPadding(new Insets(6, 12, 6, 12));
        if (!empty) {
            setStyle(item);
        }
    }

    private void setStyle(T item) {
        getStyleClass().clear();
        if (isDangerStyle(item)) {
            getStyleClass().add("danger");
        }
        if (isSuccessStyle(item)) {
            getStyleClass().add("success");
        }
    }

}
