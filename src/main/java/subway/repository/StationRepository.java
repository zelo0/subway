package subway.repository;

import subway.domain.Station;

import java.util.*;

public class StationRepository {
    private static final List<Station> stations = new ArrayList<>();

    static {
        initializeStations();
    }

    private static void initializeStations() {
        stations.add(new Station("교대역"));
        stations.add(new Station("강남역"));
        stations.add(new Station("역삼역"));
        stations.add(new Station("남부터미널역"));
        stations.add(new Station("양재역"));
        stations.add(new Station("양재시민의숲역"));
        stations.add(new Station("매봉역"));
    }

    public static List<Station> stations() {
        return Collections.unmodifiableList(stations);
    }

    public static void addStation(Station station) {
        stations.add(station);
    }

    public static boolean deleteStation(String name) {
        return stations.removeIf(station -> Objects.equals(station.getName(), name));
    }

    public static Station getStationByName(String name) {
        return stations.stream().filter(station -> station.getName().equals(name))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    public static boolean isNotExistStation(String name) {
        try {
            stations.stream().filter(station -> station.getName().equals(name))
                    .findFirst().orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println("[ERROR] 존재하지 않는 역입니다.");
            return true;
        }
        return false;
    }
}
