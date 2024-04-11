# Jukebox Custom Disc Fix

This mod fixes bug [MC-260346](https://bugs.mojang.com/browse/MC-260346), which causes custom music
discs, when played in a jukebox, to be cut off when the vanilla disc would normally end.

The fix is implemented server-side by only telling the client to stop playing a disc sound when the disc item is removed 
from the jukebox (either by a hopper or by a player clicking on the jukebox), whereas in vanilla
Minecraft the client is told to stop playing a disc sound as soon as the vanilla disc would end.

This mod does not have to be installed client side when playing multiplayer.

Feel free to report any bugs, at the issue tracker.

## License

This mod is licensed under GNU GPLv3.

## Usage

Mod builds can be found [here](https://github.com/eclipseisoffline/jukeboxcustomdiscfix/packages/2082474)
and on [Modrinth](https://modrinth.com/mod/jukebox-custom-disc-fix).

This mod is currently available for Minecraft 1.20.5, Minecraft 1.20.4 and Minecraft 1.20.1 (Fabric modloader).
The Fabric API is not required. This mod is not required on clients when playing multiplayer.
