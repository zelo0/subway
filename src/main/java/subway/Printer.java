package subway;

import static subway.common.Constant.*;
import static subway.common.Constant.DETAIL_BACK_MESSAGE;

public class Printer {

    public static void printMainMenu(String title, String[] menus, String[] messages) {
        System.out.println(title);
        for (int i = 0; i < menus.length; i++) {
            System.out.println(menus[i] + ". " + messages[i]);
        }
    }

    public static void printMessage(String message) {
        System.out.println(message);
    }

    public static void printDetailMenu(String typeName, String[] menus, String[] messages) {
        System.out.println();
        System.out.println(DETAIL_TITLE_HEAD + typeName + DETAIL_TITLE_TAIL);
        for (int i = 0; i < menus.length; i++) {
            System.out.println(menus[i] + ". " + typeName + messages[i]);
        }
        System.out.println(DETAIL_BACK_MENU + ". " + DETAIL_BACK_MESSAGE);
    }
}
