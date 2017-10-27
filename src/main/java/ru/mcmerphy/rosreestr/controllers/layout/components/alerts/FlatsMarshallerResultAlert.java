package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import ru.mcmerphy.rosreestr.controllers.xwpf.FlatsMarshallerResult;

import java.awt.*;
import java.io.IOException;
import java.util.List;

import static ru.mcmerphy.rosreestr.controllers.util.Comparators.ALPANUM_COMPARATOR;

/**
 * Represents result of flats marshalling.
 */
public class FlatsMarshallerResultAlert extends Alert {

    public FlatsMarshallerResultAlert(FlatsMarshallerResult flatsMarshallerResult) {
        super(flatsMarshallerResult.hasFails() ? AlertType.ERROR : AlertType.INFORMATION);
        getDialogPane().setMinWidth(1000);
        setHeaderText("Результаты преобразования");
        setTitle("Результаты преобразования");

        VBox vBox = new VBox();
        vBox.setPadding(new Insets(6, 12, 6, 12));
        vBox.setSpacing(10);
        vBox.setAlignment(Pos.CENTER);

        if (flatsMarshallerResult.hasFails()) {
            vBox.getChildren().add(new Label("Неудачи:"));
            List<String> fails = flatsMarshallerResult.getFails();
            fails.sort(ALPANUM_COMPARATOR);
            ListView<String> failsList = new ListView<>(FXCollections.observableArrayList(fails));
            vBox.getChildren().add(failsList);
        }

        if (flatsMarshallerResult.hasSuccesses()) {
            vBox.getChildren().add(new Label("Успехи:"));
            List<String> successes = flatsMarshallerResult.getSuccesses();
            successes.sort(ALPANUM_COMPARATOR);
            ListView<String> successesList = new ListView<>(FXCollections.observableArrayList(successes));
            vBox.getChildren().add(successesList);
        }

        Button button = new Button();
        button.setText("Перейти в директорию");
        button.setOnAction(event -> {
            try {
                Desktop.getDesktop().open(flatsMarshallerResult.getDirectory());
            } catch (IOException e) {
            }
        });
        vBox.getChildren().add(button);

        getDialogPane().setContent(vBox);
    }

}
