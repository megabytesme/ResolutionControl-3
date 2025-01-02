package io.github.ultimateboomer.resolutioncontrol.mixin;

import io.github.ultimateboomer.resolutioncontrol.ResolutionControlMod;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.WorldRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    public Framebuffer entityOutlineFramebuffer;

    @Inject(at = @At("RETURN"), method = "loadEntityOutlinePostProcessor")
    private void onLoadEntityOutlineShader(CallbackInfo ci) {
        ResolutionControlMod.getInstance().resizeMinecraftFramebuffers();
    }

    @Inject(at = @At("RETURN"), method = "onResized")
    private void onOnResized(CallbackInfo ci) {
        if (entityOutlineFramebuffer == null)
            return;
        ResolutionControlMod.getInstance().resizeMinecraftFramebuffers();
    }
}
