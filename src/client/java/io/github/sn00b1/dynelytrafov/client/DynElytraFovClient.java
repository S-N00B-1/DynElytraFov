package io.github.sn00b1.dynelytrafov.client;

import io.github.sn00b1.dynelytrafov.client.config.DynElytraFovConfig;
import net.fabricmc.api.ClientModInitializer;

public class DynElytraFovClient implements ClientModInitializer {
    public static final String MOD_ID = "dynelytrafov";

    @Override
    public void onInitializeClient() {
        DynElytraFovConfig.HANDLER.load();
    }
}
