package ru.mcmerphy.rosreestr.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import ru.mcmerphy.rosreestr.controllers.ApplicationController;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.AboutAlert;
import ru.mcmerphy.rosreestr.model.ApplicationModel;

public class ControlsController {

    private ApplicationController applicationController;

    // included controllers
    // naming convention that must be respected: "fx:id"+"Controller" e.g. "first"+"Controller"

    @FXML
    private ApplicationControlsController applicationControlsController;

    @FXML
    private FlatExcerptsControlsController flatExcerptsControlsController;

    @FXML
    private FlatExcerptControlsController flatExcerptControlsController;

    public void init(ApplicationController applicationController, ApplicationModel model) {
        this.applicationController = applicationController;
        this.applicationControlsController.init(applicationController, model);
        this.flatExcerptsControlsController.init(applicationController, model);
        this.flatExcerptControlsController.init(applicationController, model);

        applicationController.getStage().getScene().getAccelerators().put(
                new KeyCodeCombination(KeyCode.F1), this::handleAbout);
    }

    /**
     * Show 'About' window
     */
    @FXML
    private void handleAbout() {
        new AboutAlert(applicationController.getHostServices()).showAndWait();
    }

}
