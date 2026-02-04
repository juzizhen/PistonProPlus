package com.juzizhen.pistonproplus;

import com.juzizhen.pistonproplus.config.ModConfig;
import com.juzizhen.pistonproplus.command.PistonProPlusCommand;
import com.juzizhen.pistonproplus.command.ReloadCommand;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerLifecycleEvents;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class PistonProPlus implements ModInitializer {
	public static final String MOD_ID = "pistonproplus";
	public static final String MOD_NAME = "PistonProPlus";
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_NAME);

	@Override
	public void onInitialize() {
		LOGGER.info("✅ PistonProPlus Mod 初始化中...");

		// 加载配置
		ModConfig.loadConfig();

		// 注册服务器启动/停止事件
		ServerLifecycleEvents.SERVER_STARTING.register(server -> {
			LOGGER.info("✅ PistonProPlus Mod 已加载");
		});

		// 注册指令
		CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
			PistonProPlusCommand.register(dispatcher);
			ReloadCommand.register(dispatcher);
		});
	}
}
