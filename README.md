# PistonProPlus

This is a Minecraft Fabric Mod

<h3>Make Piston Great Again !</h3>

[![License: GPL](https://img.shields.io/badge/License-GPLv3-yellow.svg)](https://opensource.org/license/gpl-3-0)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)](https://www.minecraft.net/)
[![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue.svg)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

## ✨ Features

PistonProPlus is a powerful mod that enhances piston functionality in Minecraft, allowing you to break through vanilla limitations and customize piston behavior to your needs.

### 🔧 Core Features

1. **Customizable Push Limit**  
   - Break the vanilla 12-block limit (up to 4096 blocks!)
   - Set any value between 1 and 4096
   - Default is 12 (same as vanilla)

2. **Advanced Block Pushing Control**  
   - **Command Blocks**: Enable/disable pushing command blocks
   - **All Blocks**: Push almost any block (except 36 - MOVING_PISTON)
   - Safety warnings for potentially destructive operations

3. **Infinite Push Mode**  
   - Enable virtually unlimited pushing capacity
   - Perfect for creative builds and large-scale projects

4. **Hot-Reload Configuration**  
   - Change settings on-the-fly with `/pistonproplus reload`
   - No server restart required

5. **Complete Command System**  
   - Intuitive commands with permission levels
   - Colored, formatted messages for better UX
   - Comprehensive help system

6. **Full Internationalization (i18n)**  
   - English (en_us) and Chinese (zh_cn) built-in
   - Easy to add more languages
   - Automatic language detection

7. **JSON Configuration**  
   - Persistent settings stored in `config/pistonproplus.json`
   - Easy manual editing if preferred

## 📋 Commands

### Help Command
```
/pistonproplus help
```
Displays the complete help menu with all available commands.

### Push Limit Commands
```
/pistonproplus push set <1-4096>
```
Set the maximum number of blocks pistons can push.  
**Requires OP permission (level 4)**

```
/pistonproplus push get
```
Get the current piston push limit.  
**No permission required**

```
/pistonproplus push infinite <true/false>
```
Enable/disable infinite push mode.  
**Requires OP permission (level 4)**

### Block Control Commands
```
/pistonproplus block commandblock <true/false>
```
Enable/disable command block pushing.  
**Requires OP permission (level 4)**  
Pushing the command block will retain its NBT value.

```
/pistonproplus block all <true/false>
```
Enable/disable pushing of all blocks (except block 36 - MOVING_PISTON and command blocks unless separately enabled).  
**Requires OP permission (level 4)**  
⚠️ **Warning**: Pushing all blocks may cause unexpected behavior!

### Configuration Command
```
/pistonproplus reload
```
Hot-reload the configuration from file.  
**Requires OP permission (level 2)**

## ⚙️ Configuration

### Configuration File
Located at: `config/pistonproplus.json`

### Default Configuration
```json
{
  "maxPushLimit": 12,
  "enableMessages": true,
  "allowInfinitePush": false,
  "allowPushCommandBlock": false,
  "allowPushAllBlocks": false
}
```

### Configuration Options
- **maxPushLimit**: Maximum blocks pistons can push (1-4096)
- **enableMessages**: Whether to show success/failure messages
- **allowInfinitePush**: Enable infinite push mode
- **allowPushCommandBlock**: Allow pistons to push command blocks
- **allowPushAllBlocks**: Allow pistons to push all blocks (except 36 and command blocks unless enabled separately)

## ⚠️ Warnings & Limitations

### Important Notes
1. **MOVING_PISTON (block 36) can NEVER be pushed** - This is a vanilla safety mechanism
2. **Pushing all blocks may cause instability** - Use with caution in survival worlds
3. **Very high push limits may cause lag** - Test performance on your server

### Best Practices
1. Start with small limits and increase gradually
2. Test in creative mode before survival
3. Backup worlds before enabling experimental features
4. Monitor server performance with high push limits

## 📄 License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

## 🔗 Links

- [GitHub Repository](https://github.com/juzizhen/PistonProPlus)
- [Curseforge](https://www.curseforge.com/minecraft/mc-mods/pistonproplus)
- [Modrinth](https://modrinth.com/mod/pistonproplus)

---

**Note**: This mod is not affiliated with or endorsed by Mojang Studios. Minecraft is a trademark of Mojang Studios.