import java.util.Scanner;

// Класс для обработки пользовательского ввода с валидацией
public abstract class InputHandler implements Constants {
    protected final Scanner scanner;
    protected final InputValidator validator;

    public InputHandler(Scanner scanner) {
        this.scanner = scanner;
        this.validator = new InputValidator();
    }

    // Чтение непустой строки с повторным запросом при ошибке
    protected String readNonEmptyString(String message) {
        Constants.printInfo(message);
        while (true) {
            String input = scanner.nextLine().trim();
            if (!input.isEmpty()) return input;
            Constants.printError(ERROR_EMPTY_STRING);
        }
    }

    // Чтение валидного слова
    protected String readValidWord() {
        while (true) {
            String input = readNonEmptyString(PROMPT_INSERT_WORD);
            if (validator.validate(input)) {
                return input.toLowerCase();
            }
        }
    }
}