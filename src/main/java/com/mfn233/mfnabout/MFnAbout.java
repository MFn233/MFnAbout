package com.mfn233.mfnabout;

import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(modid = Tags.MOD_ID, name = Tags.MOD_NAME, version = Tags.VERSION,
        clientSideOnly = true, acceptedMinecraftVersions = "[1.12,1.13)")
public class MFnAbout {

    public static final Logger LOGGER = LogManager.getLogger(Tags.MOD_NAME);

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        AboutConfig.load(event.getSuggestedConfigurationFile());
        LOGGER.info("About button: enabled={}, position={}", AboutConfig.enabled,
                AboutConfig.absolute ? "absolute(" + AboutConfig.x + "," + AboutConfig.y + ")" : "mirrored-next-to-quit");
    }
}