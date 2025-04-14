package io.github.sn00b1.dynelytrafov.client.mixin;

import io.github.sn00b1.dynelytrafov.client.config.DynElytraFovConfig;
import net.minecraft.client.Minecraft;
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

    @Shadow private float oldFov;

    @Shadow private float fov;

    @Shadow @Final Minecraft minecraft;

    @Inject(method = "tickFov", at= @At(value = "HEAD"), cancellable = true)
    private void getFOV(CallbackInfo ci) {
        if (DynElytraFovConfig.modEnabled) {
            float f = 1.0F;
            boolean fallFly = false;
            Entity var3 = this.minecraft.getCameraEntity();
            if (var3 instanceof AbstractClientPlayer player) {
                f = player.getFieldOfViewModifier();

                if (player.isFallFlying()) {
                    fallFly = true;
                    f = !DynElytraFovConfig.alternateLogic?
                            f+(float) player.getDeltaMovement().dot(player.getForward().normalize()) * DynElytraFovConfig.effectStrength
                            :
                            Mth.lerp(((float) player.getDeltaMovement().dot(player.getForward().normalize()) * DynElytraFovConfig.effectStrength * (1.0F/this.fov)),
                                    1.0F, DynElytraFovConfig.maxFov);
                }

            }

            this.oldFov = this.fov;
            this.fov += (f - this.fov) * 0.5F;
            if (this.fov > (fallFly ? DynElytraFovConfig.maxFov : 1.5F)) {
                this.fov = (fallFly ? DynElytraFovConfig.maxFov : 1.5F);
            }

            if (this.fov < (fallFly ? 1.0F : 0.1F)) {
                this.fov = (fallFly ? 1.0F : 0.1F);
            }
            ci.cancel();
        }
    }
}
