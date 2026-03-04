package gitlet;

import java.io.Serializable;
import java.util.Date;
import java.util.TreeMap;

/** Represents a gitlet commit object.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author duxingzhe520
 */
public class Commit implements Serializable {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Commit class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided one example for `message`.
     */

    /** The message of this Commit. */
    private String message;
    /** The date when the Commit is created. */
    private Date date;
    /** The Commit's parent Commit. */
    private Commit parent;
    /** The set of blobs stored in each Commit. */
    private TreeMap<String, String> blobSet;
    /** The SHA-1 code of the Commit. */
    private String hashCode;

    public Commit() {
        date = new Date(0);
        parent = null;
        blobSet = new TreeMap<>();
        message = "initial commit";
    }

    public Commit(Commit toCopy, String message, Date date) {
        this.date = date;
        this.message = message;
        parent = toCopy;
        blobSet = toCopy.blobSet;
        hashCode = toCopy.hashCode;
    }

    @Override
    public String toString() {
        StringBuilder toReturn = new StringBuilder();
        toReturn.append("===\n");
        toReturn.append("Date: ");
        toReturn.append(date.toString());
        toReturn.append("\n");
        toReturn.append(message);
        toReturn.append("\n");
        return toReturn.toString();
    }

    private void computeHashCode() {
        this.hashCode = Utils.sha1(Utils.serialize(this));
    }

    public String getHashCode() {
        return hashCode;
    }

    public void mergeStage(StagingArea stage) {
        for(String fileName : stage.getKeySet()) {
            Blob tmp = stage.get(fileName);
            blobSet.put(fileName, tmp.getHashCode());
            Utils.writeObject(Utils.join(Repository.BLOBS_DIR, tmp.getHashCode()), tmp);
        }
        stage.clear();
    }

    public void writeInDirectory() {
        this.computeHashCode();
        Utils.writeObject(Utils.join(Repository.COMMITS_DIR, hashCode), this);
    }

    public TreeMap<String, String> getBlobSet() {
        return blobSet;
    }

    public boolean containFile(String fileName) {
        return blobSet.containsKey(fileName);
    }

    public Blob getBlob(String fileName) {
        String hashCode = blobSet.get(fileName);
        return Repository.getBlobContent(hashCode);
    }

    public void removeFile(String fileName) {
        blobSet.remove(fileName);
    }

    public void showAllCommit() {
        Commit tmp = this;
        while(tmp != null) {
            System.out.println(tmp);
            tmp = tmp.parent;
        }
    }

    public String getMessage() {
        return message;
    }
















    /* TODO: fill in the rest of this class. */
}
