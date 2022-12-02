package subway.domain.controller;

import subway.domain.*;

import java.util.List;

public class LineController extends AbstractController {
    public static final String NAME_MANAGING = "노선";

    @Override
    boolean enroll(InputTaker inputTaker) {
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

    @Override
    boolean delete(InputTaker inputTaker) {
        String inputName = inputTaker.takeDeletingStationName();
        return LineMapRepository.deleteLineInMap(inputName);
    }

    @Override
    void show() {
        System.out.println();
        System.out.println("## 노선 목록");
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            System.out.println("[INFO] " + line.getName());
        }
        System.out.println();
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
