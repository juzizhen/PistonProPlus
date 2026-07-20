# PistonProPlus

**Make Piston Great Again.**

[![License: GPLv3](https://img.shields.io/badge/License-GPLv3-yellow.svg)](https://opensource.org/license/gpl-3-0)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)](https://www.minecraft.net/)
[![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue.svg)](https://fabricmc.net/)

A server-side Fabric mod for Minecraft that enhances piston behavior with configurable push limits, advanced block pushing control, and NBT data preservation.

## Features

- **Configurable Push Limit** -- Break the vanilla 12-block restriction, set any limit from 1 to 4096
- **Infinite Push Mode** -- Set the limit to a negative value to enable virtually unlimited pushing
- **Type-Based Block Control** -- Individually toggle whether command blocks and all other blocks can be pushed
- **NBT Preservation** -- Block entity data (e.g. command block commands) is retained through piston pushes
- **Hot-Reload** -- Change configuration on the fly with `/pistonproplus reload`, no restart needed
- **Full i18n** -- English and Chinese built-in; easy to extend

## Commands

### Push Limit

| Command                                | Permission   | Description                                                   |
|----------------------------------------|--------------|---------------------------------------------------------------|
| `/pistonproplus pushlimit set <limit>` | OP (level 4) | Set piston push limit (1-4096). Use **-1** for infinite mode. |
| `/pistonproplus pushlimit get`         | None         | Display the current push limit and mode.                      |

### Type Limit

| Command                                              | Permission   | Description                                                                   |
|------------------------------------------------------|--------------|-------------------------------------------------------------------------------|
| `/pistonproplus typelimit commandblock [true/false]` | OP (level 4) | Toggle or query command block pushing.                                        |
| `/pistonproplus typelimit all [true/false]`          | OP (level 4) | Toggle or query all-blocks pushing (except MOVING_PISTON and command blocks). |

Omitting the boolean argument displays the current status (ON/OFF).

### Utility

| Command                 | Permission   | Description                         |
|-------------------------|--------------|-------------------------------------|
| `/pistonproplus help`   | None         | Show the help menu.                 |
| `/pistonproplus reload` | OP (level 2) | Hot-reload configuration from disk. |

## Configuration

Settings are stored in `config/pistonproplus.json` and can be edited directly or via commands.

```json
{
  "maxPushLimit": 12,
  "allowInfinitePush": false,
  "allowPushCommandBlock": false,
  "allowPushAllBlocks": false
}
```

| Key                     | Type    | Default | Description                                                    |
|-------------------------|---------|---------|----------------------------------------------------------------|
| `maxPushLimit`          | int     | 12      | Maximum blocks a piston can push (1-4096)                      |
| `allowInfinitePush`     | boolean | false   | When true, push limit becomes 4096                             |
| `allowPushCommandBlock` | boolean | false   | Allow pistons to push command blocks (NBT preserved)           |
| `allowPushAllBlocks`    | boolean | false   | Allow pistons to push nearly all blocks (except MOVING_PISTON) |

## Warnings

- **MOVING_PISTON (block 36) can never be pushed.** This is a hard safety constraint.
- **Command blocks may lose NBT data** in edge cases; the mod preserves NBT through capture/restore, but complex setups should be tested.
- **Pushing all blocks may cause unexpected behavior.** Attached blocks (redstone dust, torches, etc.) on slime blocks can behave unexpectedly during large pushes.
- **Very high push limits may cause server lag.** Test incrementally before deploying on production servers.

## License

This project is licensed under the [GNU General Public License v3.0](LICENSE).

## Links

- [GitHub](https://github.com/juzizhen/PistonProPlus)
- [CurseForge](https://www.curseforge.com/minecraft/mc-mods/pistonproplus)
- [Modrinth](https://modrinth.com/mod/pistonproplus)

---

*This mod is not affiliated with or endorsed by Mojang Studios. Minecraft is a trademark of Mojang Studios.*