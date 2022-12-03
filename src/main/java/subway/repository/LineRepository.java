package subway.repository;

import subway.domain.Line;

import java.util.*;

public class LineRepository {
    private static final List<Line> lines = new ArrayList<>();

    static {
        initializeLines();
    }

    private static void initializeLines() {
        lines.add(new Line("2호선"));
        lines.add(new Line("3호선"));
        lines.add(new Line("신분당선"));
    }

    public static List<Line> lines() {
        return Collections.unmodifiableList(lines);
    }

    public static void addLine(Line line) {
        lines.add(line);
    }

    public static boolean deleteLineByName(String name) {
        return lines.removeIf(line -> Objects.equals(line.getName(), name));
    }

    public static Line getLineByName(String name) {
        return lines.stream().filter(line -> line.getName().equals(name))
                .findFirst().orElseThrow(NoSuchElementException::new);
    }

    public static boolean isNotExistLine(String name) {
        try {
            lines.stream().filter(line -> line.getName().equals(name))
                    .findFirst().orElseThrow(NoSuchElementException::new);
        } catch (NoSuchElementException e) {
            System.out.println("[ERROR] 존재하지 않는 노선입니다.");
            return true;
        }
        return false;
    }
}
