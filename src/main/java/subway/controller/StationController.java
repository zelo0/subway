package subway.controller;

import subway.InputTaker;
import subway.Printer;
import subway.Validator;
import subway.domain.*;
import subway.repository.LineMapRepository;
import subway.repository.StationRepository;

import java.util.List;
import java.util.Map;

import static subway.common.Constant.*;

public class StationController extends AbstractController {
    public static final String NAME_MANAGING = "역";
    public static final String[] MENUS = new String[]{DETAIL_ENROLL_MENU, DETAIL_DELETE_MENU, DETAIL_VIEW_MENU};
    public static final String[] MESSAGES = new String[]{DETAIL_ENROLL_MESSAGE_TAIL, DETAIL_DELETE_MESSAGE_TAIL, DETAIL_VIEW_MESSAGE_TAIL};

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

    public void run(InputTaker inputTaker) {
        super.run(inputTaker, MENUS);
    }

    @Override
    protected boolean enroll() {
        String inputName;
        inputName = inputTaker.takeInputWithMessage("## 등록할 역 이름을 입력하세요.");
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
    protected boolean delete() {
        String inputName = inputTaker.takeInputWithMessage("## 삭제할 역 이름을 입력하세요.");
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
    protected boolean show() {
        System.out.println();
        System.out.println("## 역 목록");
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            System.out.println("[INFO] " + station.getName());
        }
        System.out.println();
        return true;
    }

    @Override
    protected void printMenu() {
        Printer.printDetailMenu(NAME_MANAGING, MENUS, MESSAGES);
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
