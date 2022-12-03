package subway;

import subway.domain.Line;
import subway.domain.Station;
import subway.repository.LineRepository;
import subway.repository.StationRepository;

import java.util.List;

import static subway.common.Constant.DETAIL_BACK_MENU;

public class Validator {

    public static void checkIfValidMenu(String input, String[] acceptedStringList) {
        if (input.equals(DETAIL_BACK_MENU)) {
            return;
        }
        for (String acceptedString : acceptedStringList) {
            if (input.equalsIgnoreCase(acceptedString)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public static void checkIfValidStationName(String stationName) {
        if (stationName.length() < 2) {
            throw new IllegalArgumentException();
        }
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void checkIfValidLineName(String lineName) {
        if (lineName.length() < 2) {
            throw new IllegalArgumentException();
        }
        List<Line> lines = LineRepository.lines();
        for (Line line : lines) {
            if (line.getName().equals(lineName)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static void checkIfExistsStation(String stationName) {
        List<Station> stations = StationRepository.stations();
        for (Station station : stations) {
            if (station.getName().equals(stationName)) {
                return;
            }
        }
        throw new IllegalArgumentException();
    }

    public static boolean isNumber(String input) {
        try {
            Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("[ERROR] 순서는 숫자여야 합니다.");
            return false;
        }
        return true;
    }

    public static boolean isNotValidMenu(String input, String[] menuOptions) {
        try {
            checkIfValidMenu(input, menuOptions);
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return true;
        }
        return false;
    }
}
