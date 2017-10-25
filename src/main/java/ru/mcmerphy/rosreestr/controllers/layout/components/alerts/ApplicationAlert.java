package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.scene.Node;
import javafx.scene.control.Alert;

/**
 * Application alert.
 */
public abstract class ApplicationAlert extends Alert {

    public ApplicationAlert(Alert.AlertType type) {
        super(type);
    }

    public void setBigWidth() {
        getDialogPane().setMinWidth(1000);
    }

    public void setHeader(String header) {
        setHeaderText(header);
    }

    public void setContent(String content) {
        setContentText(content);
    }

    public void setContent(Node node) {
        getDialogPane().setContent(node);
    }

}
