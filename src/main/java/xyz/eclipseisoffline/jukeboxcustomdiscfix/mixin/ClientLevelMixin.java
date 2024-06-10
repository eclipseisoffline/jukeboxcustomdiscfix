package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import java.util.function.Supplier;
import net.minecraft.client.multiplayer.ClientLevel;
import net.minecraft.client.renderer.LevelRenderer;
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
    private LevelRenderer levelRenderer;

    protected ClientLevelMixin(WritableLevelData levelData, ResourceKey<Level> dimension, RegistryAccess registryAccess,
            Holder<DimensionType> dimensionTypeRegistration, Supplier<ProfilerFiller> profiler,
            boolean isClientSide, boolean isDebug, long biomeZoomSeed, int maxChainedNeighborUpdates) {
        super(levelData, dimension, registryAccess, dimensionTypeRegistration, profiler, isClientSide, isDebug, biomeZoomSeed, maxChainedNeighborUpdates);
    }

    @Inject(method = "setServerVerifiedBlockState", at = @At("TAIL"))
    public void checkForJukeboxStateAndStopSong(BlockPos pos, BlockState state, int flags, CallbackInfo callbackInfo) {
        if (state.getBlock() == Blocks.JUKEBOX
                && !state.getValue(JukeboxBlock.HAS_RECORD) && ((LevelRenderAccessor) levelRenderer).getPlayingJukeboxSongs().containsKey(pos)) {
            levelRenderer.stopJukeboxSongAndNotifyNearby(pos);
        }
    }
}
