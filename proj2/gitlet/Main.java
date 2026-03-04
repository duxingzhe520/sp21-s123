package gitlet;

/** Driver class for Gitlet, a subset of the Git version-control system.
 *  @author duxingzhe520
 */
public class Main {

    /** Usage: java gitlet.Main ARGS, where ARGS contains
     *  <COMMAND> <OPERAND1> <OPERAND2> ... 
     */
    public static void main(String[] args) {
        // TODO: what if args is empty?
        if (args.length == 0) {
            System.out.println("Please enter a command.");
            return;
        }

        String firstArg = args[0];
        switch(firstArg) {
            case "init":
                if (!validArg(args, 1)) {
                    System.out.println("Incorrect operands.");
                }
                Repository.repositorySetup();
                break;
            case "add":
                if (!validArg(args, 2)) {
                    System.out.println("Incorrect operands.");
                }
                Repository.addFile(args[1]);
                break;
            case "commit":
                if (args.length == 1) {
                    System.out.println("Please enter a commit message.");
                    return;
                }
                if (!validArg(args, 2)) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.commit(args[1]);
                break;
            case "rm":
                if (!validArg(args, 2)) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.remove(args[1]);
                break;
            case "log":
                if (!validArg(args, 1)) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.showLog();
                break;
            case "global-log":
                if (!validArg(args, 1)) {
                    System.out.println("Incorrect operands.");
                    return;
                }
                Repository.showAllLog();
                break;
            case "find":
                if (!validArg(args, 2)) {
                    System.out.println("Incorrect operands");
                    return;
                }
                Repository.find(args[1]);
            case "status":
                if (!validArg(args, 1)) {
                    System.out.println("Incorrect operands");
                    return;
                }
                Repository.getStatus();
            case "checkout":
                if (args.length == 3) {
                    if (!validArg(args, 1, "--")) {
                        System.out.println("Incorrect operands");
                        return;
                    }
                    Repository.checkOutFile(args[2]);
                } else if (args.length == 4) {
                    if (!validArg(args, 2, "--")) {
                        System.out.println("Incorrect operands");
                        return;
                    }
                    Repository.checkOutCommit(args[1], args[3]);
                } else if (args.length == 2) {
                    Repository.checkOutBranch(args[1]);
                } else {
                    System.out.println("Incorrect operands");
                    return;
                }
            default:
                System.out.println("No command with that name exists.");
                return;
        }
    }

    private static boolean validArg(String[] args, int length) {
        return args.length == length;
    }

    private static boolean validArg(String[] args, int index, String goal) {
        return args.length > index && index >= 0 && args[index].equals(goal);
    }
}
