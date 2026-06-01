package com.juzizhen.pistonproplus.util;

import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import java.util.HashMap;
import java.util.Map;

public class PistonNbtStorage {
    public static final Map<BlockPos, NbtCompound> ORIGINAL_POS_NBT_MAP = new HashMap<>();
}