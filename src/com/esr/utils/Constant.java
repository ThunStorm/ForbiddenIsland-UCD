package com.esr.utils;

import java.awt.*;

public abstract class Constant {

    public static boolean TIMER_STOP_ON_OFF = false;//Main switch of all timers. All timers stop when this turns to ture
    public final static int FRAME_WIDTH = 1050;//主窗体宽
    public final static int FRAME_HEIGHT = 950;//主窗体高


    public final static int ADVENTURER_WIDTH = 73;
    public final static int ADVENTURER_HEIGHT = 116;
    public final static int FLOOD_WIDTH = 120;
    public final static int FLOOD_HEIGHT = 82;
    public final static int TREASURE_WIDTH = 120;
    public final static int TREASURE_HEIGHT = 82;
    public final static int TILE_WIDTH = 110;
    public final static int TILE_HEIGHT = 110;
    public final static int BOARD_WIDTH = 700;
    public final static int BOARD_HEIGHT = 700;
    public final static int WATER_METER_WIDTH = 120;
    public final static int WATER_METER_HEIGHT = 350;

    public final static int ELEMENT_SIZE = 24;//素材原始尺寸
    public final static String RESOURCES_PATH = "src/com/esr/resources/image/";//资源加载路径头

    public final static int GROUND_CACHE_COUNT = 10;//地面组缓存个数
    public final static int GROUND_LEVEL_BASE_LINE = 350;//地面基础线


}

