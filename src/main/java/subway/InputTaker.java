package subway;

import java.util.Scanner;

public class InputTaker {
    private Scanner scanner;

    public InputTaker(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeInputWithMessage(String message) {
        System.out.println();
        System.out.println(message);
        return scanner.nextLine();
    }
}
