package subway.controller;

import jdk.internal.util.xml.impl.Input;
import subway.InputTaker;
import subway.Printer;
import subway.Validator;
import subway.repository.LineMapRepository;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

import static subway.common.Constant.*;

public class LineMapController extends AbstractController {
    public static final String NAME_MANAGING = "구간";
    public static final String[] MENUS = new String[]{DETAIL_ENROLL_MENU, DETAIL_DELETE_MENU};
    public static final String[] MESSAGES = new String[]{DETAIL_ENROLL_MESSAGE_TAIL, DETAIL_DELETE_MESSAGE_TAIL};

    /* 싱글톤 */
    private static LineMapController instance = null;

    private LineMapController() {
    }

    public static LineMapController getInstance() {
        if (instance == null) {
            instance = new LineMapController();
        }
        return instance;
    }

    @Override
    protected boolean delete() {
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

    @Override
    protected boolean enroll() {
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

    @Override
    protected boolean show() {
        return true;
    }

    @Override
    protected void printMenu() {
        Printer.printDetailMenu(NAME_MANAGING, MENUS, MESSAGES);
    }

    public void run(InputTaker inputTaker) {
        super.run(inputTaker, MENUS);
    }

    public void printMap() {
        System.out.println();
        System.out.println("## 지하철 노선도");
        LineMapRepository.printMap();
    }
}
