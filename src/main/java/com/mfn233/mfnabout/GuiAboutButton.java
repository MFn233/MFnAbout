package com.mfn233.mfnabout;

import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.renderer.GlStateManager;
import net.minecraft.client.resources.I18n;
import net.minecraft.util.ResourceLocation;
import net.minecraftforge.fml.client.config.GuiUtils;

import java.util.Collections;

/**
 * 20x20 的「!」按钮：背板用原版按钮材质，中间叠一张图标。
 */
public class GuiAboutButton extends GuiButton {

    private static final ResourceLocation ICON =
            new ResourceLocation(Tags.MOD_ID, "textures/gui/about_icon.png");

    public GuiAboutButton(int id, int x, int y) {
        super(id, x, y, 20, 20, "");
    }

    @Override
    public void drawButton(Minecraft mc, int mouseX, int mouseY, float partialTicks) {
        if (!this.visible) {
            return;
        }
        super.drawButton(mc, mouseX, mouseY, partialTicks);

        mc.getTextureManager().bindTexture(ICON);
        GlStateManager.enableBlend();
        GlStateManager.tryBlendFuncSeparate(770, 771, 1, 0);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        // 注意：drawTexturedModalRect 按 256x256 图集采样，用于 16x16 独立贴图会取错区域。
        // 必须用 drawModalRectWithCustomSizedTexture 指定真实贴图尺寸。
        drawModalRectWithCustomSizedTexture(this.x + 2, this.y + 2, 0.0F, 0.0F, 16, 16, 16.0F, 16.0F);
        GlStateManager.color(1.0F, 1.0F, 1.0F, 1.0F);
        GlStateManager.disableBlend();

        if (this.isMouseOver()) {
            GuiUtils.drawHoveringText(
                    Collections.singletonList(I18n.format("mfnabout.button.tooltip")),
                    mouseX, mouseY, mc.currentScreen.width, mc.currentScreen.height, -1,
                    mc.fontRenderer);
        }
    }
}