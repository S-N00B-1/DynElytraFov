package io.github.sn00b1.dynelytrafov.mixin;

import io.github.sn00b1.dynelytrafov.config.DynElytraFovConfig;
import net.minecraft.client.Minecraft;
import net.minecraft.client.Options;
import net.minecraft.client.player.AbstractClientPlayer;
import net.minecraft.client.renderer.GameRenderer;
import net.minecraft.util.Mth;
import net.minecraft.world.entity.Entity;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {

    @Shadow private float oldFovModifier;

    @Shadow private float fovModifier;

    @Shadow @Final private Minecraft minecraft;

    @Inject(method = "tickFov", at= @At(value = "HEAD"), cancellable = true)
    private void getFOV(CallbackInfo ci) {
        if (DynElytraFovConfig.modEnabled) {
            float f = 1.0F;
            boolean fallFly = false;
            Entity var3 = this.minecraft.getCameraEntity();
            if (var3 instanceof AbstractClientPlayer player) {
                Options options = this.minecraft.options;
                boolean bl = options.getCameraType().isFirstPerson();
                float s = options.fovEffectScale().get().floatValue();
                f = player.getFieldOfViewModifier(bl, s);

                if (player.isFallFlying()) {
                    fallFly = true;
                    f = !DynElytraFovConfig.alternateLogic?
                            f+(float) player.getDeltaMovement().dot(player.getForward().normalize()) * DynElytraFovConfig.effectStrength
                            :
                            Mth.lerp(((float) player.getDeltaMovement().dot(player.getForward().normalize()) * DynElytraFovConfig.effectStrength * (1.0F/this.fovModifier)),
                                    1.0F, DynElytraFovConfig.maxFov);
                }

            }

            this.oldFovModifier = this.fovModifier;
            this.fovModifier += (f - this.fovModifier) * 0.5F;

            this.fovModifier = Mth.clamp(this.fovModifier, (fallFly ? 1.0F : 0.1F), (fallFly ? DynElytraFovConfig.maxFov : 1.5F));
            ci.cancel();
        }
    }
}
