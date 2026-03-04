package gitlet;

import java.io.Serializable;

public class Branch implements Serializable {
    public String name;
    public Commit head;
    public Commit parent;

    public Branch(String name, Commit start) {
        this.name = name;
        this.head = start;
    }

    public void writeInDirectory() {
        Utils.writeObject(Utils.join(Repository.BRANCHES_DIR, this.name), this);
    }

    public void writeInCurrentBranch() {
        Utils.writeObject(Repository.CURRENT_BRANCH_FILE, this);
    }

    @Override
    public String toString() {
        return name;
    }
}
