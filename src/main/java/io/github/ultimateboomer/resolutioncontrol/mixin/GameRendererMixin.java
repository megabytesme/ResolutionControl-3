package io.github.ultimateboomer.resolutioncontrol.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.render.GameRenderer;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import io.github.ultimateboomer.resolutioncontrol.ResolutionControlMod;

@Mixin(GameRenderer.class)
public abstract class GameRendererMixin {
	@Inject(at = @At("HEAD"), method = "renderWorld")
	private void onRenderWorldBegin(CallbackInfo callbackInfo) {
		if (MinecraftClient.getInstance().world != null) {
			ResolutionControlMod.getInstance().setShouldScale(true);
		}
	}

	@Inject(at = @At("RETURN"), method = "renderWorld")
	private void onRenderWorldEnd(CallbackInfo callbackInfo) {
		ResolutionControlMod.getInstance().setShouldScale(false);
	}
}
