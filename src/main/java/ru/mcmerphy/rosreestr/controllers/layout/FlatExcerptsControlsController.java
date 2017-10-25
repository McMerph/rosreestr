package ru.mcmerphy.rosreestr.controllers.layout;

import javafx.fxml.FXML;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import ru.mcmerphy.rosreestr.controllers.ApplicationController;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FilesAlreadyContainsAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FlatParserFailAlert;
import ru.mcmerphy.rosreestr.controllers.layout.components.alerts.FlatsMarshallerResultAlert;
import ru.mcmerphy.rosreestr.controllers.xwpf.FlatsMarshaller;
import ru.mcmerphy.rosreestr.controllers.xwpf.FlatsMarshallerResult;
import ru.mcmerphy.rosreestr.model.ApplicationModel;
import ru.mcmerphy.rosreestr.model.FilesAlreadyContainsException;
import ru.mcmerphy.rosreestr.model.FlatParserFailException;

import java.io.File;
import java.util.List;
import java.util.Objects;

import static ru.mcmerphy.rosreestr.controllers.layout.components.ChoosersProducer.produceDirectoryChooser;
import static ru.mcmerphy.rosreestr.controllers.layout.components.ChoosersProducer.produceXmlFileChooser;
import static ru.mcmerphy.rosreestr.controllers.util.FileSystemUtil.getMostCommonDirectory;
import static ru.mcmerphy.rosreestr.controllers.util.FileSystemUtil.getXmlFiles;

public class FlatExcerptsControlsController {

    private ApplicationController applicationController;
    private ApplicationModel model;

    public void init(ApplicationController applicationController, ApplicationModel model) {
        this.applicationController = applicationController;
        this.model = model;

        applicationController.getStage().getScene().getAccelerators()
                .put(new KeyCodeCombination(KeyCode.W), this::toDocx);
    }

    /**
     * Add files to parse
     */
    @FXML
    private void handleAddFiles() {
        FileChooser fileChooser = produceXmlFileChooser("Добавить файл для обработки",
                model.getFileSystemHandler().getInitialAddDirectory());
        List<File> files = fileChooser.showOpenMultipleDialog(applicationController.getStage());

        if (Objects.nonNull(files) && !files.isEmpty()) {
            model.getFileSystemHandler().saveInitialAddDirectory(getMostCommonDirectory(files));
            handleAddedFiles(files);
        }
    }

    /**
     * Add files to parse from directory
     */
    @FXML
    private void handleAddDirectory() {
        DirectoryChooser directoryChooser = produceDirectoryChooser(
                "Добавить директорию для обработки",
                model.getFileSystemHandler().getInitialAddDirectory());
        File directory = directoryChooser.showDialog(applicationController.getStage());

        if (Objects.nonNull(directory)) {
            model.getFileSystemHandler().saveInitialAddDirectory(directory);
            handleAddedFiles(getXmlFiles(directory));
        }
    }

    /**
     * Remove all flat excerpts.
     */
    @FXML
    private void clear() {
        model.clearFlatExcerpts();
    }

    /**
     * Generate docx documents from flat excerpts.
     */
    @FXML
    private void toDocx() {
        DirectoryChooser directoryChooser = produceDirectoryChooser(
                "Выберите директорию для выгрузки docx-файлов",
                model.getFileSystemHandler().getInitialToDocxDirectory());
        File directory = directoryChooser.showDialog(applicationController.getStage());

        if (Objects.nonNull(directory)) {
            model.getFileSystemHandler().saveInitialToDocxDirectory(directory);
            FlatsMarshallerResult flatsMarshallerResult = new FlatsMarshaller(directory).marshall(model.getFlats());

            new FlatsMarshallerResultAlert(flatsMarshallerResult).showAndWait();
        }
    }

    private void handleAddedFiles(List<File> addedFiles) {
        try {
            model.parseFiles(addedFiles);
        } catch (FilesAlreadyContainsException e) {
            new FilesAlreadyContainsAlert(e).showAndWait();
        } catch (FlatParserFailException e) {
            new FlatParserFailAlert(e).showAndWait();
        }
    }

}
