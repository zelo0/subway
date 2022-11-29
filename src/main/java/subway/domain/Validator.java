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
}
