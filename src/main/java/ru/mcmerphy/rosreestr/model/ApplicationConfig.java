package ru.mcmerphy.rosreestr.model;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Application configuration that can be save to(load from) file.
 * Includes list of files to extract flats.
 */
@XmlRootElement(name = "config")
@XmlAccessorType(XmlAccessType.NONE)
public class ApplicationConfig {

    public ApplicationConfig() {
    }

    public ApplicationConfig(List<File> filesList) {
        this.filesList = filesList;
    }

    @XmlElement(name = "filesList")
    private List<File> filesList = new ArrayList<>();

    public List<File> getFilesList() {
        return filesList;
    }

    private void setFilesList(List<File> filesList) {
        this.filesList = filesList;
    }

}
