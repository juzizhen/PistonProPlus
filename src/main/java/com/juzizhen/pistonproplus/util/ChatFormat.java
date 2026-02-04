package com.juzizhen.pistonproplus.util;

import net.minecraft.text.MutableText;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;

public class ChatFormat {
    public static MutableText createMessage(Text message) {
        return Text.translatable(I18n.PREFIX).append(" ").append(message);
    }

    public static MutableText createMessage(String translationKey, Object... args) {
        return Text.translatable(I18n.PREFIX).append(" ").append(Text.translatable(translationKey, args));
    }

    public static MutableText createSuccessMessage(Text message) {
        return Text.translatable(I18n.SUCCESS_PREFIX).append(" ").append(message);
    }

    public static MutableText createSuccessMessage(String translationKey, Object... args) {
        return Text.translatable(I18n.SUCCESS_PREFIX).append(" ").append(Text.translatable(translationKey, args));
    }

    public static MutableText createErrorMessage(Text message) {
        return Text.translatable(I18n.ERROR_PREFIX).append(" ").append(message);
    }

    public static MutableText createErrorMessage(String translationKey, Object... args) {
        return Text.translatable(I18n.ERROR_PREFIX).append(" ").append(Text.translatable(translationKey, args));
    }

    public static MutableText createInfoMessage(Text message) {
        return Text.translatable(I18n.INFO_PREFIX).append(" ").append(message);
    }

    public static MutableText createInfoMessage(String translationKey, Object... args) {
        return Text.translatable(I18n.INFO_PREFIX).append(" ").append(Text.translatable(translationKey, args));
    }

    public static MutableText createSectionHeader(Text title) {
        return Text.literal("\n").append(Text.literal("----------------").formatted(Formatting.STRIKETHROUGH))
                .append(" ").append(title).append(" ")
                .append(Text.literal("----------------").formatted(Formatting.STRIKETHROUGH))
                .append("\n");
    }

    public static MutableText createSectionHeader(String translationKey, Object... args) {
        return createSectionHeader(Text.translatable(translationKey, args));
    }
}