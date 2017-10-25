package ru.mcmerphy.rosreestr.model;

import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;

import java.io.File;
import java.util.Objects;
import java.util.prefs.Preferences;

/**
 * Application file system handler.
 * Used for handling files/directories related to application.
 * Retrieve/save paths to application preferences.
 */
public class ApplicationFileSystemHandler {

    private static final Preferences preferences = Preferences.userNodeForPackage(ApplicationFileSystemHandler.class);

    private static final String ASSOCIATED_CONFIG_FILE_PATH_KEY = "configFilePath";
    private static final String INITIAL_ADD_DIRECTORY_PATH_KEY = "initialAddDirectory";
    private static final String INITIAL_OPEN_DIRECTORY_PATH_KEY = "initialOpenDirectory";
    private static final String INITIAL_SAVE_DIRECTORY_PATH_KEY = "initialSaveDirectory";
    private static final String INITIAL_TO_DOCX_PATH_KEY = "initialToDocxDirectory";

    private final ObjectProperty<File> associatedConfigFile;
    private File initialAddDirectory;
    private File initialOpenDirectory;
    private File initialSaveDirectory;
    private File initialToDocxDirectory;

    public ApplicationFileSystemHandler() {
        associatedConfigFile = new SimpleObjectProperty<>(retrieveAssociatedConfigFile());
        initialAddDirectory = retrieveInitialDirectory(INITIAL_ADD_DIRECTORY_PATH_KEY);
        initialOpenDirectory = retrieveInitialDirectory(INITIAL_OPEN_DIRECTORY_PATH_KEY);
        initialSaveDirectory = retrieveInitialDirectory(INITIAL_SAVE_DIRECTORY_PATH_KEY);
        initialToDocxDirectory = retrieveInitialDirectory(INITIAL_TO_DOCX_PATH_KEY);
    }

    /**
     * @return true if associated config file exists
     */
    public boolean isAssociatedConfigFileExists() {
        return Objects.nonNull(associatedConfigFile.get());
    }

    /**
     * Set initial directory to add from and save path to it in preferences
     */
    public void saveInitialAddDirectory(File directory) {
        this.initialAddDirectory = directory;
        preferences.put(INITIAL_ADD_DIRECTORY_PATH_KEY, directory.getPath());
    }

    /**
     * Set initial directory to open from and save path to it in preferences
     */
    public void saveInitialOpenDirectory(File directory) {
        this.initialOpenDirectory = directory;
        preferences.put(INITIAL_OPEN_DIRECTORY_PATH_KEY, directory.getPath());
    }

    /**
     * Set initial directory to save to and save path to it in preferences
     */
    public void saveInitialSaveDirectory(File directory) {
        this.initialSaveDirectory = directory;
        preferences.put(INITIAL_SAVE_DIRECTORY_PATH_KEY, directory.getPath());
    }

    /**
     * Set initial directory to ship docx to and save path to it in preferences
     */
    public void saveInitialToDocxDirectory(File directory) {
        this.initialToDocxDirectory = directory;
        preferences.put(INITIAL_TO_DOCX_PATH_KEY, directory.getPath());
    }

    /**
     * Set associated config file and save path to it in preferences
     */
    public void saveAssociatedConfigFile(File associatedConfigFile) {
        this.associatedConfigFile.set(associatedConfigFile);
        preferences.put(ASSOCIATED_CONFIG_FILE_PATH_KEY, associatedConfigFile.getPath());
    }

    /**
     * Remove associated config file and remove path to it from preferences
     */
    public void removeAssociatedConfigFile() {
        this.associatedConfigFile.set(null);
        preferences.remove(ASSOCIATED_CONFIG_FILE_PATH_KEY);
    }

    public File getAssociatedConfigFile() {
        return associatedConfigFile.get();
    }

    public ObjectProperty<File> associatedConfigFileProperty() {
        return associatedConfigFile;
    }

    public void setAssociatedConfigFile(File associatedConfigFile) {
        this.associatedConfigFile.set(associatedConfigFile);
    }

    public File getInitialAddDirectory() {
        return initialAddDirectory;
    }

    public File getInitialOpenDirectory() {
        return initialOpenDirectory;
    }

    public File getInitialSaveDirectory() {
        return initialSaveDirectory;
    }

    public File getInitialToDocxDirectory() {
        return initialToDocxDirectory;
    }

    private File retrieveInitialDirectory(String pathKey) {
        String defaultDirectoryPath = "./";
        String filePath = preferences.get(pathKey, defaultDirectoryPath);
        File directory = new File(filePath);

        return checkDirectoryExistence(directory) ? directory : new File(defaultDirectoryPath);
    }

    private boolean checkDirectoryExistence(File directory) {
        return directory.exists() && directory.isDirectory();
    }

    private File retrieveAssociatedConfigFile() {
        String filePath = preferences.get(ASSOCIATED_CONFIG_FILE_PATH_KEY, null);

        if (Objects.nonNull(filePath)) {
            return new File(filePath);
        } else {
            return null;
        }
    }

}
