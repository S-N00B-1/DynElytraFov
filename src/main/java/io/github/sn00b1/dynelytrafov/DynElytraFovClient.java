package io.github.sn00b1.dynelytrafov;

import io.github.sn00b1.dynelytrafov.config.DynElytraFovConfig;
import me.shedaniel.autoconfig.AutoConfig;
import me.shedaniel.autoconfig.ConfigHolder;
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer;
import me.shedaniel.clothconfig2.api.ConfigEntryBuilder;
import me.shedaniel.clothconfig2.gui.entries.BooleanListEntry;
import me.shedaniel.clothconfig2.gui.entries.FloatListEntry;
import net.fabricmc.api.ClientModInitializer;
import net.minecraft.network.chat.TranslatableComponent;

public class DynElytraFovClient implements ClientModInitializer {
    public static final String MOD_ID = "dynelytrafov";

    public static ConfigHolder<DynElytraFovConfig> CONFIG = null;


    @Override
    public void onInitializeClient() {
        AutoConfig.register(DynElytraFovConfig.class, GsonConfigSerializer::new);
        CONFIG = AutoConfig.getConfigHolder(DynElytraFovConfig.class);
    }
}
