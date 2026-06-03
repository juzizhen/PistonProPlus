package com.juzizhen.pistonproplus.mixin;

import com.juzizhen.pistonproplus.util.PistonNbtStorage;
import net.minecraft.block.BlockState;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.PistonBlockEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Direction;
import net.minecraft.world.World;
import org.jetbrains.annotations.UnknownNullability;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(PistonBlockEntity.class)
public abstract class PistonBlockEntityMixin extends BlockEntity {
    public PistonBlockEntityMixin(BlockEntityType<?> type, BlockPos pos, BlockState state) {
        super(type, pos, state);
    }

    @Inject(
            method = "tick",
            at = @At(
                    value = "INVOKE",
                    target = "Lnet/minecraft/world/World;setBlockState(Lnet/minecraft/util/math/BlockPos;Lnet/minecraft/block/BlockState;I)Z",
                    shift = At.Shift.AFTER
            )
    )
    private static void onTickSetBlockState(World world, BlockPos pos, BlockState state, PistonBlockEntity blockEntity, CallbackInfo ci) {
        if (world != null && !world.isClient && !blockEntity.isSource()) {
            pistonproplus$restoreNbt(world, pos, blockEntity);
        }
    }

    @Unique
    private static void pistonproplus$restoreNbt(World world, BlockPos finalPos, @UnknownNullability PistonBlockEntity pbe) {
        Direction moveDir = pbe.isExtending() ? pbe.getFacing() : pbe.getFacing().getOpposite();
        BlockPos originalPos = finalPos.offset(moveDir.getOpposite());

        NbtCompound nbtToRestore = PistonNbtStorage.ORIGINAL_POS_NBT_MAP.remove(originalPos);
        System.out.println("nbtToRestore" + nbtToRestore);
        if (nbtToRestore == null) {
            return;
        }

        BlockEntity targetBe = world.getBlockEntity(finalPos);
        if (targetBe != null && !(targetBe instanceof PistonBlockEntity)) {
            targetBe.read(nbtToRestore, world.getRegistryManager());
            targetBe.markDirty();

            BlockState currentState = world.getBlockState(finalPos);
            world.updateListeners(finalPos, currentState, currentState, 3);
        }
    }
}