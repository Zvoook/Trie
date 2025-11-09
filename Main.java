import java.util.Scanner;

public class Main implements Constants {
    public static void main(String[] args) {
        System.out.println("Type help for instructions.");

        Scanner scan = new Scanner(System.in);
        String command = "";
        UI UI = new UI(scan);
        Trie Trie = new Trie();

        while (!command.equals(TERMINATE_SYMBOL)) {
            System.out.print("> ");
            command = scan.nextLine().trim();

            switch (command.toLowerCase()) {
                case TERMINATE_SYMBOL:
                    continue;

                case HELP:
                    UI.help();
                    break;

                case ADD:
                    UI.add(Trie);
                    break;

                case PRINT:
                    UI.print(Trie);
                    break;

                case FIND:
                    UI.find(Trie);
                    break;

                case CONTAINS:
                    UI.contains(Trie);
                    break;

                case CLEAR:
                    UI.clear(Trie);
                    break;

                case STARTS_WITH:
                    UI.startsWith(Trie);
                    break;

                default:
                    Constants.printError(ERROR_UNKNOWN_COMMAND);
            }
        }
        scan.close();
        Constants.printSuccess("Program terminated successfully.");
    }
}