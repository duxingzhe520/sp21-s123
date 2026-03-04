package gitlet;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.TreeMap;

import static gitlet.Utils.*;

// TODO: any imports you need here

/** Represents a gitlet repository.
 *  TODO: It's a good idea to give a description here of what else this Class
 *  does at a high level.
 *
 *  @author TODO
 */
public class Repository {
    /**
     * TODO: add instance variables here.
     *
     * List all instance variables of the Repository class here with a useful
     * comment above them describing what that variable represents and how that
     * variable is used. We've provided two examples for you.
     */

    /** The current working directory. */
    public static final File CWD = new File(System.getProperty("user.dir"));
    /** The .gitlet directory. */
    public static final File GITLET_DIR = join(CWD, ".gitlet");
    /** The commits directory, a subdirectory of GITLET_DIR, where all the
     *  commits' information be stored at. */
    public static final File COMMITS_DIR = join(GITLET_DIR, "commits");
    /** The blobs directory, another subdirectory of GITLET_DIR, where all the
     *  blobs' information be stored at. */
    public static final File BLOBS_DIR = join(GITLET_DIR, "blobs");
    public static final File STAGE_FILE = join(GITLET_DIR, "stage");
    public static final File BRANCHES_DIR = join(GITLET_DIR, "branches");
    public static final File CURRENT_BRANCH_FILE = join(GITLET_DIR, "currentBranch");

    /* TODO: fill in the rest of this class. */

    public static void repositorySetup() {
        if (GITLET_DIR.exists()) {
            System.out.println("A Gitlet version-control system already exists in the current directory.");
            System.exit(0);
        }
        GITLET_DIR.mkdir();
        COMMITS_DIR.mkdir();
        BLOBS_DIR.mkdir();
        BRANCHES_DIR.mkdir();

        Commit initialCommit = new Commit();
        initialCommit.writeInDirectory();

        Branch currentBranch = new Branch("master", initialCommit);
        currentBranch.writeInDirectory();
        currentBranch.writeInCurrentBranch();

        StagingArea stage = new StagingArea();
        stage.writeInFile();
    }

    public static void addFile(String fileName) {
        StagingArea stage = updateStage();
        Branch currentBranch = updateBranch();

        File thisFile = Utils.join(Repository.CWD, fileName);
        if (!thisFile.exists()) {
            System.out.println("File does not exist.");
            return;
        }

        Blob tmpBlob = new Blob(fileName, Utils.readContents(thisFile));
        String thisHashCode = tmpBlob.getHashCode();

        /* Check if the current commit includes tmpBlob. */
        TreeMap<String, String> currentCommitBlob = currentBranch.head.getBlobSet();
        if (currentCommitBlob.containsKey(fileName)) {
            if (currentCommitBlob.get(fileName).equals(thisHashCode)) {
                stage.removeFile(fileName);
                return;
            }
        }
        stage.putFile(fileName, tmpBlob);
        Utils.writeObject(Utils.join(STAGE_FILE), stage);
    }

    public static void commit(String message) {
        StagingArea stage = updateStage();
        if (stage.isFileEmpty()) {
            System.out.println("No changes added to the commit.");
            return;
        }

        Branch currentBranch = updateBranch();
        Commit headCommit = currentBranch.head;
        Date date = new Date();
        Commit newCommit = new Commit(headCommit, message, date);

        if (!stage.isRemovalEmpty()) {
            for(String fileName : stage.getRemoveSet()) {
                newCommit.removeFile(fileName);
            }
        }
        newCommit.mergeStage(stage);
        newCommit.writeInDirectory();

        currentBranch.head = newCommit;
        currentBranch.writeInDirectory();
        currentBranch.writeInCurrentBranch();
    }

    public static void remove(String fileName) {
        StagingArea stage = updateStage();
        if (!stage.isFileEmpty() && stage.contain(fileName)) {
            stage.removeFile(fileName);
            stage.writeInFile();
            return;
        }

        Commit headCommit = updateCommit();
        if (headCommit.containFile(fileName)) {
            Blob tracking = headCommit.getBlob(fileName);
            stage.addRemovalFile(fileName, tracking);
            stage.writeInFile();

            File toRemove = new File(CWD, fileName);
            Utils.restrictedDelete(toRemove);
            return;
        }

        System.out.println("No reason to remove the file.");
    }

    public static void showLog() {
        Commit headCommit = updateCommit();
        headCommit.showAllCommit();
    }

    public static void showAllLog() {
        List<String> commitHashCodes = Utils.plainFilenamesIn(COMMITS_DIR);
        for(String commitCode : commitHashCodes) {
            System.out.println(readCommit(commitCode));
        }
    }

    public static void find(String commitMessage) {
        boolean hasFound = false;
        List<String> commitHashCodes = Utils.plainFilenamesIn(COMMITS_DIR);
        for(String commitCode : commitHashCodes) {
            Commit tmp = readCommit(commitCode);
            if (commitMessage.equals(tmp.getMessage())) {
                hasFound = true;
                System.out.println(tmp);
            }
        }
        if (!hasFound) {
            System.out.println("Found no commit with that message.");
        }
    }

    public static void getStatus() {
        System.out.println("=== Branches ===");
        System.out.println(updateBranch());
        List<String> branchNames = Utils.plainFilenamesIn(BRANCHES_DIR);
        for(String branchName : branchNames) {
            String currentBranchName = updateBranch().toString();
            if (!currentBranchName.equals(branchName)) {
                System.out.println(branchName);
            }
        }
        System.out.println();

        System.out.println("=== Staged Files ===");
        StagingArea stage = updateStage();
        Set<String> stageFiles = stage.getKeySet();
        for(String fileName : stageFiles) {
            System.out.println(fileName);
        }
        System.out.println();

        System.out.println("=== Removed Files ===");
        Set<String> removalFiles = stage.getRemoveSet();
        for(String fileName : removalFiles) {
            System.out.println(fileName);
        }
        System.out.println();

        System.out.println("=== Modifications Not Staged For Commit ===");
        System.out.println();

        System.out.println("=== Untracked Files ===");
        System.out.println();
        System.out.println();
    }

    public static void checkOutFile(String fileName) {
        Commit headCommit = updateCommit();
        if (!headCommit.containFile(fileName)) {
            System.out.println("File does not exist in that commit.");
            return;
        }
        Blob toChange = headCommit.getBlob(fileName);
        //TODO: ***
        return;
    }

    public static void checkOutCommit(String commitID, String fileName) {
        return;
    }

    public static void checkOutBranch(String branchName) {
        return;
    }

    private static StagingArea updateStage() {
        return Utils.readObject(STAGE_FILE, StagingArea.class);
    }

    private static Branch updateBranch() {
        return Utils.readObject(CURRENT_BRANCH_FILE, Branch.class);
    }

    private static Commit updateCommit() {
        return updateBranch().head;
    }

    private static Commit readCommit(String hashCode) {
        return Utils.readObject(Utils.join(COMMITS_DIR, hashCode), Commit.class);
    }

    public static Blob getBlobContent(String hashCode) {
        return Utils.readObject(join(BLOBS_DIR, hashCode), Blob.class);
    }














}
