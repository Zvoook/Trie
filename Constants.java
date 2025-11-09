public interface Constants {
    public static final int LETTERS = 26;
    public static final char FIRST_CHAR = 'a';
    public static final char LAST_CHAR = 'z';
    public static final int HASH_BASE = 17;
    public static final int HASH_MULT = 31;
    public static final int HASH_MOD = Integer.MAX_VALUE;

    // Сообщения об ошибках
    public static final String ERROR_NULL_PTR = "Null is invalid argument";
    public static final String ERROR_INVALID_SYMBOL = "Invalid symbol";
    public static final String ERROR_COLLECTION_EMPTY = "Collection is empty.";
    public static final String ERROR_EMPTY_STRING = "Input cannot be empty.";
    public static final String ERROR_UNKNOWN_COMMAND = "Unknown command. Type 'help' to see available options.";
    public static final String ERROR_WORD_ALREADY_EXISTS = "Word is already exists.";

    public static final String TERMINATE_SYMBOL = "#";

    // Информационные сообщения
    public static final String HELP_INFO =
            "Available commands:\n" +
                    "  - add      : Add a new word\n" +
                    "  - print    : Show all words\n" +
                    "  - find     : Find words by prefix\n" +
                    "  - contains : Check if word exists\n" +
                    "  - starts with : Check if some words start with the prefix\n" +
                    "  - clear    : Clear the Trie\n" +
                    "  - help     : Show this message\n" +
                    "  - #        : Exit program";

    public static final String WORD_IS_CONTAINS = "Word found in Trie.";
    public static final String WORD_IS_NOT_CONTAINS = "Word not found in Trie.";
    public static final String WORDS_STARTS_WITH = "Words with this prefix exist.";
    public static final String WORDS_NOT_STARTS_WITH = "No words with this prefix.";

    // Команды
    public static final String CLEAR = "clear";
    public static final String PRINT = "print";
    public static final String FIND = "find";
    public static final String ADD = "add";
    public static final String HELP = "help";
    public static final String STARTS_WITH = "starts with";
    public static final String CONTAINS = "contains";
    public static final String PROMPT_INSERT_WORD = "Insert word:";
    public static final String PROMPT_INSERT_PREFIX = "Insert prefix:";

    // Методы форматированного вывода
    public static void printError(String msg) {
        System.out.println("ERROR: " + msg);
    }
    public static void printSuccess(String msg) {
        System.out.println("SUCCESS: " + msg);
    }
    public static void printInfo(String msg) {
        System.out.println("@ " + msg);
    }
}