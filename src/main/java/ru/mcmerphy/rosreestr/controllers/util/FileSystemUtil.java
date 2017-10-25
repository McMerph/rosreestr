package ru.mcmerphy.rosreestr.controllers.util;

import org.apache.commons.io.FilenameUtils;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class FileSystemUtil {

    /**
     * Get xml-files from specified directory.
     *
     * @param directory directory to search for xml-files
     */
    public static List<File> getXmlFiles(File directory) {
        List<File> files = new ArrayList<>();
        if (Objects.nonNull(directory)) {
            try (Stream<Path> paths = Files.walk(Paths.get(directory.getPath()))) {
                paths
                        .filter(Files::isRegularFile)
                        .filter(path -> FilenameUtils.getExtension(path.getFileName().toString()).equals("xml"))
                        .map(Path::toFile)
                        .forEach(files::add);
            } catch (IOException e) {
                return new ArrayList<>();
            }
        }

        return files;
    }

    /**
     * Get most common parent directory for specified files.
     *
     * @param files files to handle
     */
    public static File getMostCommonDirectory(List<File> files) {
        return files.stream()
                .map(File::getParentFile)
                .filter(Objects::nonNull)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting()))
                .entrySet()
                .stream()
                .max(Comparator.comparing(Map.Entry::getValue))
                .map(Map.Entry::getKey)
                .orElse(new File("./"));
    }

}
