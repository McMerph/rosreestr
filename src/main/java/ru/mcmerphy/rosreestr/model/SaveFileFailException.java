package ru.mcmerphy.rosreestr.model;

import java.io.File;

/**
 * Signals that file save is failed.
 */
public class SaveFileFailException extends Exception {

    public SaveFileFailException(File file) {
        super("Ошибка при сохранении данных в файл: " + file.getPath());
    }

}
