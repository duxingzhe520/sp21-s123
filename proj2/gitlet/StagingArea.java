package gitlet;

import java.io.File;
import java.io.Serializable;
import java.util.Set;
import java.util.TreeMap;
import static gitlet.Utils.*;

public class StagingArea implements Serializable {
    /** The key String is the file's name.
     *  We assume that there is no same name in the CWD directory. */
    private TreeMap<String, Blob> files;
    private TreeMap<String, Blob> removalStage;

    public StagingArea() {
        files = new TreeMap<>();
        removalStage = new TreeMap<>();
    }

    public void putFile(String fileName, Blob blob) {
        if (files.containsKey(fileName)) {
            if (!files.get(fileName).getHashCode().equals(blob.getHashCode())) {
                files.put(fileName, blob);
            }
        } else {
            files.put(fileName, blob);
        }
    }

    public void removeFile(String fileName) {
        if (files.containsKey(fileName)) {
            //TODO: remove the file from staging area.
            files.remove(fileName);
        }
    }

    public void writeInFile() {
        Utils.writeObject(Repository.STAGE_FILE, this);
    }

    public boolean isFileEmpty() {
        return files.isEmpty();
    }

    public boolean contain(String fileName) {
        return files.containsKey(fileName);
    }

    public Blob get(String fileName) {
        return files.get(fileName);
    }

    public Set<String> getKeySet() {
        return files.keySet();
    }

    public void clear() {
        files.clear();
    }

    public void addRemovalFile(String fileName, Blob blob) {
        removalStage.put(fileName, blob);
    }

    public Set<String> getRemoveSet() {
        return removalStage.keySet();
    }

    public boolean isRemovalEmpty() {
        return removalStage.isEmpty();
    }
}
