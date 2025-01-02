package io.github.ultimateboomer.resolutioncontrol.mixin;

import io.github.ultimateboomer.resolutioncontrol.ResolutionControlMod;
import net.minecraft.client.gl.Framebuffer;
import net.minecraft.client.render.WorldRenderer;
import net.minecraft.client.render.DefaultFramebufferSet;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import net.minecraft.client.util.Handle;

@Mixin(WorldRenderer.class)
public class WorldRendererMixin {
    @Shadow
    private DefaultFramebufferSet framebufferSet;

    @Inject(at = @At("RETURN"), method = "loadEntityOutlinePostProcessor")
    private void onLoadEntityOutlineShader(CallbackInfo ci) {
        Handle<Framebuffer> entityOutlineHandle = framebufferSet.get(DefaultFramebufferSet.ENTITY_OUTLINE);
        if (entityOutlineHandle != null && entityOutlineHandle.get() != null) {
            ResolutionControlMod.getInstance().resizeMinecraftFramebuffers();
        }
    }

    @Inject(at = @At("RETURN"), method = "onResized")
    private void onOnResized(CallbackInfo ci) {
        Handle<Framebuffer> entityOutlineHandle = framebufferSet.get(DefaultFramebufferSet.ENTITY_OUTLINE);
        if (entityOutlineHandle != null && entityOutlineHandle.get() != null) {
            ResolutionControlMod.getInstance().resizeMinecraftFramebuffers();
        }
    }
}
