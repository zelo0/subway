package subway.controller;

import subway.InputTaker;
import subway.annot.MapController;
import subway.annot.Task;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import static subway.common.Constant.*;

public abstract class AbstractController {

    InputTaker inputTaker;

    // 추상 메소드
    @Task(DETAIL_ENROLL_MENU)
    protected abstract boolean enroll();

    @Task(DETAIL_DELETE_MENU)
    protected abstract boolean delete();

    @Task(DETAIL_VIEW_MENU)
    protected abstract boolean show();

    @Task(DETAIL_BACK_MENU)
    protected boolean back() {
        return true;
    }

    protected abstract void printMenu();

    protected void run(InputTaker inputTaker, String[] menus) {
        boolean isEnd = false;
        this.inputTaker = inputTaker;

        while (!isEnd) {
            printMenu();    // 구현된 추상 메소드 실행
            String menuInput = inputTaker.takeMenuSelection(menus);
            try {
                isEnd = proceed(menuInput);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private boolean proceed(String menuInput) throws Exception {
        Method[] methods = AbstractController.class.getDeclaredMethods();
        for (Method method : methods) {
            Task task = method.getAnnotation(Task.class);
            if (task == null) {
                continue;
            }
            if (task.value().equals(menuInput)) {
                return (boolean) method.invoke(this);
            }
        }
        return false;
    }
}
