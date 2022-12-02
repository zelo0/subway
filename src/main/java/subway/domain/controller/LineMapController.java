package subway.domain.controller;

import subway.domain.*;

public class LineMapController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String BACK_STR = "B";

    public static void run(InputTaker inputTaker) {
        while (true) {
            printMenu();
            String menuInput = requestMenuSelection(inputTaker);
            if (menuInput.equals(ENROLL_STR) && enrollSection(inputTaker)) {
                break;
            }
            if (menuInput.equals(DELETE_STR) && deleteSection(inputTaker)) {
                break;
            }
            if (menuInput.equals(BACK_STR)) {
                break;
            }
        }
    }

    private static boolean deleteSection(InputTaker inputTaker) {
        return true;
    }

    private static boolean enrollSection(InputTaker inputTaker) {
        String lineName = inputTaker.takeInputWithMessage("## 노선을 입력하세요.");    // 존재하는 노선인 지 체크
        if (!LineRepository.isExistLine(lineName)) {
            System.out.println("[ERROR] 존재하지 않는 노선입니다.");
            return false;
        }
        String stationName = inputTaker.takeInputWithMessage("## 역이름을 입력하세요.");  // 존재하는 역인 지 체크
        if (!StationRepository.isExistStation(stationName)) {
            System.out.println("[ERROR] 존재하지 않는 역입니다.");
            return false;
        }
        String order = inputTaker.takeInputWithMessage("## 순서를 입력하세요.");  // 숫자인 지 체크
        if (!Validator.isNumber(order)) {
            System.out.println("[ERROR] 순서는 숫자여야 합니다.");
            return false;
        }
        LineMapRepository.addStationInLineInOrder(lineName, stationName, Integer.parseInt(order));
        return true;
    }

    private static void printMenu() {
        System.out.println();
        System.out.println("## 구간 관리 화면");
        System.out.println(ENROLL_STR + ". 구간 등록");
        System.out.println(DELETE_STR + ". 구간 삭제");
        System.out.println(BACK_STR + ". 돌아가기");
    }

    private static String requestMenuSelection(InputTaker inputTaker) {
        String input;
        do {
            input = inputTaker.takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (!isValidFunction(input));
        return input.trim();
    }

    private static boolean isValidFunction(String input) {
        try {
            Validator.checkIfValidMenu(input, new String[]{ENROLL_STR, DELETE_STR, BACK_STR});
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }

    public static void printMap() {
        System.out.println();
        System.out.println("## 지하철 노선도");
        LineMapRepository.printMap();
    }
}
