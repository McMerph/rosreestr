package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.scene.control.Alert;
import ru.mcmerphy.rosreestr.model.LoadFileFailException;

/**
 * Represents {@link LoadFileFailException} instance.
 */
public class LoadFileFailAlert extends Alert {

    public LoadFileFailAlert(LoadFileFailException e) {
        super(Alert.AlertType.ERROR);
        setHeaderText("Ошибка при загрузке данных из файла");
        setTitle("Ошибка при загрузке данных из файла");
        setContentText(e.getMessage());
    }

}
