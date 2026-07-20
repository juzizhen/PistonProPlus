package com.juzizhen.pistonproplus.util;

import net.minecraft.util.math.BlockPos;

import java.util.HashSet;
import java.util.Set;

/**
 * Thread-local context that tracks which block positions are actively being
 * moved by a piston during the current push operation.
 * <p>
 * Used to suppress item drops from attached blocks (redstone dust, torches, etc.)
 * whose support is removed mid-push, preventing item duplication when
 * {@code allowPushAllBlocks} is enabled.
 */
public final class PistonMoveContext {

    private static final ThreadLocal<Set<BlockPos>> ACTIVE_POSITIONS = ThreadLocal.withInitial(HashSet::new);

    private PistonMoveContext() {}

    /** Begin a new piston move tracking phase. Call at the start of PistonBlock.move(). */
    public static void begin() {
        ACTIVE_POSITIONS.get().clear();
    }

    /** Register a block position that is part of the current piston move. */
    public static void add(BlockPos pos) {
        ACTIVE_POSITIONS.get().add(pos);
    }

    /** Check whether a position is currently part of an active piston move. */
    public static boolean isInMove(BlockPos pos) {
        Set<BlockPos> set = ACTIVE_POSITIONS.get();
        return set != null && set.contains(pos);
    }

    /** End the current piston move tracking phase and release resources. */
    public static void end() {
        ACTIVE_POSITIONS.get().clear();
    }
}