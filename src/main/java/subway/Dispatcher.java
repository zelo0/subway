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
            Printer.printMainMenu(MAIN_MENU_TITLE, MAIN_MENU_OPTIONS, MAIN_MENU_MESSAGES);
            menuInput = inputTaker.takeMenuSelection(MAIN_MENU_OPTIONS);
            if (menuInput.equalsIgnoreCase(MAIN_END_MENU)) {
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
}
