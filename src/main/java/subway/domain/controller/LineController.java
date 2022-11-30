package subway.domain.controller;

import subway.domain.*;

public class LineController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String VIEW_STR = "3";
    private static final String BACK_STR = "B";
    
    public static void run(InputTaker inputTaker) {
        while (true) {
            printMenu();
            String menuInput = requestMenuSelection(inputTaker);
            if (menuInput.equals(ENROLL_STR) && enrollLine(inputTaker)) {
                return;
            }
            if (menuInput.equals(DELETE_STR) && deleteLine(inputTaker)) {
                return;
            }
            if (menuInput.equals(VIEW_STR)) {
                showLines();
                return;
            }
            if (menuInput.equals(BACK_STR)) {
                break;
            }
        }
    }

    private static boolean deleteLine(InputTaker inputTaker) {
        String inputName = inputTaker.takeDeletingStationName();
        return LineMapRepository.deleteLineInMap(inputName);
    }

    private static void showLines() {

    }

    private static boolean enrollLine(InputTaker inputTaker) {
        String inputName = inputTaker.takeAddingStationName();
        if (isValidLineNameToEnroll(inputName)) {
            String topEndStation = inputTaker.takeTopEndStation();
            String bottomEndStation = inputTaker.takeBottomEndStation();
            if (isValidStation(topEndStation) && isValidStation(bottomEndStation)) {
                LineRepository.addLine(new Line(inputName));
                LineMapRepository.addLine(inputName, topEndStation, bottomEndStation);
                System.out.println();
                System.out.println("[INFO] 지하철 노선이 등록되었습니다.");
                System.out.println();
                return true;
            }
        }
        return false;
    }

    private static boolean isValidStation(String stationName) {
        try {
            Validator.checkIfExistsStation(stationName);
        } catch (IllegalArgumentException e) {
            return false;
        }
        return true;
    }

    private static boolean isValidLineNameToEnroll(String inputName) {
        try {
            Validator.checkIfValidLineName(inputName);
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("[ERROR] 노선 역 이름은 2글자 이상이고 기존에 등록되지 않은 이름이어야 합니다.");
            System.out.println();
            return false;
        }
        return true;
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
            System.out.println();
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("## 노석 관리 화면");
        System.out.println(ENROLL_STR + ". 노선 등록");
        System.out.println(DELETE_STR + ". 노선 삭제");
        System.out.println(VIEW_STR + ". 노선 조회");
        System.out.println(BACK_STR + ". 돌아가기");
    }


}
