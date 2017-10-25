package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import javafx.collections.ObservableList;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;

public class StatusTableCell extends CenteredTextTableCell<FlatExcerpt, ObservableList<String>> {

    @Override
    protected String getTextFill(ObservableList<String> inconsistency) {
        if (inconsistency.isEmpty()) {
            return "✔";
        } else {
            return String.join("\n", inconsistency);
        }
    }

    @Override
    protected boolean isDangerStyle(ObservableList<String> inconsistency) {
        return !inconsistency.isEmpty();
    }

    @Override
    protected boolean isSuccessStyle(ObservableList<String> inconsistency) {
        return inconsistency.isEmpty();
    }

}
