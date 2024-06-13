package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.Clearable;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.ticks.ContainerSingleItem;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(JukeboxBlockEntity.class)
public abstract class JukeboxBlockEntityMixin extends BlockEntity implements Clearable, ContainerSingleItem {

    public JukeboxBlockEntityMixin(BlockEntityType<?> type,
            BlockPos pos, BlockState blockState) {
        super(type, pos, blockState);
    }

    @Inject(method = "stopPlaying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"), cancellable = true)
    public void cancelStopRecordWhenNotEmpty(CallbackInfo callbackInfo) {
        if (!getTheItem().isEmpty()) {
            setChanged();
            callbackInfo.cancel();
        }
    }
}
