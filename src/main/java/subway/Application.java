package subway;

import subway.domain.*;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // TODO: 프로그램 구현
        List<Line> lines = LineRepository.lines();
        System.out.println("len of lines : " + lines.size());
        Map<Line, List<Station>> lineListMap = LineStationRepository.lineStations();
        System.out.println("len of lineMap: " + lineListMap.size());
        for (Line line : lineListMap.keySet()) {
            System.out.println("line = " + line.getName());
            List<Station> stations = lineListMap.get(line);
            for (Station station : stations) {
                System.out.println("station = " + station);
            }
        }
        // static 때 할당해줘도 제거는 가능한가?
//        boolean wasDeleted = StationRepository.deleteStation("강남역");
//        System.out.println("wasDeleted = " + wasDeleted);
        LineStationRepository.deleteStationInLine("2호선", "강남역" );
        System.out.println("len of lineMap after delete: " + lineListMap.size());
        for (Line line : lineListMap.keySet()) {
            System.out.println("line = " + line.getName());
            List<Station> stations = lineListMap.get(line);
            for (Station station : stations) {
                System.out.println("station = " + station);
            }
        }

    }
}
