package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.scene.control.Alert;
import ru.mcmerphy.rosreestr.model.SaveFileFailException;

/**
 * Represents {@link SaveFileFailException} instance.
 */
public class SaveFileFailAlert extends Alert {

    public SaveFileFailAlert(SaveFileFailException e) {
        super(Alert.AlertType.ERROR);
        setHeaderText("Ошибка при сохранении данных в файл");
        setTitle("Ошибка при сохранении данных в файл");
        setContentText(e.getMessage());
    }

}
