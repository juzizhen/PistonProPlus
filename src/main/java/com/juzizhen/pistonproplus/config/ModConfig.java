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

    public int maxPushLimit = 12;
    protected boolean enableMessages = true;
    protected boolean allowInfinitePush = false;

    public static void loadConfig() {
        if (CONFIG_FILE.exists()) {
            try (FileReader reader = new FileReader(CONFIG_FILE)) {
                INSTANCE = GSON.fromJson(reader, ModConfig.class);

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
            limit = 1;
        }
        INSTANCE.maxPushLimit = limit;
        saveConfig();
    }

    public static boolean isEnableMessages() {
        return INSTANCE.enableMessages;
    }

    public static void setEnableMessages(boolean enable) {
        INSTANCE.enableMessages = enable;
        saveConfig();
    }

    public static boolean isAllowInfinitePush() {
        return INSTANCE.allowInfinitePush;
    }

    public static void setAllowInfinitePush(boolean allow) {
        INSTANCE.allowInfinitePush = allow;
        saveConfig();
    }

    public static ModConfig getInstance() {
        return INSTANCE;
    }
}