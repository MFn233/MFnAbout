package com.mfn233.mfnabout;

import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import org.lwjgl.input.Mouse;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 富文本界面：每行支持 & 颜色代码，内容超长时可滚轮滚动。
 * 底部一个「好的」按钮返回上一界面。
 */
public class GuiAbout extends GuiScreen {

    private final GuiScreen parent;
    private final List<String> lines = new ArrayList<String>();
    private final String title;

    private int scroll = 0;
    private int panelX, panelY, panelW, panelH;
    private int contentTop;

    public GuiAbout(GuiScreen parent) {
        this.parent = parent;
        this.title = colorize(AboutConfig.title);
        for (String line : AboutConfig.body) {
            this.lines.add(colorize(line));
        }
    }

    private static String colorize(String s) {
        return s == null ? "" : s.replace('&', '\u00a7');
    }

    @Override
    public void initGui() {
        this.buttonList.clear();
        this.panelW = Math.min(this.width - 40, 340);
        this.panelH = Math.min(this.height - 60, 220);
        this.panelX = (this.width - panelW) / 2;
        this.panelY = (this.height - panelH) / 2 - 10;
        this.contentTop = panelY + 30;
        this.buttonList.add(new GuiButton(0, this.width / 2 - 60, panelY + panelH + 8, 120, 20,
                I18n.format("mfnabout.gui.ok")));
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        this.drawDefaultBackground();

        // 面板
        drawRect(panelX - 1, panelY - 1, panelX + panelW + 1, panelY + panelH + 1, 0xFF000000);
        drawRect(panelX, panelY, panelX + panelW, panelY + panelH, 0xCC101418);

        // 标题
        this.fontRenderer.drawString(this.title, panelX + 10, panelY + 9, 0xFFFFFF);
        drawRect(panelX + 8, panelY + 22, panelX + panelW - 8, panelY + 23, 0xFF3A4A5A);

        // 正文
        int lineHeight = this.fontRenderer.FONT_HEIGHT + 3;
        int maxLines = (panelH - 36) / lineHeight;
        scroll = Math.max(0, Math.min(scroll, Math.max(0, lines.size() - maxLines)));

        for (int i = 0; i < maxLines && i + scroll < lines.size(); i++) {
            this.fontRenderer.drawString(lines.get(i + scroll), panelX + 10,
                    contentTop + i * lineHeight, 0xE0E0E0);
        }

        // 滚动提示
        if (lines.size() > maxLines) {
            String hint = (scroll + maxLines) + " / " + lines.size();
            this.fontRenderer.drawString(hint, panelX + panelW - 10 - this.fontRenderer.getStringWidth(hint),
                    panelY + panelH - 12, 0xFF707070);
        }

        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    public void handleMouseInput() throws IOException {
        super.handleMouseInput();
        int dw = Mouse.getEventDWheel();
        if (dw != 0) {
            scroll += dw > 0 ? -1 : 1;
        }
    }

    @Override
    protected void actionPerformed(GuiButton button) throws IOException {
        if (button.id == 0) {
            this.mc.displayGuiScreen(this.parent);
        }
    }

    @Override
    public boolean doesGuiPauseGame() {
        return false;
    }
}