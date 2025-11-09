public class InputValidator implements Constants {

    // Проверка на null и допустимые символы
    public boolean validate(String input) {
        if (input == null || input.trim().isEmpty()) {
            Constants.printError(ERROR_EMPTY_STRING);
            return false;
        }

        input = input.trim().toLowerCase();
        for (int i = 0; i < input.length(); ++i) {
            char ch = input.charAt(i);
            if (ch < FIRST_CHAR || ch > LAST_CHAR) {
                Constants.printError(ERROR_INVALID_SYMBOL + ": '" + ch + "'");
                return false;
            }
        }
        return true;
    }
}