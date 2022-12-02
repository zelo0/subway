package subway.domain.controller;

import subway.domain.*;

import java.util.List;
import java.util.Map;

public class StationController extends AbstractController {
    public static final String NAME_MANAGING = "역";

    /* 싱글톤 */
    private static StationController instance = null;

    private StationController() {
    }

    public static StationController getInstance() {
        if (instance == null) {
            instance = new StationController();
        }
        return instance;
    }

    @Override
    boolean enroll(InputTaker inputTaker) {
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

    @Override
    boolean delete(InputTaker inputTaker) {
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

    @Override
    void show() {
        System.out.println();
        System.out.println("## 역 목록");
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            System.out.println("[INFO] " + station.getName());
        }
        System.out.println();
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
}
