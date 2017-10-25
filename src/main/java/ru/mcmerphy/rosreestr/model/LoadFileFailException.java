package ru.mcmerphy.rosreestr.model;

import java.io.File;

/**
 * Signals that file load is failed.
 */
public class LoadFileFailException extends Exception {

    public LoadFileFailException(File file) {
        super("Ошибка при загрузке данных из файла: " + file.getPath());
    }

}
