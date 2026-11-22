package com.mfn233.mfnabout;

import net.minecraft.client.gui.GuiMainMenu;
import net.minecraftforge.client.event.GuiScreenEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.relauncher.Side;

@Mod.EventBusSubscriber(modid = Tags.MOD_ID, value = Side.CLIENT)
public final class MainMenuHandler {

    private static final int BUTTON_ID = 0x4D46;

    private MainMenuHandler() {
    }

    @SubscribeEvent
    public static void onInitGui(GuiScreenEvent.InitGuiEvent.Post event) {
        if (!AboutConfig.enabled || !(event.getGui() instanceof GuiMainMenu)) {
            return;
        }
        GuiMainMenu gui = (GuiMainMenu) event.getGui();

        int bx;
        int by;
        if (AboutConfig.absolute) {
            bx = AboutConfig.x;
            by = AboutConfig.y;
        } else {
            // 原版主菜单：「退出游戏」在 (width/2 + 2, j+84, 98x20)，语言按钮 20x20 在其左侧、间距 4
            // 默认镜像到右侧：width/2 + 2 + 98 + 4
            bx = gui.width / 2 + 104;
            by = gui.height / 4 + 48 + 84;
        }
        event.getButtonList().add(new GuiAboutButton(BUTTON_ID, bx, by));
    }

    @SubscribeEvent
    public static void onAction(GuiScreenEvent.ActionPerformedEvent.Post event) {
        if (event.getButton() instanceof GuiAboutButton) {
            event.getGui().mc.displayGuiScreen(new GuiAbout(event.getGui()));
        }
    }
}