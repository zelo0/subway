package subway.controller;

import subway.InputTaker;
import subway.Validator;
import subway.repository.LineMapRepository;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

public class LineMapController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String BACK_STR = "B";

    private static LineMapController instance = null;

    private LineMapController() {
    }

    public static LineMapController getInstance() {
        if (instance == null) {
            instance = new LineMapController();
        }
        return instance;
    }

    public void run(InputTaker inputTaker) {
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

    private boolean deleteSection(InputTaker inputTaker) {
        String lineName = inputTaker.takeInputWithMessage("## 삭제할 구간의 노선을 입력하세요.");
        if (LineRepository.isNotExistLine(lineName)) {
            return false;
        }
        String stationName = inputTaker.takeInputWithMessage("## 삭제할 구간의 역을 입력하세요.");
        if (StationRepository.isNotExistStation(stationName)) {
            return false;
        }
        boolean wasDeleted = LineMapRepository.deleteStationInLine(lineName, stationName);
        if (!wasDeleted) {
            System.out.println("[ERROR] 노선에 해당 역이 없습니다.");
            return false;
        }
        return true;
    }

    private boolean enrollSection(InputTaker inputTaker) {
        String lineName = inputTaker.takeInputWithMessage("## 노선을 입력하세요.");    // 존재하는 노선인 지 체크
        if (LineRepository.isNotExistLine(lineName)) {
            return false;
        }
        String stationName = inputTaker.takeInputWithMessage("## 역이름을 입력하세요.");  // 존재하는 역인 지 체크
        if (StationRepository.isNotExistStation(stationName)) {
            return false;
        }
        String order = inputTaker.takeInputWithMessage("## 순서를 입력하세요.");  // 숫자인 지 체크
        if (!Validator.isNumber(order)) {
            return false;
        }
        LineMapRepository.addStationInLineInOrder(lineName, stationName, Integer.parseInt(order));
        return true;
    }

    private void printMenu() {
        System.out.println();
        System.out.println("## 구간 관리 화면");
        System.out.println(ENROLL_STR + ". 구간 등록");
        System.out.println(DELETE_STR + ". 구간 삭제");
        System.out.println(BACK_STR + ". 돌아가기");
    }

    private String requestMenuSelection(InputTaker inputTaker) {
        String input;
        do {
            input = inputTaker.takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (!isValidFunction(input));
        return input.trim();
    }

    private boolean isValidFunction(String input) {
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

    public void printMap() {
        System.out.println();
        System.out.println("## 지하철 노선도");
        LineMapRepository.printMap();
    }
}
