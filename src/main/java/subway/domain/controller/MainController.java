package subway.domain.controller;

import subway.domain.Validator;

import java.util.Scanner;

public class MainController {
    private Scanner scanner;
    private static final String END_STR = "Q";

    public MainController(Scanner scanner) {
        this.scanner = scanner;
    }

    public void run() {
        String menuInput;
        do {
            printMenu();
            menuInput = requestFunction();
            mapToController(menuInput);
        } while (!menuInput.equalsIgnoreCase(END_STR));

    }

    private void mapToController(String menuInput) {
    }

    private void printMenu() {
        System.out.println("## 메인 화면");
        System.out.println("1. 역 관리");
        System.out.println("2. 노선 관리");
        System.out.println("3. 구간 관리");
        System.out.println("4. 지하철 노선도 출력");
        System.out.println(END_STR + ". 종료");
        System.out.println();
    }

    private String requestFunction() {
        String input;
        do {
            System.out.println("## 원하는 기능을 선택하세요.");
            input = scanner.nextLine();
        } while (!isValidFunction(input));

        return input.trim();
    }

    private boolean isValidFunction(String input) {
        try {
            Validator.checkIfValidInput(input, 1, 4, END_STR);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }


}
