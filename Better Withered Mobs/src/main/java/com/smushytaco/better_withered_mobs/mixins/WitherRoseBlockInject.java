package com.smushytaco.better_withered_mobs.mixins;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.WitherRoseBlock;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(WitherRoseBlock.class)
public abstract class WitherRoseBlockInject {
    @ModifyReturnValue(method = "canPlantOnTop", at = @At("RETURN"))
    private boolean hookCanPlantOnTop(boolean original, BlockState floor, BlockView world, BlockPos pos) { return original || floor.isOf(Blocks.WARPED_NYLIUM) || floor.isOf(Blocks.CRIMSON_NYLIUM); }
}