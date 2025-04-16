package io.github.sn00b1.dynelytrafov;

import dev.isxander.yacl3.api.ConfigCategory;
import dev.isxander.yacl3.api.Option;
import dev.isxander.yacl3.api.YetAnotherConfigLib;
import dev.isxander.yacl3.api.controller.FloatSliderControllerBuilder;
import dev.isxander.yacl3.api.controller.TickBoxControllerBuilder;
import io.github.sn00b1.dynelytrafov.config.DynElytraFovConfig;
import net.minecraft.network.chat.Component;
import net.neoforged.fml.common.Mod;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.fml.ModContainer;
import net.neoforged.fml.ModLoadingContext;
import net.neoforged.neoforge.client.gui.IConfigScreenFactory;

@Mod(DynElytraFovClient.MOD_ID)
public class DynElytraFovClient {
    public static final String MOD_ID = "dynelytrafov";
    Option<Boolean> EnableMod = Option.<Boolean>createBuilder()
            .name(Component.translatable("dynelytrafov.enable"))
            .binding(true, () -> DynElytraFovConfig.modEnabled, newVal -> DynElytraFovConfig.modEnabled = newVal)
            .controller(TickBoxControllerBuilder::create)
            .build();

    Option<Float> EffectStrength = Option.<Float>createBuilder()
            .name(Component.translatable("dynelytrafov.strength"))
            .binding(0.5F, () -> DynElytraFovConfig.effectStrength, newVal -> DynElytraFovConfig.effectStrength = newVal)
            .controller(opt -> FloatSliderControllerBuilder.create(opt).range(0.0f,1.0f).step(0.1F))
            .build();

    Option<Float> MaxFov = Option.<Float>createBuilder()
            .name(Component.translatable("dynelytrafov.maxfov"))
            .binding(2.0F, () -> DynElytraFovConfig.maxFov, newVal -> DynElytraFovConfig.maxFov = newVal)
            .controller(opt -> FloatSliderControllerBuilder.create(opt).range(1.0f,2.5f).step(0.1F))
            .build();

    public DynElytraFovClient(IEventBus modEventBus, ModContainer modContainer) {
        DynElytraFovConfig.HANDLER.load();

        ModLoadingContext.get().registerExtensionPoint(
                IConfigScreenFactory.class,
                () -> (client, parent) -> YetAnotherConfigLib.createBuilder()
                        .title(Component.literal("Dynamic Elytra FOV"))
                        .category(ConfigCategory.createBuilder()
                                .name(Component.literal("Dynamic Elytra FOV"))
                                .option(EnableMod).option(EffectStrength).option(MaxFov)
                                .build()).save(DynElytraFovConfig.HANDLER::save)
                        .build()
                        .generateScreen(parent)
        );
    }
}
