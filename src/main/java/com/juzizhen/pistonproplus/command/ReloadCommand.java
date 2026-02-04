package com.juzizhen.pistonproplus.command;

import com.juzizhen.pistonproplus.PistonProPlus;
import com.juzizhen.pistonproplus.config.ModConfig;
import com.juzizhen.pistonproplus.util.ChatFormat;
import com.juzizhen.pistonproplus.util.I18n;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;

public class ReloadCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("pistonproplus")
                .then(CommandManager.literal("reload")
                        .requires(source -> source.hasPermissionLevel(2))
                        .executes(ReloadCommand::executeReload))
        );
    }

    private static int executeReload(CommandContext<ServerCommandSource> context) {
        // 保存旧值用于比较
        int oldLimit = ModConfig.getMaxPushLimit();
        boolean oldInfinite = ModConfig.isAllowInfinitePush();
        boolean oldCommandBlock = ModConfig.isAllowPushCommandBlock();
        boolean oldAllBlocks = ModConfig.isAllowPushAllBlocks();

        // 重新加载配置
        ModConfig.reloadConfig();

        // 获取新值
        int newLimit = ModConfig.getMaxPushLimit();
        boolean newInfinite = ModConfig.isAllowInfinitePush();
        boolean newCommandBlock = ModConfig.isAllowPushCommandBlock();
        boolean newAllBlocks = ModConfig.isAllowPushAllBlocks();

        // 构建反馈消息
        StringBuilder message = new StringBuilder();
        message.append(Text.translatable(I18n.COMMAND_RELOAD_SUCCESS).getString()).append("\n");

        // 检查无限推动模式是否改变
        if (oldInfinite != newInfinite) {
            String oldStatus = oldInfinite ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();
            String newStatus = newInfinite ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();

            message.append(Text.translatable(I18n.COMMAND_RELOAD_INFINITE_CHANGED, oldStatus, newStatus).getString()).append("\n");
        }

        // 检查命令方块推动是否改变
        if (oldCommandBlock != newCommandBlock) {
            String oldStatus = oldCommandBlock ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();
            String newStatus = newCommandBlock ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();

            message.append("Command Block: ").append(oldStatus).append(" → ").append(newStatus).append("\n");
        }

        // 检查所有方块推动是否改变
        if (oldAllBlocks != newAllBlocks) {
            String oldStatus = oldAllBlocks ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();
            String newStatus = newAllBlocks ?
                    Text.translatable(I18n.OPTIONS_ON).getString() :
                    Text.translatable(I18n.OPTIONS_OFF).getString();

            message.append("All Blocks: ").append(oldStatus).append(" → ").append(newStatus).append("\n");
        }

        // 检查推动上限是否改变
        if (oldLimit != newLimit) {
            String oldDisplay = oldInfinite ?
                    "∞" : String.valueOf(oldLimit);
            String newDisplay = newInfinite ?
                    "∞" : String.valueOf(newLimit);

            message.append(Text.translatable(I18n.COMMAND_RELOAD_LIMIT_CHANGED, oldDisplay, newDisplay).getString());
        } else {
            String display = newInfinite ?
                    "∞" : String.valueOf(newLimit);

            message.append(Text.translatable(I18n.COMMAND_RELOAD_LIMIT_UNCHANGED, display).getString());
        }

        context.getSource().sendMessage(ChatFormat.createSuccessMessage(Text.literal(message.toString())));
        PistonProPlus.LOGGER.info("PistonProPlus config reloaded, new push limit: {}", newLimit);

        return Command.SINGLE_SUCCESS;
    }
}