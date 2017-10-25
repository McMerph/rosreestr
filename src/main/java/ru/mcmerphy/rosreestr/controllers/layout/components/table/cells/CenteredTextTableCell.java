package ru.mcmerphy.rosreestr.controllers.layout.components.table.cells;

import javafx.geometry.Pos;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.util.Formatter.NO_CONTENT;

public abstract class CenteredTextTableCell<S, T> extends StyledTableCell<S, T> {

    protected abstract String getTextFill(T item);

    private Text getText(String fill) {
        Text text = new Text();
        text.setText(fill);

        text.wrappingWidthProperty().bind(getTableColumn().widthProperty());
        text.setTextAlignment(TextAlignment.CENTER);

        return text;
    }

    @Override
    protected void updateItem(T item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            if (Objects.nonNull(item)) {
                String textFill = getTextFill(item);
                Text text = getText(textFill);
                setGraphic(text);
            } else {
                Text text = getText(NO_CONTENT);
                setGraphic(text);
            }
            setAlignment(Pos.CENTER);
        } else {
            setText(null);
            setGraphic(null);
        }
    }

}
