package subway;

import java.util.Scanner;

import static subway.common.Constant.DETAIL_BACK_MENU;

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

    public String takeMenuSelection(String[] menuOptions) {
        String input;
        do {
            input = takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (Validator.isNotValidMenu(input, menuOptions));
        return input.trim();
    }
}
