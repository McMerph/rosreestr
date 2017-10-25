package ru.mcmerphy.rosreestr.controllers.layout.components.alerts;

import javafx.collections.FXCollections;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import org.apache.commons.io.FilenameUtils;
import ru.mcmerphy.rosreestr.model.FilesAlreadyContainsException;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Represents {@link FilesAlreadyContainsException} instance.
 */
public class FilesAlreadyContainsAlert extends Alert {

    public FilesAlreadyContainsAlert(FilesAlreadyContainsException e) {
        super(Alert.AlertType.ERROR);

        List<String> existedFileNames = e.getFiles().stream()
                .map(file -> FilenameUtils.removeExtension(file.getName()))
                .collect(Collectors.toList());
        String error = existedFileNames.size() == 1 ? "Выписка уже загружена" : "Выписки уже загружены";
        setHeaderText(error);
        setTitle(error);
        getDialogPane().setContent(new ListView<>(FXCollections.observableArrayList(existedFileNames)));
    }

}
