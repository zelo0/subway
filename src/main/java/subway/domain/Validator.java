package subway.domain;

public class Validator {

    public static void checkIfValidInput(String input, String[] acceptedStringList) {
        String trimmedInput = input.trim();
        for (String acceptedString : acceptedStringList) {
            if (trimmedInput.equalsIgnoreCase(acceptedString)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }
}
