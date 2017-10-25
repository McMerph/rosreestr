package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import ru.mcmerphy.rosreestr.model.FlatParserFailException;
import ru.mcmerphy.rosreestr.model.domain.parser.FlatParser;

/**
 * Represents {@link FlatParserFailException} instance.
 */
public class FlatParserFailAlert extends Alert {

    public FlatParserFailAlert(FlatParserFailException e) {
        super(Alert.AlertType.ERROR);
        getDialogPane().setMinWidth(1000);
        setHeaderText("Парсинг потерпел неудачу");
        setTitle("Парсинг потерпел неудачу");
        getDialogPane().setContent(getFlatParsersTable(e));
    }

    private TableView<FlatParser> getFlatParsersTable(FlatParserFailException e) {
        TableView<FlatParser> table = new TableView<>();
        table.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);
        table.getColumns().add(getFileColumn());
        table.getColumns().add(getParseErrorsColumn());
        table.setItems(FXCollections.observableArrayList(e.getFlatParsers()));

        return table;
    }

    private TableColumn<FlatParser, String> getFileColumn() {
        TableColumn<FlatParser, String> fileColumn = new TableColumn<>("Файл");
        fileColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(cellData.getValue().getFile().getName()));

        return fileColumn;
    }

    private TableColumn<FlatParser, String> getParseErrorsColumn() {
        TableColumn<FlatParser, String> parseErrorsColumn = new TableColumn<>("Ошибки парсера");
        parseErrorsColumn.setCellValueFactory(
                cellData -> new SimpleStringProperty(String.join(
                        "\n",
                        cellData.getValue().getParseErrors()
                ))
        );

        return parseErrorsColumn;
    }
}
