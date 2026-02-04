package com.juzizhen.pistonproplus.mixin;

import org.spongepowered.asm.mixin.Mixin;

import com.juzizhen.pistonproplus.config.ModConfig;
import net.minecraft.block.piston.PistonHandler;
import org.spongepowered.asm.mixin.injection.Constant;
import org.spongepowered.asm.mixin.injection.ModifyConstant;

@Mixin(PistonHandler.class)
public class PistonBlockMixin {
	@ModifyConstant(
			method = {"tryMove", "calculatePush"},
			constant = @Constant(intValue = 12),
			require = 1
	)
	private int modifyMaxPushBlocks(int original) {
		return ModConfig.getMaxPushLimit();
	}
}