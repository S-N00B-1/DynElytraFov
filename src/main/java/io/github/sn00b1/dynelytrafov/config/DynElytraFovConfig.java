package io.github.sn00b1.dynelytrafov.config;

import dev.isxander.yacl3.config.v2.api.ConfigClassHandler;
import dev.isxander.yacl3.config.v2.api.SerialEntry;
import dev.isxander.yacl3.config.v2.api.serializer.GsonConfigSerializerBuilder;
import io.github.sn00b1.dynelytrafov.DynElytraFovClient;
import net.minecraft.resources.ResourceLocation;
import net.neoforged.fml.loading.FMLPaths;

public class DynElytraFovConfig {
    private static Object GsonBuilder;
    public static ConfigClassHandler<DynElytraFovConfig> HANDLER = ConfigClassHandler.createBuilder(DynElytraFovConfig.class)
            .id(ResourceLocation.fromNamespaceAndPath(DynElytraFovClient.MOD_ID, "dynelytrafovconfig"))
                    .serializer(config -> GsonConfigSerializerBuilder.create(config)
                            .setPath(FMLPaths.CONFIGDIR.get().resolve("dynelytrafovconfig.json5"))
                            .setJson5(true)
                            .build())
                    .build();

    @SerialEntry
    public static boolean modEnabled = true;

    @SerialEntry
    public static float effectStrength = 0.5F;

    @SerialEntry
    public static float maxFov = 2.0F;

    @SerialEntry
    public static boolean alternateLogic = false;


}
