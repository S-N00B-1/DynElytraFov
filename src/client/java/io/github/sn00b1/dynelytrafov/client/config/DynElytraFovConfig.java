package io.github.sn00b1.dynelytrafov.client.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import io.github.sn00b1.dynelytrafov.client.DynElytraFovClient;
import net.fabricmc.loader.api.FabricLoader;
import net.minecraft.resources.ResourceLocation;

public class DynElytraFovConfig {
    private static Object GsonBuilder;
    public static ConfigClassHandler<DynElytraFovConfig> HANDLER = ConfigClassHandler.createBuilder(DynElytraFovConfig.class)
            .id(ResourceLocation.fromNamespaceAndPath(DynElytraFovClient.MOD_ID, "dynelytrafovconfig"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FabricLoader.getInstance().getConfigDir().resolve("dynelytrafovconfig.json5"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    public static boolean modEnabled = true;

    @SerialEntry
    public static float effectStrength = 1.0F;

    @SerialEntry
    public static float maxFov = 1.5F;


}
