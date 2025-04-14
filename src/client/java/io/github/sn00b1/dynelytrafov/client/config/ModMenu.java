package io.github.sn00b1.dynelytrafov.client.config;

import com.terraformersmc.modmenu.api.ConfigScreenFactory;
import com.terraformersmc.modmenu.api.ModMenuApi;
import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import net.minecraft.network.chat.Component;

public class ModMenu implements ModMenuApi {
   Option<Boolean> EnableMod = Option.<Boolean>createBuilder()
           .name(Component.translatable("dynelytrafov.enable"))
           .binding(true, () -> DynElytraFovConfig.modEnabled, newVal -> DynElytraFovConfig.modEnabled = newVal)
           .controller(TickBoxControllerBuilder::create)
           .build();

    Option<Float> EffectStrength = Option.<Float>createBuilder()
            .name(Component.translatable("dynelytrafov.strength"))
            .binding(1.0F, () -> DynElytraFovConfig.effectStrength, newVal -> DynElytraFovConfig.effectStrength = newVal)
            .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0.0f,1.0f).step(0.1F))
            .build();

    Option<Float> MaxFov = Option.<Float>createBuilder()
            .name(Component.translatable("dynelytrafov.maxfov"))
            .binding(1.5F, () -> DynElytraFovConfig.maxFov, newVal -> DynElytraFovConfig.maxFov = newVal)
            .controller(opt -> FloatSliderControllerBuilder.create(opt).range(1.0f,2.5f).step(0.1F))
            .build();

    @Override
    public ConfigScreenFactory<?> getModConfigScreenFactory() {
        return parentScreen -> YetAnotherConfigLib.createBuilder()
                .title(Component.literal("Dynamic Elytra FOV"))
                .category(ConfigCategory.createBuilder()
                        .name(Component.literal("Dynamic Elytra FOV"))
                        .option(EnableMod).option(EffectStrength).option(MaxFov)
                        .build()).save(DynElytraFovConfig.HANDLER::save)
                .build()
                .generateScreen(parentScreen);
    }
}