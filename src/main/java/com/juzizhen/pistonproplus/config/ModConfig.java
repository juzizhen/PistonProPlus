package com.juzizhen.pistonproplus.config;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.juzizhen.pistonproplus.PistonProPlus;
import net.fabricmc.loader.api.FabricLoader;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class ModConfig {
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    private static final File CONFIG_FILE = new File(
            FabricLoader.getInstance().getConfigDir().toFile(),
            "pistonproplus.json"
    );

    private static ModConfig INSTANCE;

    // 配置项 - 默认值为原版推动上限12
    public int maxPushLimit = 12;
    private boolean allowInfinitePush = false;

    // 新添加的配置项
    private boolean allowPushCommandBlock = false;    // 是否允许推动命令方块
    private boolean allowPushAllBlocks = false;       // 是否允许推动所有方块（除36号方块和命令方块外）

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);

                // 向后兼容性检查
                if (INSTANCE.maxPushLimit < 1) {
                    INSTANCE.maxPushLimit = 12;
                    saveConfig();
                }
            } catch (IOException e) {
                PistonProPlus.LOGGER.error("❌ 无法加载配置文件", e);
                INSTANCE = new ModConfig();
            }
        } else {
            INSTANCE = new ModConfig();
            saveConfig();
        }
    }

    public static void saveConfig() {
        try (FileWriter writer = new FileWriter(CONFIG_FILE)) {
            GSON.toJson(INSTANCE, writer);
        } catch (IOException e) {
            PistonProPlus.LOGGER.error("❌ 无法保存配置文件", e);
        }
    }

    public static void reloadConfig() {
        loadConfig();
    }

    public static int getMaxPushLimit() {
        if (INSTANCE.allowInfinitePush) {
            return 4096;
        }
        return INSTANCE.maxPushLimit;
    }

    public static void setMaxPushLimit(int limit) {
        if (limit <= 0) {
            limit = 12;
        }
        INSTANCE.maxPushLimit = limit;
        saveConfig();
    }

    public static boolean isAllowInfinitePush() {
        return INSTANCE.allowInfinitePush;
    }

    public static void setAllowInfinitePush(boolean allow) {
        INSTANCE.allowInfinitePush = allow;
        saveConfig();
    }

    // 新增的getter和setter方法
    public static boolean isAllowPushCommandBlock() {
        return INSTANCE.allowPushCommandBlock;
    }

    public static void setAllowPushCommandBlock(boolean allow) {
        INSTANCE.allowPushCommandBlock = allow;
        saveConfig();
    }

    public static boolean isAllowPushAllBlocks() {
        return INSTANCE.allowPushAllBlocks;
    }

    public static void setAllowPushAllBlocks(boolean allow) {
        INSTANCE.allowPushAllBlocks = allow;
        saveConfig();
    }

    public static ModConfig getInstance() {
        return INSTANCE;
    }
}