package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.scene.control.Alert;

/**
 * Represents fail on open file.
 */
public class OpenFileFailAlert extends Alert {

    public OpenFileFailAlert() {
        super(AlertType.ERROR);
        String error = "Ошибка при открытии файла";
        setHeaderText(error);
        setTitle(error);
        setContentText(error);
    }

}
