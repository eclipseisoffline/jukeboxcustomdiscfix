package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import net.minecraft.core.BlockPos;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.JukeboxBlockEntity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Redirect;

@Mixin(JukeboxBlockEntity.class)
public abstract class JukeboxBlockEntityMixin {

    @Shadow
    public abstract ItemStack getTheItem();

    @Redirect(method = "stopPlaying", at = @At(value = "INVOKE", target = "Lnet/minecraft/world/level/Level;levelEvent(ILnet/minecraft/core/BlockPos;I)V"))
    public void cancelStopRecord(Level instance, int type, BlockPos blockPos, int data) {
        if (getTheItem().isEmpty()) {
            instance.levelEvent(type, blockPos, data);
        }
    }
}
