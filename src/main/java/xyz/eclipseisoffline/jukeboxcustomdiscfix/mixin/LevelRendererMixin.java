package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.core.BlockPos;
import net.minecraft.server.packs.resources.ResourceManagerReloadListener;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelRenderer.class)
public abstract class LevelRendererMixin implements ResourceManagerReloadListener, AutoCloseable {

    @Redirect(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelRenderer;stopJukeboxSongAndNotifyNearby(Lnet/minecraft/core/BlockPos;)V"))
    public void cancelStopJukeboxSong(LevelRenderer instance, BlockPos blockPos) {}
}
