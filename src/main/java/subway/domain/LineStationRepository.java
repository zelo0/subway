package subway.domain;

import java.util.*;

public class LineStationRepository {
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

    public static void deleteStationInLine(String lineName, String stationName) {
        lineStations.get(LineRepository.getLineByName(lineName)).remove(StationRepository.getStationByName(stationName));
    }
}
