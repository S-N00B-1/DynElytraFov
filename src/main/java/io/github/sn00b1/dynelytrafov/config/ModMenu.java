package io.github.sn00b1.dynelytrafov.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import io.github.sn00b1.dynelytrafov.DynElytraFovClient;
import com.terraformersmc.modmenu.api.ModMenuApi;
import me.shedaniel.clothconfig2.api.ConfigBuilder;
import me.shedaniel.clothconfig2.api.ConfigCategory;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.network.chat.TextComponent;
import net.minecraft.network.chat.TranslatableComponent;


@Environment(EnvType.CLIENT)
public class ModMenu implements ModMenuApi {

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        if (DynElytraFovClient.CONFIG != null) {
            return parentScreen -> {
                ConfigBuilder builder = ConfigBuilder.create()
                        .setParentScreen(parentScreen)
                        .setTitle(new TextComponent("Dynamic Elytra FOV"));
                ConfigCategory main = builder.getOrCreateCategory(new TextComponent("Dynamic Elytra FOV"));
                main.addEntry(builder.entryBuilder()
                        .startFloatField(new TranslatableComponent("dynelytrafov.maxfov"), DynElytraFovConfig.maxFov)
                        .setDefaultValue(2.0F)
                        .setMax(2.5F)
                        .setMin(1.0F)
                        .setSaveConsumer(newValue -> DynElytraFovConfig.maxFov = newValue)
                        .build());
                main.addEntry(builder.entryBuilder()
                        .startFloatField(new TranslatableComponent("dynelytrafov.strength"), DynElytraFovConfig.effectStrength)
                        .setDefaultValue(0.5F)
                        .setMax(1.0F)
                        .setMin(0.0F)
                        .setSaveConsumer(newValue -> DynElytraFovConfig.effectStrength = newValue)
                        .build());
                main.addEntry(builder.entryBuilder()
                        .startBooleanToggle(new TranslatableComponent("dynelytrafov.enable"), DynElytraFovConfig.modEnabled)
                        .setDefaultValue(true)
                        .setSaveConsumer(newValue -> DynElytraFovConfig.modEnabled = newValue)
                        .build());
                return builder.build();
            };
        } else return parent -> parent;
    }
}