package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import javafx.beans.property.ReadOnlyDoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;
import ru.mcmerphy.rosreestr.model.domain.Right;

import java.util.Objects;

public class RightsTableCell extends StyledTableCell<FlatExcerpt, ObservableList<Right>> {

    private static final int DENOMINATOR = 4;
    private static final int OWNER_NUMERATOR = 1;
    private static final int REGISTRATION_NUMERATOR = DENOMINATOR - OWNER_NUMERATOR;
    private static final int MARGIN = 15;

    @Override
    protected void updateItem(ObservableList<Right> rights, boolean empty) {
        super.updateItem(rights, empty);
        if (!empty && Objects.nonNull(rights)) {
            GridPane grid = new GridPane();
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(0, 10, 0, 10));

            setColumnConstraints(grid);

            for (int i = 0; i < rights.size(); i++) {
                Right right = rights.get(i);

                Text ownerText = getText(right.getOwnerRepresentation(), OWNER_NUMERATOR);
                ownerText.setTextAlignment(TextAlignment.CENTER);
                grid.add(ownerText, 0, i);

                Text registrationText = getText(right.getRegistrationRepresentation(), REGISTRATION_NUMERATOR);
                grid.add(registrationText, 1, i);
            }

            setGraphic(grid);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

    private void setColumnConstraints(GridPane grid) {
        ReadOnlyDoubleProperty cellWidth = getTableColumn().widthProperty();
        SimpleDoubleProperty boundedCellWidth = new SimpleDoubleProperty();
        boundedCellWidth.bind(cellWidth);

        ColumnConstraints column1 = new ColumnConstraints();
        column1.prefWidthProperty().bind(boundedCellWidth
                .divide(DENOMINATOR)
                .multiply(OWNER_NUMERATOR)
                .subtract(MARGIN));

        ColumnConstraints column2 = new ColumnConstraints();
        column2.prefWidthProperty().bind(boundedCellWidth
                .divide(DENOMINATOR)
                .multiply(REGISTRATION_NUMERATOR)
                .subtract(MARGIN));

        grid.getColumnConstraints().addAll(column1, column2);
    }

    private Text getText(String fill, int numerator) {
        Text text = new Text();
        text.setText(fill);

        ReadOnlyDoubleProperty cellWidth = getTableColumn().widthProperty();
        SimpleDoubleProperty boundedCellWidth = new SimpleDoubleProperty();
        boundedCellWidth.bind(cellWidth);
        text.wrappingWidthProperty().bind(boundedCellWidth
                .divide(DENOMINATOR)
                .multiply(numerator)
                .subtract(MARGIN)
        );

        return text;
    }

}
