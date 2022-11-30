package subway.domain;

import java.util.*;

public class LineMapRepository {
    private static final HashMap<Line, List<Station>> lineStations = new HashMap<>();

    static {
        initializeLinesWithStations();
    }

    private static void initializeLinesWithStations() {
        addStationsInLine("2호선", new String[]{"강남역", "교대역", "역삼역"});
        addStationsInLine("3호선", new String[]{"교대역", "남부터미널역", "양재역", "매봉역"});
        addStationsInLine("신분당선", new String[]{"강남역", "양재역", "양재시민의숲역"});
    }

    private static void addStationsInLine(String lineName, String[] stationNames) {
        LinkedList<Station> stationList = new LinkedList<>();
        for (String stationName : stationNames) {
            stationList.addLast(StationRepository.getStationByName(stationName));
        }

        Line line = LineRepository.getLineByName(lineName);
        lineStations.put(line, stationList);
    }

    public static Map<Line, List<Station>> lineStations() {
        return Collections.unmodifiableMap(lineStations);
    }

    public static void addLine(String lineName, String topEndStation, String bottomEndStation) {
        LinkedList<Station> stationList = new LinkedList<>();
        Station topStation = StationRepository.getStationByName(topEndStation);
        Station bottomStation = StationRepository.getStationByName(bottomEndStation);
        stationList.addLast(topStation);
        stationList.addLast(bottomStation);
        lineStations.put(LineRepository.getLineByName(lineName), stationList);
    }

    public static boolean deleteLineInMap(String lineName) {
        Line lineByName = LineRepository.getLineByName(lineName);
        if (!lineStations.containsKey(lineByName)) {
            return false;
        }
        lineStations.remove(lineByName);
        LineRepository.deleteLineByName(lineName);
        return true;
    }
}
