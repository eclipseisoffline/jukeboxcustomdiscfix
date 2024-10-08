package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import java.util.Map;
import java.util.function.Supplier;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelEventHandler;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Holder;
import net.minecraft.core.RegistryAccess;
import net.minecraft.resources.ResourceKey;
import net.minecraft.util.profiling.ProfilerFiller;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.JukeboxBlock;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.level.storage.WritableLevelData;
import org.spongepowered.asm.mixin.Final;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(ClientLevel.class)
public abstract class ClientLevelMixin extends Level {

    @Shadow
    @Final
    private LevelEventHandler levelEventHandler;

    protected ClientLevelMixin(WritableLevelData writableLevelData, ResourceKey<Level> resourceKey, RegistryAccess registryAccess, Holder<DimensionType> holder, boolean bl, boolean bl2, long l, int i) {
        super(writableLevelData, resourceKey, registryAccess, holder, bl, bl2, l, i);
    }

    @Inject(method = "setServerVerifiedBlockState", at = @At("TAIL"))
    public void checkForJukeboxStateAndStopSong(BlockPos pos, BlockState state, int flags, CallbackInfo callbackInfo) {
        Map<BlockPos, SoundInstance> playingJukeboxSongs = ((LevelEventHandlerAccessor) levelEventHandler).getPlayingJukeboxSongs();
        if (playingJukeboxSongs.containsKey(pos)) {
            if (state.getBlock() != Blocks.JUKEBOX || !state.getValue(JukeboxBlock.HAS_RECORD)) {
                ((LevelEventHandlerAccessor) levelEventHandler).invokeStopJukeboxSongAndNotifyNearby(pos);
            }
        }
    }
}
