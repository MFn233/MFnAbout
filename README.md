<p align="center">
  <a href="https://github.com/maple-days"><img src="https://github.com/maple-days.png?size=96" width="56" alt="Maple Days"></a>
</p>

<p align="center">
  <sub><b>Maple Days</b> · 《打脸日常》整合包附属 mod · Minecraft 1.12.2</sub>
</p>

# MFn's About

给 Minecraft **1.12.2** 主菜单加一个可配置的「关于」按钮。点开后是一个支持颜色代码的富文本界面。

为《打脸日常》整合包而写，但可以独立使用。

## 特性

- 主菜单「退出游戏」右侧出现一个 **20×20 的「!」按钮**，尺寸与间距和原版语言按钮一致
- 悬停显示 tooltip「关于」（中英双语）
- 点开是一个富文本界面：标题 + 正文 + 底部「好的」按钮
- 正文支持 `&` 颜色代码：`&6` 金色、`&c` 红色、`&l` 加粗、`&o` 斜体、`&n` 下划线
- 内容超长时可用滚轮滚动，右下角显示进度
- **按钮位置**与**全部文字内容**都在配置文件里改，无需重新编译

## 配置

首次启动会在 `config/mfnabout.cfg` 生成：

```ini
[button]
    B:enabled=true
    B:useAbsolutePosition=false   # false = 贴「退出游戏」右侧（跟随 GUI 缩放）
    I:x=0                          # 仅 useAbsolutePosition=true 时生效
    I:y=0

[content]
    S:title=关于
    S:body <
        &6MFn's About &7v1.0.0
        ...
     >
```

`body` 是**字符串列表**，一行一条，不要写换行符。

## 兼容性

| 项 | 值 |
|---|---|
| Minecraft | 1.12.2 |
| 加载器 | Forge 14.23.5.2847+ / Cleanroom |
| 环境 | **仅客户端**（`clientSideOnly = true`） |
| 前置 | 无 |
| 编译目标 | Java 8 |

## 构建

需要 JDK 25 运行 Gradle，编译目标为 Java 8。

```bash
./gradlew build
# 产物：build/libs/mfnabout-<version>.jar
```

## 实现说明

- 按钮通过 `GuiScreenEvent.InitGuiEvent.Post` 注入 `GuiMainMenu`，点击处理走 `ActionPerformedEvent.Post`
- 图标是独立贴图 `assets/mfnabout/textures/gui/about_icon.png`（16×16）。
  **不能用 `drawTexturedModalRect`**（它按 256×256 图集采样），
  必须用 `drawModalRectWithCustomSizedTexture` 指定真实尺寸
- 默认坐标由原版布局镜像推出：「退出游戏」在 `(width/2 + 2, height/4 + 48 + 84)`、98×20，
  语言按钮 20×20 在左侧、间距 4 → 「关于」放在 `width/2 + 104`

## 许可证

MIT
