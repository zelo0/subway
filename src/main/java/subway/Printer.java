package subway;

public class Printer {

    public static void printMenu(String title, String[] menus, String[] messages) {
        System.out.println(title);
        for (int i = 0; i < menus.length; i++) {
            System.out.println(menus[i] + ". " + messages[i]);
        }
    }
}
