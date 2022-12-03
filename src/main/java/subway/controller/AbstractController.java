package subway.controller;

import subway.InputTaker;
import subway.Validator;

public abstract class AbstractController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String VIEW_STR = "3";
    private static final String BACK_STR = "B";
    private static final String[] menuOptions =
            new String[]{ENROLL_STR, DELETE_STR, VIEW_STR, BACK_STR};

    InputTaker inputTaker;

    // 추상 메소드
    abstract boolean enroll();
    abstract boolean delete();
    abstract void show();

    protected void run(InputTaker inputTaker, String typeName) {
        this.inputTaker = inputTaker;
        while (true) {
            printMenu(typeName);
            String menuInput = requestMenuSelection();
            if (isEnd(menuInput)) {
                break;
            }
        }
    }

    private boolean isEnd(String menuInput) {
        if (menuInput.equals(ENROLL_STR) && enroll()) {
            return true;
        }
        if (menuInput.equals(DELETE_STR) && delete()) {
            return true;
        }
        if (menuInput.equals(VIEW_STR)) {
            show();
            return true;
        }
        return menuInput.equals(BACK_STR);
    }

    private void printMenu(String typeName) {
        System.out.println();
        System.out.println("## " + typeName + " 관리 화면");
        System.out.println(ENROLL_STR + ". " + typeName + " 등록");
        System.out.println(DELETE_STR + ". " + typeName + " 삭제");
        System.out.println(VIEW_STR + ". " + typeName + " 조회");
        System.out.println(BACK_STR + ". 돌아가기");
    }

    private String requestMenuSelection() {
        String input;
        do {
            input = inputTaker.takeInputWithMessage("## 원하는 기능을 선택하세요.");
        } while (Validator.isNotValidMenu(input, menuOptions));
        return input.trim();
    }
}
