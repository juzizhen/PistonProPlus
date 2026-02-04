package com.juzizhen.pistonproplus.util;

public class I18n {
    // 翻译键前缀
    public static final String MOD_ID = "pistonproplus";

    // 通用消息
    public static final String PREFIX = "message." + MOD_ID + ".prefix";
    public static final String SUCCESS_PREFIX = "message." + MOD_ID + ".success";
    public static final String ERROR_PREFIX = "message." + MOD_ID + ".error";
    public static final String INFO_PREFIX = "message." + MOD_ID + ".info";

    // 命令帮助
    public static final String COMMAND_HELP_TITLE = "command." + MOD_ID + ".help.title";
    public static final String COMMAND_HELP_LINE = "command." + MOD_ID + ".help.line";
    public static final String COMMAND_HELP_VERSION = "command." + MOD_ID + ".help.version";

    // 命令设置
    public static final String COMMAND_SET_SUCCESS = "command." + MOD_ID + ".set.success";
    public static final String COMMAND_SET_ENHANCED = "command." + MOD_ID + ".set.enhanced";
    public static final String COMMAND_SET_RESTRICTED = "command." + MOD_ID + ".set.restricted";
    public static final String COMMAND_SET_SAME_AS_VANILLA = "command." + MOD_ID + ".set.same_as_vanilla";

    // 命令获取
    public static final String COMMAND_GET_CURRENT = "command." + MOD_ID + ".get.current";
    public static final String COMMAND_GET_VANILLA = "command." + MOD_ID + ".get.vanilla";
    public static final String COMMAND_GET_INFINITE = "command." + MOD_ID + ".get.infinite";
    public static final String COMMAND_GET_CONFIG_VALUE = "command." + MOD_ID + ".get.config_value";

    // 命令无限模式
    public static final String COMMAND_INFINITE_ENABLED = "command." + MOD_ID + ".infinite.enabled";
    public static final String COMMAND_INFINITE_DISABLED = "command." + MOD_ID + ".infinite.disabled";

    // 命令重载
    public static final String COMMAND_RELOAD_SUCCESS = "command." + MOD_ID + ".reload.success";
    public static final String COMMAND_RELOAD_INFINITE_CHANGED = "command." + MOD_ID + ".reload.infinite_changed";
    public static final String COMMAND_RELOAD_LIMIT_CHANGED = "command." + MOD_ID + ".reload.limit_changed";
    public static final String COMMAND_RELOAD_LIMIT_UNCHANGED = "command." + MOD_ID + ".reload.limit_unchanged";

    // 杂项
    public static final String VANILLA_DEFAULT = "misc." + MOD_ID + ".vanilla_default";
    public static final String BLOCKS = "misc." + MOD_ID + ".blocks";
    public static final String INFINITY_SYMBOL = "misc." + MOD_ID + ".infinity_symbol";

    // 通用翻译（用于命令帮助）
    public static final String COMMANDS_HELP_DESCRIPTION = "commands.help.description";
    public static final String COMMANDS_SET_LIMIT_DESCRIPTION = "commands.set_limit.description";
    public static final String COMMANDS_GET_LIMIT_DESCRIPTION = "commands.get_limit.description";
    public static final String COMMANDS_SET_INFINITE_DESCRIPTION = "commands.set_infinite.description";
    public static final String COMMANDS_RELOAD_DESCRIPTION = "commands.reload.description";
    public static final String COMMANDS_REQUIRES_OP = "commands.requires_op";

    // 模式名称
    public static final String MODE_ENHANCED = "misc.enhanced_mode";
    public static final String MODE_RESTRICTED = "misc.restricted_mode";

    // 选项
    public static final String OPTIONS_ON = "options.on";
    public static final String OPTIONS_OFF = "options.off";

    // 功能特性（用于帮助文本）
    public static final String FEATURE_MODIFY_LIMIT = "feature.modify_limit";
    public static final String FEATURE_HOT_RELOAD = "feature.hot_reload";
    public static final String FEATURE_COMMANDS = "feature.commands";
    public static final String FEATURE_COLORED_MESSAGES = "feature.colored_messages";
}