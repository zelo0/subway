package subway.domain;

import sun.security.jgss.GSSCaller;

import java.util.Scanner;

public class InputTaker {
    private Scanner scanner;

    public InputTaker(Scanner scanner) {
        this.scanner = scanner;
    }

    public String takeMenuInput() {
        System.out.println();
        System.out.println("## 원하는 기능을 선택하세요.");
        return scanner.nextLine();
    }

    public String takeAddingStationName() {
        System.out.println();
        System.out.println("## 등록할 역 이름을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeDeletingStationName() {
        System.out.println();
        System.out.println("## 삭제할 역 이름을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeTopEndStation() {
        System.out.println();
        System.out.println("## 등록할 노선의 상행 종점역 이름을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeBottomEndStation() {
        System.out.println();
        System.out.println("## 등록할 노선의 하행 종점역 이름을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeLineName() {
        System.out.println();
        System.out.println("## 노선을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeStationName() {
        System.out.println();
        System.out.println("## 역이름을 입력하세요.");
        return scanner.nextLine();
    }

    public String takeOrder() {
        System.out.println();
        System.out.println("## 순서를 입력하세요.");
        return scanner.nextLine();
    }
}
