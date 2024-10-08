package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(LevelEventHandler.class)
public abstract class LevelEventHandlerMixin {

    @Redirect(method = "levelEvent", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/LevelEventHandler;stopJukeboxSongAndNotifyNearby(Lnet/minecraft/core/BlockPos;)V"))
    public void cancelStopJukeboxSong(LevelEventHandler instance, BlockPos blockPos) {}
}
