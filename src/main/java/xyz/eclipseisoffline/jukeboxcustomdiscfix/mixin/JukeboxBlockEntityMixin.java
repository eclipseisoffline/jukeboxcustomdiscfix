package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(JukeboxBlockEntity.class)
public abstract class JukeboxBlockEntityMixin {

    @Unique
    private boolean manuallyStopped = false;

    @Redirect(method = "stopPlaying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"))
    public void cancelStopRecord(Level instance, int type, BlockPos blockPos, int data) {
        if (manuallyStopped) {
            instance.levelEvent(type, blockPos, data);
        }
    }

    @Inject(method = "startPlaying", at = @At("HEAD"))
    public void unsetManuallyStopped(CallbackInfo callbackInfo) {
        manuallyStopped = false;
    }

    @Inject(method = "splitTheItem", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/block/entity/JukeboxBlockEntity;stopPlaying()V"))
    public void setManuallyStopped(int amount, CallbackInfoReturnable<ItemStack> callbackInfoReturnable) {
        manuallyStopped = true;
    }
}
