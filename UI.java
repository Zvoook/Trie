import java.util.Scanner;

public class UI extends InputHandler implements Constants {

    public UI(Scanner scanner) {
        super(scanner);
    }

    public void help() {
        System.out.println(HELP_INFO);
    }

    public void print(Trie trie) {
        if (!trie.isEmpty()) {
            System.out.println(trie);
        } else {
            Constants.printError(ERROR_COLLECTION_EMPTY);
        }
    }

    public void clear(Trie trie) {
        Constants.printInfo("Clearing the TRIE...");
        trie.clear();
        Constants.printSuccess("Trie cleared successfully.");
    }

    // Поиск слов по префиксу
    public void find(Trie trie) {
        if (!trie.isEmpty()) {
            Constants.printInfo("Finding words in TRIE...");
            try {
                String prefix = readNonEmptyString(PROMPT_INSERT_PREFIX);
                String[] words = trie.getByPrefix(prefix);

                if (words.length == 0) {
                    Constants.printInfo("No words found with prefix '" + prefix + "'.");
                } else {
                    Constants.printInfo("Found " + words.length + " word(s):");
                    for (String word : words) {
                        System.out.println("  - " + word);
                    }
                }
            } catch (IllegalArgumentException e) {
                Constants.printError(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                Constants.printError(ERROR_INVALID_SYMBOL);
            }
        }
        else {
            Constants.printError(ERROR_COLLECTION_EMPTY);
        }
    }

    // Добавление нового слова
    public void add(Trie trie) {
        Constants.printInfo("Adding the word in TRIE...");
        try {
            String word = readValidWord();
            if (!trie.contains(word)) {
                trie.insert(word);
                Constants.printSuccess("Word '" + word + "' added successfully.");
            }
            else Constants.printError(ERROR_WORD_ALREADY_EXISTS);
        } catch (IllegalArgumentException e) {
            Constants.printError(e.getMessage());
        } catch (ArrayIndexOutOfBoundsException e) {
            Constants.printError(ERROR_INVALID_SYMBOL);
        }
    }

    // Проверка наличия слова
    public void contains(Trie trie) {
        if (!trie.isEmpty()) {
            Constants.printInfo("Checking containing the word in TRIE...");
            try {
                String word = readValidWord();
                if (trie.contains(word)) {
                    Constants.printSuccess(WORD_IS_CONTAINS);
                } else {
                    Constants.printInfo(WORD_IS_NOT_CONTAINS);
                }
            } catch (IllegalArgumentException e) {
                Constants.printError(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                Constants.printError(ERROR_INVALID_SYMBOL);
            }
        }
        else {
            Constants.printError(ERROR_COLLECTION_EMPTY);
        }
    }

    // Проверка существования слов с префиксом
    public void startsWith(Trie trie) {
        if (!trie.isEmpty()) {
            try {
                String prefix = readValidWord();
                if (trie.startsWith(prefix)) {
                    Constants.printSuccess(WORDS_STARTS_WITH);
                } else {
                    Constants.printInfo(WORDS_NOT_STARTS_WITH);
                }
            } catch (IllegalArgumentException e) {
                Constants.printError(e.getMessage());
            } catch (ArrayIndexOutOfBoundsException e) {
                Constants.printError(ERROR_INVALID_SYMBOL);
            }
        }
        else {
            Constants.printError(ERROR_COLLECTION_EMPTY);
        }
    }
}