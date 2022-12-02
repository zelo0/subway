package subway.domain;

import java.util.List;

public class Validator {

    public static void checkIfValidMenu(String input, String[] acceptedStringList) {
        String trimmedInput = input.trim();
        for (String acceptedString : acceptedStringList) {
            if (trimmedInput.equalsIgnoreCase(acceptedString)) {
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
            return false;
        }
        return true;
    }
}
