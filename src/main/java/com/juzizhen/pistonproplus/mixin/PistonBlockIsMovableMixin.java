package com.juzizhen.pistonproplus.mixin;

import com.juzizhen.pistonproplus.config.ModConfig;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.PistonBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(PistonBlock.class)
public class PistonBlockIsMovableMixin {

    @Inject(
            method = "isMovable(Lnet/minecraft/block/BlockState;Lnet/minecraft/world/World;Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/util/math/Direction;ZLnet/minecraft/util/math/Direction;)Z",
            at = @At("HEAD"),
            cancellable = true
    )
    private static void pistonproplus$onIsMovable(BlockState state, World world, BlockPos pos, Direction direction, boolean canBreak, Direction pistonDir, CallbackInfoReturnable<Boolean> cir) {
        Block block = state.getBlock();

        // 记录当前检查的方块，用于调试
        String blockId = block.getTranslationKey();

        // 1. 检查是否是 36 号方块（MOVING_PISTON） - 永远不能推动
        if (block == Blocks.MOVING_PISTON) {
            cir.setReturnValue(false);
            return;
        }

        // 2. 检查是否是命令方块
        boolean isCommandBlock =
                block == Blocks.COMMAND_BLOCK ||
                        block == Blocks.CHAIN_COMMAND_BLOCK ||
                        block == Blocks.REPEATING_COMMAND_BLOCK;

        // 3. 检查是否是基岩
        boolean isBedrock = block == Blocks.BEDROCK;

        // 4. 检查是否是末地传送门框架
        boolean isEndPortalFrame = block == Blocks.END_PORTAL_FRAME;

        // 5. 检查是否是刷怪笼
        boolean isSpawner = block == Blocks.SPAWNER;

        // 6. 检查是否是屏障
        boolean isBarrier = block == Blocks.BARRIER;

        // 7. 检查是否是结构方块
        boolean isStructureBlock = block == Blocks.STRUCTURE_BLOCK;

        // 8. 检查是否是 Jigsaw 方块
        boolean isJigsawBlock = block == Blocks.JIGSAW;

        // 9. 检查是否是光源方块
        boolean isLightBlock = block == Blocks.LIGHT;

        // 10. 检查是否是重生锚
        boolean isRespawnAnchor = block == Blocks.RESPAWN_ANCHOR;

        // 11. 检查是否是强化深板岩
        boolean isReinforcedDeepslate = block == Blocks.REINFORCED_DEEPSLATE;

        // 如果是命令方块
        if (isCommandBlock) {
            if (ModConfig.isAllowPushCommandBlock()) {
                cir.setReturnValue(true);
                return;
            }
            // 如果命令方块未启用，则让原版逻辑决定
            return;
        }

        // 如果允许推动所有方块
        if (ModConfig.isAllowPushAllBlocks()) {
            // 除了 36 号方块，所有方块都可以推动
            cir.setReturnValue(true);
            return;
        }
    }
}
