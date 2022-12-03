package subway.common;

public class Constant {
    /* 메인 메뉴 */

    // 메뉴 제목
    public static final String MAIN_MENU_TITLE = "## 메인 화면";

    // 메뉴
    public static final String MAIN_STATION_MENU = "1";
    public static final String MAIN_LINE_MENU = "2";
    public static final String MAIN_SECTION_MENU = "3";
    public static final String MAIN_LINE_MAP_MENU = "4";
    public static final String MAIN_END_MENU = "Q";
    public static final String[] MAIN_MENU_OPTIONS =
            new String[]{MAIN_STATION_MENU, MAIN_LINE_MENU, MAIN_SECTION_MENU, MAIN_LINE_MAP_MENU, MAIN_END_MENU};

    // 메뉴별 안내
    public static final String MAIN_STATION_MESSAGE = "역 관리";
    public static final String MAIN_LINE_MESSAGE = "노선 관리";
    public static final String MAIN_SECTION_MESSAGE = "구간 관리";
    public static final String MAIN_LINE_MAP_MESSAGE = "지하철 노선도 출력";
    public static final String MAIN_END_MESSAGE = "종료";
    public static final String[] MAIN_MENU_MESSAGES =
            new String[]{MAIN_STATION_MESSAGE, MAIN_LINE_MESSAGE, MAIN_SECTION_MESSAGE, MAIN_LINE_MAP_MESSAGE, MAIN_END_MESSAGE};


    /* 역 메뉴 */
}
