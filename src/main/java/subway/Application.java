package subway;

import subway.domain.InputTaker;
import subway.domain.Dispatcher;

import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        final Scanner scanner = new Scanner(System.in);

        // TODO: 프로그램 구현
        Dispatcher mainController = new Dispatcher(new InputTaker(scanner));
        mainController.run();
    }
}
