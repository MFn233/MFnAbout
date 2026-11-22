package com.mfn233.mfnabout;

import net.minecraftforge.common.config.Configuration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * config/mfnabout.cfg
 *
 * [button]  enABLED / position / x / y
 * [content] title / body（每行一条，可用 & 颜色代码）
 */
public final class AboutConfig {

    public static boolean enabled = true;
    /** false = 贴在「退出游戏」右侧（默认）；true = 用绝对坐标 x/y */
    public static boolean absolute = false;
    public static int x = 0;
    public static int y = 0;

    public static String title = "关于";
    public static List<String> body = new ArrayList<String>();

    private AboutConfig() {
    }

    public static void load(File file) {
        Configuration cfg = new Configuration(file);
        cfg.load();

        enabled = cfg.getBoolean("enabled", "button", true,
                "是否在主菜单显示「关于」按钮");
        absolute = cfg.getBoolean("useAbsolutePosition", "button", false,
                "false = 默认贴在「退出游戏」按钮右侧（推荐）；true = 使用下面的 x/y 绝对坐标");
        x = cfg.getInt("x", "button", 0, -4000, 4000,
                "按钮左上角的 X（仅在 useAbsolutePosition=true 时生效）");
        y = cfg.getInt("y", "button", 0, -4000, 4000,
                "按钮左上角的 Y（仅在 useAbsolutePosition=true 时生效）");

        title = cfg.getString("title", "content", "关于",
                "界面标题；支持 & 颜色代码");
        String[] lines = cfg.getStringList("body", "content", new String[]{
                "&6MFn's About &7v1.0.0",
                "",
                "&f这是一个富文本测试界面。",
                "&a支持颜色代码：&c红 &e黄 &a绿 &9蓝 &d紫 &7灰",
                "&l也可以加粗&r、&o斜体&r、&n下划线&r。",
                "",
                "&7按钮位置与这段文字都在 config/mfnabout.cfg 里改。",
                "&7默认位置是「退出游戏」右侧，和语言按钮同尺寸。"
        }, "界面正文，一行一条；支持 & 颜色代码");
        body.clear();
        for (String line : lines) {
            body.add(line);
        }

        if (cfg.hasChanged()) {
            cfg.save();
        }
    }
}