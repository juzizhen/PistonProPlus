package com.juzizhen.pistonproplus.mixin;

import com.juzizhen.pistonproplus.config.ModConfig;
import com.juzizhen.pistonproplus.util.PistonMoveContext;
import com.juzizhen.pistonproplus.util.PistonNbtStorage;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.piston.PistonHandler;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Clearable;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.List;

@Mixin(PistonBlock.class)
public class PistonBlockMixin {
    @Inject(
            method = "isMovable(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;ZLnet/minecraft/util/math/Direction;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void pistonproplus$onIsMovable(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonDir, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();

        if (block == Blocks.MOVING_PISTON) {
            cir.setReturnValue(false);
            return;
        }

        boolean isCommandBlock =
                block == Blocks.COMMAND_BLOCK ||
                        block == Blocks.CHAIN_COMMAND_BLOCK ||
                        block == Blocks.REPEATING_COMMAND_BLOCK;

        if (isCommandBlock) {
            if (ModConfig.isAllowPushCommandBlock()) {
                cir.setReturnValue(true);
            }
            return;
        }

        if (ModConfig.isAllowPushAllBlocks()) {
            cir.setReturnValue(true);
        }
    }

    @Inject(
            method = "move",
            at = @At(value = "INVOKE", target = "Lnet/minecraft/block/piston/PistonHandler;calculatePush()Z")
    )
    private void captureNBT(World world, BlockPos pos, Direction dir, boolean extending, CallbackInfoReturnable<Boolean> cir) {
        if (world.isClient) return;

        PistonMoveContext.begin();

        PistonHandler handler = new PistonHandler(world, pos, dir, extending);

        if (!handler.calculatePush()) {
            return;
        }

        List<BlockPos> movedBlocks = handler.getMovedBlocks();
        for (BlockPos srcPos : movedBlocks) {
            PistonMoveContext.add(srcPos.toImmutable());

            BlockEntity be = world.getBlockEntity(srcPos);
            if (be != null) {
                NbtCompound nbt = be.createNbtWithIdentifyingData();
                PistonNbtStorage.ORIGINAL_POS_NBT_MAP.put(srcPos, nbt);

                Clearable.clear(be);
                world.removeBlockEntity(srcPos);
            }
        }
    }

    @Inject(method = "move", at = @At("RETURN"))
    private void pistonproplus$cleanupMoveContext(World world, BlockPos pos, Direction dir, boolean extending, CallbackInfoReturnable<Boolean> cir) {
        if (world.isClient) return;
        PistonMoveContext.end();
    }
}