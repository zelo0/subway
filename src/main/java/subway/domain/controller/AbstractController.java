package subway.domain.controller;

import subway.domain.InputTaker;
import subway.domain.Validator;

public abstract class AbstractController {

    private static final String ENROLL_STR = "1";
    private static final String DELETE_STR = "2";
    private static final String VIEW_STR = "3";
    private static final String BACK_STR = "B";

    public void run(InputTaker inputTaker, String typeName) {
        while (true) {
            printMenu(typeName);
            String menuInput = requestMenuSelection(inputTaker);
            if (menuInput.equals(ENROLL_STR) && enroll(inputTaker)) {
                break;
            }
            if (menuInput.equals(DELETE_STR) && delete(inputTaker)) {
                break;
            }
            if (menuInput.equals(VIEW_STR)) {
                show();
                break;
            }
            if (menuInput.equals(BACK_STR)) {
                break;
            }
        }
    }

    // 추상 메소드
    abstract boolean enroll(InputTaker inputTaker);
    abstract boolean delete(InputTaker inputTaker);
    abstract void show();

    private void printMenu(String typeName) {
        System.out.println();
        System.out.println("## " + typeName + " 관리 화면");
        System.out.println(ENROLL_STR + ". " + typeName + " 등록");
        System.out.println(DELETE_STR + ". " + typeName + " 삭제");
        System.out.println(VIEW_STR + ". " + typeName + " 조회");
        System.out.println(BACK_STR + ". 돌아가기");
    }

    private String requestMenuSelection(InputTaker inputTaker) {
        String input;
        do {
            input = inputTaker.takeMenuInput();
        } while (!isValidFunction(input));
        return input.trim();
    }

    private boolean isValidFunction(String input) {
        try {
            Validator.checkIfValidMenu(input, new String[]{ENROLL_STR, DELETE_STR, VIEW_STR, BACK_STR});
        } catch (IllegalArgumentException e) {
            System.out.println();
            System.out.println("[ERROR] 메뉴에서 선택해주세요");
            System.out.println();
            return false;
        }
        return true;
    }
}
