package subway.domain;

import subway.domain.controller.LineController;
import subway.domain.controller.LineMapController;
import subway.domain.controller.StationController;


public class Dispatcher {
    private InputTaker inputTaker;

    private static final String STATION_STR = "1";
    private static final String LINE_STR = "2";
    private static final String SECTION_STR = "3";
    private static final String LINE_MAP_STR = "4";
    private static final String END_STR = "Q";

    public Dispatcher(InputTaker inputTaker) {
        this.inputTaker = inputTaker;
    }

    public void run() {
        String menuInput;
        do {
            printMenu();
            menuInput = requestMenuSelection();
            mapToController(menuInput);
        } while (!menuInput.equalsIgnoreCase(END_STR));
    }

    private void mapToController(String menuInput) {
        if (menuInput.equals(STATION_STR)) {
            StationController.getInstance().run(inputTaker, StationController.NAME_MANAGING);
            return;
        }
        if (menuInput.equals(LINE_STR)) {
            LineController.getInstance().run(inputTaker, LineController.NAME_MANAGING);
            return;
        }
        if (menuInput.equals(SECTION_STR)) {
            LineMapController.run(inputTaker);
            return;
        }
        if (menuInput.equals(LINE_MAP_STR)) {
            LineMapController.printMap();
        }
    }

    private void printMenu() {
        System.out.println("## 메인 화면");
        System.out.println(STATION_STR + ". 역 관리");
        System.out.println(LINE_STR + ". 노선 관리");
        System.out.println(SECTION_STR + ". 구간 관리");
        System.out.println(LINE_MAP_STR + ". 지하철 노선도 출력");
        System.out.println(END_STR + ". 종료");
    }

    private String requestMenuSelection() {
        String input;
        do {
            input = inputTaker.takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (!isValidFunction(input));

        return input.trim();
    }

    private boolean isValidFunction(String input) {
        try {
            Validator.checkIfValidMenu(input, new String[]{STATION_STR, LINE_STR, SECTION_STR, LINE_MAP_STR, END_STR});
        } catch (IllegalArgumentException e) {
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }


}
