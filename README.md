# Jukebox Custom Disc Fix

This mod fixes bug [MC-260346](https://bugs.mojang.com/browse/MC-260346), which causes custom music
discs, when played in a jukebox, to be cut off when the vanilla disc would normally end.

The fix is implemented by only telling the client to stop playing a disc sound when the disc item is removed 
from the jukebox (either by a hopper or by a player clicking on the jukebox), whereas in vanilla
Minecraft the client is told to stop playing a disc sound as soon as the vanilla disc would end.

This mod does not have to be installed client side.

Feel free to report any bugs, or suggest new features, at the issue tracker.

## License

This mod is licensed under GNU GPLv3.

## Usage

Mod builds can be found [here]()https://github.com/eclipseisoffline/jukeboxcustomdiscfix/packages/2082474.

This mod is currently available for Minecraft 1.20.4 (Fabric modloader 0.15.7 or higher).
The Fabric API is not required. This mod is not required on clients.
