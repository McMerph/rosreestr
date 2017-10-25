package ru.mcmerphy.rosreestr.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import ru.mcmerphy.rosreestr.controllers.ApplicationController;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.OpenFileFailAlert;
import ru.mcmerphy.rosreestr.model.ApplicationModel;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;

import java.awt.*;
import java.io.File;
import java.io.IOException;

/**
 * Represents controls of current flat excerpt.
 */
public class FlatExcerptControlsController {

    private ApplicationController applicationController;
    private ApplicationModel model;

    public void init(ApplicationController applicationController, ApplicationModel model) {
        this.applicationController = applicationController;
        this.model = model;
        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.DELETE), this::handleRemove);
        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.O), this::handleOpen);
    }

    /**
     * Remove flat parser from list
     */
    @FXML
    private void handleRemove() {
        try {
            model.removeFlatParser(applicationController.getCurrentFlatExcerpt());
        } catch (NoCurrentFlatExcerptException e) {
        }
    }

    /**
     * Open file from currently selected flat parser
     */
    @FXML
    private void handleOpen() {
        try {
            FlatExcerpt flatExcerpt = applicationController.getCurrentFlatExcerpt();
            File fileToOpen = flatExcerpt.getFile();
            try {
                Desktop.getDesktop().open(fileToOpen);
            } catch (IOException e) {
                new OpenFileFailAlert().showAndWait();
                e.printStackTrace();
            }
        } catch (NoCurrentFlatExcerptException e) {
        }
    }

}
