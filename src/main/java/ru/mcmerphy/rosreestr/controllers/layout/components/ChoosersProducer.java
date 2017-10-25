package ru.mcmerphy.rosreestr.controllers.layout.components;

import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;

import java.io.File;

/**
 * File chooser and directory chooser producer.
 */
public class ChoosersProducer {

    /**
     * Instantiate {@link FileChooser} to choose xml-file(s).
     *
     * @param title            file chooser title
     * @param initialDirectory directory to start from
     */
    public static FileChooser produceXmlFileChooser(String title, File initialDirectory) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter(
                "XML files (*.xml)", "*.xml");
        fileChooser.getExtensionFilters().add(extFilter);
        if (initialDirectory.exists()) {
            fileChooser.setInitialDirectory(initialDirectory);
        }

        return fileChooser;
    }

    /**
     * Instantiate {@link DirectoryChooser} to choose xml-file(s).
     *
     * @param title            directory chooser title
     * @param initialDirectory directory to start from
     */
    public static DirectoryChooser produceDirectoryChooser(String title, File initialDirectory) {
        DirectoryChooser directoryChooser = new DirectoryChooser();
        directoryChooser.setTitle(title);
        if (initialDirectory.exists()) {
            directoryChooser.setInitialDirectory(initialDirectory);
        }

        return directoryChooser;
    }

}
