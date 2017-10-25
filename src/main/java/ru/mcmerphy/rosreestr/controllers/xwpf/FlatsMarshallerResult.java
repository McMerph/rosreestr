package ru.mcmerphy.rosreestr.controllers.xwpf;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class FlatsMarshallerResult {

    private final File directory;

    public FlatsMarshallerResult(File directory) {
        this.directory = directory;
    }

    private final List<String> fails = new ArrayList<>();
    private final List<String> successes = new ArrayList<>();

    public boolean hasFails() {
        return !fails.isEmpty();
    }

    public boolean hasSuccesses() {
        return !successes.isEmpty();
    }

    public File getDirectory() {
        return directory;
    }

    public List<String> getFails() {
        return fails;
    }

    public List<String> getSuccesses() {
        return successes;
    }

}
