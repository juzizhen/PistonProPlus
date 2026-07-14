package com.juzizhen.pistonproplus.mixin;

import com.juzizhen.pistonproplus.util.PistonMoveContext;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.Entity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraft.world.WorldAccess;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * Intercepts {@link Block#dropStacks} to suppress item drops for blocks that
 * are currently being moved by a piston.
 * <p>
 * When {@code allowPushAllBlocks} is enabled, attached blocks (redstone dust,
 * torches, tripwires, etc.) are forced into the piston's move list. If their
 * support block moves first, they break and would normally drop items — but the
 * piston still places them at the destination, causing duplication. This mixin
 * cancels the drop for any block whose position is tracked by
 * {@link PistonMoveContext}.
 */
@Mixin(Block.class)
public abstract class BlockMixin {

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;)V",
            at = @At("HEAD"), cancellable = true)
    private static void pistonproplus$cancelDrop3(BlockState state, World world, BlockPos pos, CallbackInfo ci) {
        if (PistonMoveContext.isInMove(pos)) {
            ci.cancel();
        }
    }

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/WorldAccess;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;)V",
            at = @At("HEAD"), cancellable = true)
    private static void pistonproplus$cancelDrop4(BlockState state, WorldAccess world, BlockPos pos, BlockEntity blockEntity, CallbackInfo ci) {
        if (PistonMoveContext.isInMove(pos)) {
            ci.cancel();
        }
    }

    @Inject(method = "dropStacks(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/entity/BlockEntity;Lnet/minecraft/entity/Entity;Lnet/minecraft/item/ItemStack;)V",
            at = @At("HEAD"), cancellable = true)
    private static void pistonproplus$cancelDrop6(BlockState state, World world, BlockPos pos, BlockEntity blockEntity, Entity entity, ItemStack tool, CallbackInfo ci) {
        if (PistonMoveContext.isInMove(pos)) {
            ci.cancel();
        }
    }
}
