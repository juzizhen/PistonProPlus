package com.juzizhen.pistonproplus.command;

import com.juzizhen.pistonproplus.config.ModConfig;
import com.juzizhen.pistonproplus.util.ChatFormat;
import com.juzizhen.pistonproplus.util.I18n;
import com.mojang.brigadier.Command;
import com.mojang.brigadier.CommandDispatcher;
import com.mojang.brigadier.arguments.BoolArgumentType;
import com.mojang.brigadier.arguments.IntegerArgumentType;
import com.mojang.brigadier.context.CommandContext;
import net.minecraft.server.command.CommandManager;
import net.minecraft.server.command.ServerCommandSource;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class PistonProPlusCommand {
    public static void register(CommandDispatcher<ServerCommandSource> dispatcher) {
        dispatcher.register(CommandManager.literal("pistonproplus")
                .then(CommandManager.literal("help")
                        .executes(PistonProPlusCommand::executeHelp))

                .then(CommandManager.literal("push")
                        .then(CommandManager.literal("set")
                                .requires(source -> source.hasPermissionLevel(4))
                                .then(CommandManager.argument("limit", IntegerArgumentType.integer(1, 4096))
                                        .executes(context -> executeSetLimit(
                                                context,
                                                IntegerArgumentType.getInteger(context, "limit")
                                        ))))

                        .then(CommandManager.literal("get")
                                .executes(PistonProPlusCommand::executeGetLimit))

                        .then(CommandManager.literal("infinite")
                                .requires(source -> source.hasPermissionLevel(4))
                                .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                        .executes(context -> executeSetInfinite(
                                                context,
                                                BoolArgumentType.getBool(context, "enabled")
                                        )))))

                // 新增的命令方块控制指令
                .then(CommandManager.literal("block")
                        .then(CommandManager.literal("commandblock")
                                .requires(source -> source.hasPermissionLevel(4))
                                .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                        .executes(context -> executeSetCommandBlock(
                                                context,
                                                BoolArgumentType.getBool(context, "enabled")
                                        ))))

                        // 新增的所有方块控制指令
                        .then(CommandManager.literal("all")
                                .requires(source -> source.hasPermissionLevel(4))
                                .then(CommandManager.argument("enabled", BoolArgumentType.bool())
                                        .executes(context -> executeSetAllBlocks(
                                                context,
                                                BoolArgumentType.getBool(context, "enabled")
                                        )))))
        );
    }

    private static int executeHelp(CommandContext<ServerCommandSource> context) {
        ServerCommandSource source = context.getSource();

        // 显示帮助标题
        source.sendMessage(ChatFormat.createSectionHeader(I18n.COMMAND_HELP_TITLE));

        // 显示帮助条目
        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus help").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_HELP_DESCRIPTION).formatted(Formatting.GRAY)
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus push set <1-4096>").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_REQUIRES_OP).formatted(Formatting.RED)
                        .append(Text.translatable(I18n.COMMANDS_SET_LIMIT_DESCRIPTION).formatted(Formatting.GRAY))
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus push get").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_GET_LIMIT_DESCRIPTION).formatted(Formatting.GRAY)
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus push infinite <true/false>").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_REQUIRES_OP).formatted(Formatting.RED)
                        .append(Text.translatable(I18n.COMMANDS_SET_INFINITE_DESCRIPTION).formatted(Formatting.GRAY))
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus block commandblock <true/false>").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_REQUIRES_OP).formatted(Formatting.RED)
                        .append(Text.translatable(I18n.COMMANDS_SET_COMMANDBLOCK_DESCRIPTION).formatted(Formatting.GRAY))
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus block all <true/false>").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_REQUIRES_OP).formatted(Formatting.RED)
                        .append(Text.translatable(I18n.COMMANDS_SET_ALLBLOCKS_DESCRIPTION).formatted(Formatting.GRAY))
        ));

        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_LINE,
                Text.literal("/pistonproplus reload").formatted(Formatting.YELLOW),
                Text.translatable(I18n.COMMANDS_RELOAD_DESCRIPTION).formatted(Formatting.GREEN)
        ));

        // 显示版本信息
        source.sendMessage(ChatFormat.createMessage(
                I18n.COMMAND_HELP_VERSION,
                Text.literal("1.1.0").formatted(Formatting.AQUA)
        ));

        // 显示功能特性
        source.sendMessage(ChatFormat.createInfoMessage(Text.translatable(I18n.FEATURE_MODIFY_LIMIT)));
        source.sendMessage(ChatFormat.createInfoMessage(Text.translatable(I18n.FEATURE_HOT_RELOAD)));
        source.sendMessage(ChatFormat.createInfoMessage(Text.translatable(I18n.FEATURE_COMMANDS)));
        source.sendMessage(ChatFormat.createInfoMessage(Text.translatable(I18n.FEATURE_COLORED_MESSAGES)));
        source.sendMessage(ChatFormat.createInfoMessage(Text.translatable(I18n.FEATURE_BLOCK_CONTROL)));

        return Command.SINGLE_SUCCESS;
    }

    private static int executeSetLimit(CommandContext<ServerCommandSource> context, int limit) {
        ModConfig.setMaxPushLimit(limit);

        if (limit == 12) {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(
                            I18n.COMMAND_SET_SAME_AS_VANILLA,
                            Text.literal(String.valueOf(limit)).formatted(Formatting.YELLOW),
                            Text.translatable(I18n.VANILLA_DEFAULT).formatted(Formatting.AQUA)
                    )
            );
        } else if (limit > 12) {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(
                            I18n.COMMAND_SET_ENHANCED,
                            Text.literal(String.valueOf(limit)).formatted(Formatting.YELLOW),
                            Text.translatable(I18n.MODE_ENHANCED).formatted(Formatting.GOLD)
                    )
            );
        } else {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(
                            I18n.COMMAND_SET_RESTRICTED,
                            Text.literal(String.valueOf(limit)).formatted(Formatting.YELLOW),
                            Text.translatable(I18n.MODE_RESTRICTED).formatted(Formatting.RED)
                    )
            );
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int executeGetLimit(CommandContext<ServerCommandSource> context) {
        int effectiveLimit = ModConfig.getMaxPushLimit();
        int configLimit = ModConfig.getInstance().maxPushLimit;
        boolean isInfinite = ModConfig.isAllowInfinitePush();

        if (isInfinite) {
            context.getSource().sendMessage(
                    ChatFormat.createInfoMessage(
                            I18n.COMMAND_GET_INFINITE,
                            Text.literal(String.valueOf(configLimit)).formatted(Formatting.GRAY)
                    )
            );
        } else if (effectiveLimit == 12) {
            context.getSource().sendMessage(
                    ChatFormat.createInfoMessage(
                            I18n.COMMAND_GET_VANILLA,
                            Text.literal(String.valueOf(effectiveLimit)).formatted(Formatting.YELLOW),
                            Text.translatable(I18n.VANILLA_DEFAULT).formatted(Formatting.AQUA)
                    )
            );
        } else {
            context.getSource().sendMessage(
                    ChatFormat.createInfoMessage(
                            I18n.COMMAND_GET_CURRENT,
                            Text.literal(String.valueOf(effectiveLimit)).formatted(Formatting.YELLOW),
                            Text.translatable(I18n.COMMAND_GET_CONFIG_VALUE,
                                    Text.literal(String.valueOf(configLimit)).formatted(Formatting.GRAY)
                            )
                    )
            );
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int executeSetInfinite(CommandContext<ServerCommandSource> context, boolean enabled) {
        ModConfig.setAllowInfinitePush(enabled);

        if (enabled) {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.COMMAND_INFINITE_ENABLED)
            );
        } else {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.COMMAND_INFINITE_DISABLED)
            );
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int executeSetCommandBlock(CommandContext<ServerCommandSource> context, boolean enabled) {
        ModConfig.setAllowPushCommandBlock(enabled);

        if (enabled) {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.COMMAND_BLOCK_ENABLED)
            );
            // 显示警告消息
            context.getSource().sendMessage(
                    ChatFormat.createInfoMessage(I18n.COMMAND_BLOCK_WARNING)
            );
        } else {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.COMMAND_BLOCK_DISABLED)
            );
        }

        return Command.SINGLE_SUCCESS;
    }

    private static int executeSetAllBlocks(CommandContext<ServerCommandSource> context, boolean enabled) {
        ModConfig.setAllowPushAllBlocks(enabled);

        if (enabled) {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.ALL_BLOCKS_ENABLED)
            );
            // 显示警告消息
            context.getSource().sendMessage(
                    ChatFormat.createInfoMessage(I18n.ALL_BLOCKS_WARNING)
            );
        } else {
            context.getSource().sendMessage(
                    ChatFormat.createSuccessMessage(I18n.ALL_BLOCKS_DISABLED)
            );
        }

        return Command.SINGLE_SUCCESS;
    }
}
