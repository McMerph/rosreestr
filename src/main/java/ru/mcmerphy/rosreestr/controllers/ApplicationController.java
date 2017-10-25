package ru.mcmerphy.rosreestr.controllers;

import javafx.application.HostServices;
import javafx.beans.binding.StringBinding;
import javafx.beans.property.ObjectProperty;
import javafx.fxml.FXML;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import org.apache.commons.io.FilenameUtils;
import ru.mcmerphy.rosreestr.App;
import ru.mcmerphy.rosreestr.controllers.layout.ControlsController;
import ru.mcmerphy.rosreestr.controllers.layout.FlatExcerptsController;
import ru.mcmerphy.rosreestr.controllers.layout.NoCurrentFlatExcerptException;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FilesAlreadyContainsAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FlatParserFailAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.LoadFileFailAlert;
import ru.mcmerphy.rosreestr.model.ApplicationModel;
import ru.mcmerphy.rosreestr.model.FilesAlreadyContainsException;
import ru.mcmerphy.rosreestr.model.FlatParserFailException;
import ru.mcmerphy.rosreestr.model.LoadFileFailException;
import ru.mcmerphy.rosreestr.model.domain.FlatExcerpt;

import java.io.File;
import java.io.IOException;

//TODO Show spinner while loading?

/**
 * Application main controller.
 */
public class ApplicationController {

    private ApplicationModel model;

    private Stage stage;
    private HostServices hostServices;

    // Included controllers from FXML-layout.
    // Naming convention that must be respected: "fx:id"+"Controller" e.g. "first"+"Controller".

    @FXML
    private ControlsController controlsController;

    @FXML
    private FlatExcerptsController flatExcerptsController;

    /**
     * Instantiate model, initialize subsequent controllers and handle stage.
     */
    public void start() {
        model = new ApplicationModel();
        controlsController.init(this, model);
        flatExcerptsController.init(model);

        try {
            model.loadAssociatedConfig();
        } catch (LoadFileFailException e) {
            new LoadFileFailAlert(e).showAndWait();
        } catch (FilesAlreadyContainsException e) {
            new FilesAlreadyContainsAlert(e).showAndWait();
        } catch (FlatParserFailException e) {
            new FlatParserFailAlert(e).showAndWait();
        }

        handleStage();
    }

    public FlatExcerpt getCurrentFlatExcerpt() throws NoCurrentFlatExcerptException {
        return this.flatExcerptsController.getCurrentFlatExcerpt();
    }

    public Stage getStage() {
        return stage;
    }

    public void setStage(Stage stage) {
        this.stage = stage;
    }

    public HostServices getHostServices() {
        return hostServices;
    }

    public void setHostServices(HostServices hostServices) {
        this.hostServices = hostServices;
    }

    private void handleStage() {
        setStageIcon();
        bindStageTitle();
    }

    private void setStageIcon() {
        try {
            stage.getIcons().add(new Image(App.class.getResource("/images/logo.png").openStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void bindStageTitle() {
        ObjectProperty<File> associatedConfigFile = model.getFileSystemHandler().associatedConfigFileProperty();

        stage.titleProperty().bind(new StringBinding() {

            final String prefix = "Выписки помещений из росреестра";

            {
                super.bind(associatedConfigFile);
            }

            @Override
            protected String computeValue() {
                if (associatedConfigFile.isNull().get()) {
                    return prefix;
                } else {
                    return String.join(" ",
                            prefix,
                            "-",
                            FilenameUtils.removeExtension(associatedConfigFile.get().getName()));
                }
            }

        });
    }

}
