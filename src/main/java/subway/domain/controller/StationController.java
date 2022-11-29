package subway.domain.controller;

import subway.domain.InputTaker;
import subway.domain.Validator;

public class StationController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String VIEW_STR = "3";
    private static final String BACK_STR = "B";

    public static void run(InputTaker inputTaker) {
        printMenu();
        String menuInput = requestMenuSelection(inputTaker);
        if (menuInput.equals(ENROLL_STR)) {
            enrollStation(inputTaker);
        }
    }

    private static void enrollStation(InputTaker inputTaker) {
        String inputName;
        do {
            inputName = inputTaker.takeStationName();
        } while (!isValidStationName(inputName));
        System.out.println();
        System.out.println("[INFO] 지하철 역이 등록되었습니다.");
        System.out.println();
    }

    private static boolean isValidStationName(String inputStationName) {
        try {
            Validator.checkIfValidStationName(inputStationName);
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 지하철 역 이름은 2글자 이상이고 기존에 등록되지 않은 이름이어야 합니다.");
            System.out.println();
            return false;
        }
        return true;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("## 역 관리 화면");
        System.out.println(ENROLL_STR + ". 역 등록");
        System.out.println(DELETE_STR + ". 역 삭제");
        System.out.println(VIEW_STR + ". 역 조회");
        System.out.println(BACK_STR + ". 돌아가기");
    }

    private static String requestMenuSelection(InputTaker inputTaker) {
        String input;
        do {
            input = inputTaker.takeMenuInput();
        } while (!isValidFunction(input));
        return input.trim();
    }

    private static boolean isValidFunction(String input) {
        try {
            Validator.checkIfValidMenu(input, new String[]{ENROLL_STR, DELETE_STR, VIEW_STR, BACK_STR});
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }
}
