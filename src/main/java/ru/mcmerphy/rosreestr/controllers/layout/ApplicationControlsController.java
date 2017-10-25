package ru.mcmerphy.rosreestr.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.stage.FileChooser;
import ru.mcmerphy.rosreestr.controllers.ApplicationController;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FilesAlreadyContainsAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FlatParserFailAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.LoadFileFailAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.SaveFileFailAlert;
import ru.mcmerphy.rosreestr.model.*;

import java.io.File;
import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.layout.components.ChoosersProducer.produceXmlFileChooser;

/**
 * Represents controls of application.
 */
public class ApplicationControlsController {

    private ApplicationController applicationController;
    private ApplicationModel model;

    public void init(ApplicationController applicationController, ApplicationModel model) {
        this.applicationController = applicationController;
        this.model = model;
        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.N, KeyCombination.CONTROL_DOWN), this::handleCreate);
        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.O, KeyCombination.CONTROL_DOWN), this::handleOpen);
        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.S, KeyCombination.CONTROL_DOWN), this::handleSave);
    }

    /**
     * Remove link to configuration file and clear flat parsers list
     */
    @FXML
    private void handleCreate() {
        model.getFileSystemHandler().removeAssociatedConfigFile();
        model.clearFlatExcerpts();
    }

    /**
     * Load flat excerpts list from file
     */
    @FXML
    private void handleOpen() {
        FileChooser fileChooser = produceXmlFileChooser("Открыть список выписок",
                model.getFileSystemHandler().getInitialOpenDirectory());
        File file = fileChooser.showOpenDialog(applicationController.getStage());

        if (Objects.nonNull(file)) {
            model.getFileSystemHandler().saveInitialOpenDirectory(file.getParentFile());
            try {
                model.loadConfig(file);
            } catch (LoadFileFailException e) {
                new LoadFileFailAlert(e).showAndWait();
            } catch (FilesAlreadyContainsException e) {
                new FilesAlreadyContainsAlert(e).showAndWait();
            } catch (FlatParserFailException e) {
                new FlatParserFailAlert(e).showAndWait();
            }
        }
    }

    /**
     * Save flat excerpts list to file
     */
    @FXML
    private void handleSave() {
        if (model.getFileSystemHandler().isAssociatedConfigFileExists()) {
            try {
                model.saveAssociatedConfig();
            } catch (SaveFileFailException e) {
                new SaveFileFailAlert(e).showAndWait();
            }
        } else {
            handleSaveAs();
        }
    }

    /**
     * Save flat excerpts list to new file
     */
    @FXML
    private void handleSaveAs() {
        FileChooser fileChooser = produceXmlFileChooser("Сохранить список выписок",
                model.getFileSystemHandler().getInitialSaveDirectory());
        File file = fileChooser.showSaveDialog(applicationController.getStage());

        if (Objects.nonNull(file)) {
            if (!file.getPath().endsWith(".xml")) {
                file = new File(file.getPath() + ".xml");
            }
            model.getFileSystemHandler().saveInitialSaveDirectory(file.getParentFile());
            try {
                model.saveConfig(file);
            } catch (SaveFileFailException e) {
                new SaveFileFailAlert(e).showAndWait();
            }
        }
    }

}
