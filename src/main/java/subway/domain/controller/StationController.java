package subway.domain.controller;

import subway.domain.*;

import java.util.List;
import java.util.Map;

public class StationController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String VIEW_STR = "3";
    private static final String BACK_STR = "B";

    public static void run(InputTaker inputTaker) {
        while (true) {
            printMenu();
            String menuInput = requestMenuSelection(inputTaker);
            if (menuInput.equals(ENROLL_STR) && enrollStation(inputTaker)) {
                return;
            }
            if (menuInput.equals(DELETE_STR) && deleteStation(inputTaker)) {
                return;
            }
        }
    }

    private static boolean deleteStation(InputTaker inputTaker) {
        String inputName = inputTaker.takeDeletingStationName();
        if (isAbleToDeleteStation(inputName)) {
            StationRepository.deleteStation(inputName);
            System.out.println();
            System.out.println("[INFO] 지하철 역이 삭제되었습니다.");
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean isAbleToDeleteStation(String inputName) {
        Map<Line, List<Station>> lineListMap = LineMapRepository.lineStations();
        // 노선에 등록된 역은 삭제 불가
        if (isEnrolledInLine(inputName, lineListMap)) {
            return false;
        }
        // 존재하지 않는 역도 삭제 불가
        return isAlreadyExistsStation(inputName);
    }

    private static boolean isAlreadyExistsStation(String inputName) {
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            if (station.getName().equals(inputName)) {
                return true;
            }
        }
        System.out.println("[ERROR] 존재하지 않는 역입니다.");
        return false;
    }

    private static boolean isEnrolledInLine(String inputName, Map<Line, List<Station>> lineListMap) {
        for (List<Station> stationList : lineListMap.values()) {
            if (containsStation(inputName, stationList)) {
                System.out.println("[ERROR] 노선에 등록된 역은 삭제할 수 없습니다.");
                return true;
            }
        }
        return false;
    }

    private static boolean containsStation(String inputName, List<Station> stationList) {
        for (Station station : stationList) {
            if (station.getName().equals(inputName)) {
                return true;
            }
        }
        return false;
    }

    private static boolean enrollStation(InputTaker inputTaker) {
        String inputName;
        inputName = inputTaker.takeAddingStationName();
        if (isValidStationName(inputName)) {
            StationRepository.addStation(new Station(inputName));
            System.out.println();
            System.out.println("[INFO] 지하철 역이 등록되었습니다.");
            System.out.println();
            return true;
        }
        return false;
    }

    private static boolean isValidStationName(String inputStationName) {
        try {
            Validator.checkIfValidStationName(inputStationName);
        } catch (IllegalArgumentException e) {
            System.out.println();
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
            System.out.println();
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }
}
