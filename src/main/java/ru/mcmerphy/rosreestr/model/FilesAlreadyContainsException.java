package ru.mcmerphy.rosreestr.model;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Signals that file(s) is(are) already contains in a model.
 */
public class FilesAlreadyContainsException extends Exception {

    private List<File> files = new ArrayList<>();

    public FilesAlreadyContainsException(File file) {
        files.add(file);
    }

    public FilesAlreadyContainsException(List<File> files) {
        this.files = files;
    }

    public List<File> getFiles() {
        return files;
    }

}
