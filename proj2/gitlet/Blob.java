package gitlet;

import java.io.Serializable;

/**
 * TODO: Give the class a description on a high level.
 *
 * @author duxingzhe520
 * */

public class Blob implements Serializable {
    /** The name of the blob referring to */
    private String fileName;

    /** The SHA-1 code of the file's content */
    private String hashCode;

    /** The exact content of the blob */
    private byte[] fileContent;

    /** Creates a new blob. */
    public Blob(String fileName, byte[] fileContent) {
        this.fileName = fileName;
        this.fileContent = fileContent;
        hashCode = this.computeHashCode();
    }

    /** Returns true iff fileName equals to the blob's name. */
    public boolean isFile(String fileName) {
        return this.fileName.equals(fileName);
    }

    /** Returns true iff the contents of the blob and the file equal to each other.
     *  Rather than comparing their contents directly, We compare their SHA-1 code.*/
    public boolean sameFile(String hashCode) {
        return this.hashCode.equals(hashCode);
    }

    /***/
    public void overWriteFile(byte[] fileContent) {
        this.fileContent = fileContent;
        hashCode = this.computeHashCode();
    }

    /***/
    private String computeHashCode() {
        if (fileContent == null) {
            return null;
        }
        return Utils.sha1(fileContent);
    }

    public String getHashCode() {
        return hashCode;
    }

    public void save() {
        Utils.writeObject(Utils.join(Repository.BLOBS_DIR, hashCode), this);
    }
}
