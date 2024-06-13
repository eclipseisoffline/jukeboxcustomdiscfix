package xyz.eclipseisoffline.jukeboxcustomdiscfix.mixin;

import java.util.Map;
import net.minecraft.client.renderer.LevelRenderer;
import net.minecraft.client.resources.sounds.SoundInstance;
import net.minecraft.core.BlockPos;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;

@Mixin(LevelRenderer.class)
public interface LevelRenderAccessor {

    @Accessor
    Map<BlockPos, SoundInstance> getPlayingJukeboxSongs();
}
