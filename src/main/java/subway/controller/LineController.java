package subway.controller;

import subway.InputTaker;
import subway.Printer;
import subway.Validator;
import subway.domain.*;
import subway.repository.LineMapRepository;
import subway.repository.LineRepository;

import java.util.List;

import static subway.common.Constant.*;

public class LineController extends AbstractController {
    public static final String NAME_MANAGING = "노선";
    public static final String[] MENUS = new String[]{DETAIL_ENROLL_MENU, DETAIL_DELETE_MENU, DETAIL_VIEW_MENU};
    public static final String[] MESSAGES = new String[]{DETAIL_ENROLL_MESSAGE_TAIL, DETAIL_DELETE_MESSAGE_TAIL, DETAIL_VIEW_MESSAGE_TAIL};

    /* 싱글톤 */
    private static LineController instance = null;

    private LineController() {
    }

    public static LineController getInstance() {
        if (instance == null) {
            instance = new LineController();
        }
        return instance;
    }

    public void run(InputTaker inputTaker) {
        super.run(inputTaker, MENUS);
    }

    @Override
    public boolean enroll() {
        String inputName = inputTaker.takeInputWithMessage("## 등록할 노선 이름을 입력하세요.");
        if (isValidLineNameToEnroll(inputName)) {
            String topEndStation = inputTaker.takeInputWithMessage("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
            String bottomEndStation = inputTaker.takeInputWithMessage("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
            if (isValidStation(topEndStation) && isValidStation(bottomEndStation)) {
                LineRepository.addLine(new Line(inputName));
                LineMapRepository.addLine(inputName, topEndStation, bottomEndStation);
                Printer.printMessage("[INFO] 지하철 노선이 등록되었습니다.");
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean delete() {
        String inputName = inputTaker.takeInputWithMessage("## 삭제할 역 이름을 입력하세요.");
        return LineMapRepository.deleteLineInMap(inputName);
    }

    @Override
    public boolean show() {
        System.out.println();
        System.out.println("## 노선 목록");
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            System.out.println("[INFO] " + line.getName());
        }
        System.out.println();
        return true;
    }

    @Override
    public void printMenu() {
        Printer.printDetailMenu(NAME_MANAGING, MENUS, MESSAGES);
    }

    private static boolean isValidStation(String stationName) {
        try {
            Validator.checkIfExistsStation(stationName);
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("[ERROR] 존재하지 않는 역입니다.");
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
}
