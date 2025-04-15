package io.github.sn00b1.dynelytrafov.config;

import io.github.sn00b1.dynelytrafov.DynElytraFovClient;
import me.shedaniel.autoconfig.ConfigData;
import me.shedaniel.autoconfig.annotation.Config;

@Config(name = DynElytraFovClient.MOD_ID)
public class DynElytraFovConfig implements ConfigData {
    public static boolean modEnabled = true;

    public static float effectStrength = 0.5F;

    public static float maxFov = 2.0F;

    public static boolean alternateLogic = false;

}
