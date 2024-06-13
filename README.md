# Jukebox Custom Disc Fix

![Modrinth Version](https://img.shields.io/modrinth/v/NtPNF0D7?logo=modrinth&color=008800)
![Modrinth Game Versions](https://img.shields.io/modrinth/game-versions/NtPNF0D7?logo=modrinth&color=008800)
![Modrinth Downloads](https://img.shields.io/modrinth/dt/NtPNF0D7?logo=modrinth&color=008800)

## Minecraft 1.21 notice

The bug report for the bug this mod fixes has been marked as resolved as of Minecraft 1.21.

This mod will continue to be updated and has a 1.21 version, however as of Minecraft 1.21 it is
strongly recommended to use the vanilla Minecraft way of adding custom discs, which is through datapacks.
Information about how to do so can be found [here](https://minecraft.wiki/w/Jukebox_song_definition).

If you still wish to listen to custom discs through just the use of a resource pack though, you will need this mod.
As of 1.21 this mod now fixes the bug client-side as well, meaning you can join every server with a custom disc resource pack, without the disc being cut off.

## README

This mod fixes bug [MC-260346](https://bugs.mojang.com/browse/MC-260346), which causes custom music
discs, when played in a jukebox, to be cut off when the vanilla disc would normally end.

The fix is implemented server-side by only telling the client to stop playing a disc sound when the disc item is removed 
from the jukebox (either by a hopper or by a player clicking on the jukebox), whereas in vanilla
Minecraft the client is told to stop playing a disc sound as soon as the vanilla disc would end.

As of Minecraft 1.21 (mod version 0.1.4-1.21), this mod has a client-side fix as well. This is implemented
by ignoring all packets the server sends to the client that tell the client to stop playing a song,
and instead have the client only stop playing a song when the `has_record` block state for the jukebox
playing the song has been set to `false`.

When installed on Fabric servers, this mod does not have to be installed on clients connecting.

Feel free to report any bugs, at the issue tracker.

## License

This mod is licensed under GNU GPLv3.

## Usage

Mod builds can be found [here](https://github.com/eclipseisoffline/jukeboxcustomdiscfix/packages/2082474)
and on [Modrinth](https://modrinth.com/mod/jukebox-custom-disc-fix).

This mod is currently available for Minecraft 1.21, 1.20.5+6, 1.20.4 and 1.20.1 (Fabric modloader).
The Fabric API is not required. This mod is not required on clients when playing multiplayer.
