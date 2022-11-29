package subway.domain;

import java.util.Scanner;

public class InputTaker {
    private Scanner scanner;

    public InputTaker(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeMenuInput() {
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }
}
