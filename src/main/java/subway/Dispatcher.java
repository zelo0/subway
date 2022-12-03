package subway;

import subway.annot.MapController;
import subway.controller.ControllerMapper;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static subway.common.Constant.*;


public class Dispatcher {
    private InputTaker inputTaker;

    public Dispatcher(InputTaker inputTaker) {
        this.inputTaker = inputTaker;
    }

    public void run() {
        String menuInput;
        while (true) {
            printMenu();
            menuInput = requestMenuSelection();
            if (menuInput.equalsIgnoreCase(MAIN_END_STR)) {
                break;
            }
            try {
                mapToController(menuInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void mapToController(String menuInput) throws InvocationTargetException, IllegalAccessException {
        ControllerMapper controllerMapper = new ControllerMapper();
        Method[] methods = ControllerMapper.class.getDeclaredMethods();
        for (Method method : methods) {
            String annotationValue = method.getDeclaredAnnotation(MapController.class).value();
            if (annotationValue.equals(menuInput)) {
                method.invoke(controllerMapper, inputTaker);
                break;
            }
        }
    }

    private void printMenu() {
        System.out.println("## 메인 화면");
        System.out.println(MAIN_STATION_STR + ". 역 관리");
        System.out.println(MAIN_LINE_STR + ". 노선 관리");
        System.out.println(MAIN_SECTION_STR + ". 구간 관리");
        System.out.println(MAIN_LINE_MAP_STR + ". 지하철 노선도 출력");
        System.out.println(MAIN_END_STR + ". 종료");
    }

    private String requestMenuSelection() {
        String input;
        do {
            input = inputTaker.takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (Validator.isNotValidFunction(input, MAIN_MENU_OPTIONS));
        return input.trim();
    }
}
