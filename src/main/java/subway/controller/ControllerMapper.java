package subway.controller;

import subway.InputTaker;
import subway.annot.MapController;

import static subway.common.Constant.*;

public class ControllerMapper {

    @MapController(MAIN_STATION_MENU)
    public void stationController(InputTaker inputTaker) {
        StationController.getInstance().run(inputTaker);
    }

    @MapController(MAIN_LINE_MENU)
    public void lineController(InputTaker inputTaker) {
        LineController.getInstance().run(inputTaker);
    }

    @MapController(MAIN_SECTION_MENU)
    public void lineMapController(InputTaker inputTaker) {
        LineMapController.getInstance().run(inputTaker);
    }

    @MapController(MAIN_LINE_MAP_MENU)
    public void printMap(InputTaker inputTaker) {
        LineMapController.getInstance().printMap();
    }
}
