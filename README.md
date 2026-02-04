# PistonProPlus

This is a Minecraft 1.20.1 Fabric Mod

<h3>Make Piston Great Again !</h3>

[![License: GPL](https://img.shields.io/badge/License-GPLv3-yellow.svg)](https://opensource.org/license/gpl-3-0)
[![Minecraft](https://img.shields.io/badge/Minecraft-1.20.1-green.svg)](https://www.minecraft.net/)
[![Mod Loader](https://img.shields.io/badge/Mod%20Loader-Fabric-blue.svg)](https://fabricmc.net/)
[![Java](https://img.shields.io/badge/Java-17%2B-orange.svg)](https://www.oracle.com/java/)

## ✨ Features

PistonProPlus is a powerful mod that enhances piston functionality in Minecraft, allowing you to break through vanilla limitations and customize piston behavior to your needs.

### 🔧 Core Features

1. **Customizable Push Limit**  
   - Break the vanilla 12-block limit (up to 1024 blocks!)
   - Set any value between 1 and 1024
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
/pistonproplus push set <1-1024>
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
⚠️ **Warning**: Pushed command blocks may lose NBT tags!

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
- **maxPushLimit**: Maximum blocks pistons can push (1-1024)
- **enableMessages**: Whether to show success/failure messages
- **allowInfinitePush**: Enable infinite push mode
- **allowPushCommandBlock**: Allow pistons to push command blocks
- **allowPushAllBlocks**: Allow pistons to push all blocks (except 36 and command blocks unless enabled separately)

## 🌍 Language Support

### Built-in Languages
- **English (en_us)** - Complete translation
- **Chinese (zh_cn)** - 完整中文翻译

### Adding New Languages
1. Create a new JSON file in `assets/pistonproplus/lang/`
2. Name it with the locale code (e.g., `fr_fr.json`, `es_es.json`)
3. Translate all keys from `en_us.json`
4. The mod will automatically detect and use the language based on client settings

## 📦 Installation

### For Players
1. Install [Fabric Loader](https://fabricmc.net/use/) for Minecraft 1.20.1
2. Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3. Download the latest PistonProPlus jar from [Releases](https://github.com/yourname/PistonProPlus/releases)
4. Place it in your `mods` folder
5. Launch Minecraft!

### For Server Admins
1. Install [Fabric Loader](https://fabricmc.net/use/) on your server
2. Install [Fabric API](https://www.curseforge.com/minecraft/mc-mods/fabric-api)
3. Place PistonProPlus jar in the server's `mods` folder
4. Configure permissions as needed:
   - Level 2: `/pistonproplus reload`
   - Level 4: All other configuration commands

## 🛠️ Building from Source

### Prerequisites
- Java 17 or higher
- Gradle

### Build Steps
```bash
# Clone the repository
git clone https://github.com/yourname/PistonProPlus.git
cd PistonProPlus

# Build the mod
./gradlew build

# The built jar will be in build/libs/
```

## 🚀 Use Cases

### Creative Building
- Move large structures easily
- Create complex piston machines without space constraints
- Experiment with command block contraptions

### Technical Minecraft
- Build more efficient flying machines
- Create advanced redstone contraptions
- Push previously immovable blocks for technical builds

### Adventure Maps
- Create custom mechanics with movable command blocks
- Design puzzles with pushable barriers
- Implement dynamic map elements

## ⚠️ Warnings & Limitations

### Important Notes
1. **MOVING_PISTON (block 36) can NEVER be pushed** - This is a vanilla safety mechanism
2. **Command blocks may lose NBT data when pushed** - Back up important command blocks
3. **Pushing all blocks may cause instability** - Use with caution in survival worlds
4. **Very high push limits may cause lag** - Test performance on your server

### Best Practices
1. Start with small limits and increase gradually
2. Test in creative mode before survival
3. Backup worlds before enabling experimental features
4. Monitor server performance with high push limits

## 🤝 Contributing

Contributions are welcome! Here's how you can help:

1. **Report Bugs** - Open an issue with detailed information
2. **Suggest Features** - Share your ideas for improvements
3. **Submit Translations** - Add support for more languages
4. **Code Contributions** - Fork and submit pull requests

## 📄 License

This project is licensed under the GPLv3 License - see the [LICENSE](LICENSE) file for details.

## 🙏 Credits

- **Mojang Studios** - For creating Minecraft
- **Fabric Team** - For the amazing modding framework
- **All Contributors** - For making this mod better

## 🔗 Links

- [GitHub Repository](https://github.com/juzizhen/PistonProPlus)
- [Curseforge](https://www.curseforge.com/minecraft/mc-mods/pistonproplus)

---

**Note**: This mod is not affiliated with or endorsed by Mojang Studios. Minecraft is a trademark of Mojang Studios.
