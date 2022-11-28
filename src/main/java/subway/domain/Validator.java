package subway.domain;

public class Validator {

    public static void checkIfNumberInRange(String input, int startInclusive, int endInclusive) {
        int inputNumber;

        try {
            inputNumber = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }

        if (inputNumber < startInclusive || inputNumber > endInclusive) {
            throw new IllegalArgumentException();
        }
    }

    public static void checkIfValidInput(String input, int startInclusive, int endInclusive, String acceptedString) {
        String trimmedInput = input.trim();
        if (trimmedInput.equalsIgnoreCase(acceptedString)) {
            return;
        }
        try {
            checkIfNumberInRange(trimmedInput, startInclusive, endInclusive);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException();
        }
    }
}
