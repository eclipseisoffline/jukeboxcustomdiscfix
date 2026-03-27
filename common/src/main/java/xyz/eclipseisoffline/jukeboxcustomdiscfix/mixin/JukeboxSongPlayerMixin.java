package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import java.util.Optional;
import net.minecraft.core.BlockPos;
import net.minecraft.world.item.JukeboxSongPlayer;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JukeboxSongPlayer.class)
public abstract class JukeboxSongPlayerMixin {

    @Shadow
    @Final
    private BlockPos blockPos;

    @Inject(method = "stop", at = @At(value = "RETURN", ordinal = 0))
    public void sendStopRecordEvent(LevelAccessor levelAccessor, BlockState blockState, CallbackInfo callbackInfo) {
        levelAccessor.levelEvent(1011, this.blockPos, 0);
    }

    @Redirect(method = "stop", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/LevelAccessor;levelEvent(ILnet/minecraft/core/BlockPos;I)V"))
    public void onlySendStopRecordEventIfJukeboxEmpty(LevelAccessor levelAccessor, int type, BlockPos pos, int data) {
        Optional<JukeboxBlockEntity> jukeboxBlock = levelAccessor.getBlockEntity(pos, BlockEntityType.JUKEBOX);
        if (jukeboxBlock.isPresent() && !jukeboxBlock.orElseThrow().isEmpty()) {
            return;
        }
        levelAccessor.levelEvent(type, pos, data);
    }
}
