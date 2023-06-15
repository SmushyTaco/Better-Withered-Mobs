package com.smushytaco.better_withered_mobs.mixins;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WitherRoseBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(WitherRoseBlock.class)
public abstract class WitherRoseBlockInject {
    @Inject(method = "canPlantOnTop", at = @At("HEAD"), cancellable = true)
    private void hookCanPlantOnTop(BlockState floor, BlockView world, BlockPos pos, CallbackInfoReturnable<Boolean> cir) {
        if (floor.isOf(Blocks.WARPED_NYLIUM) || floor.isOf(Blocks.CRIMSON_NYLIUM)) cir.setReturnValue(true);
    }
}