package com.smushytaco.better_withered_mobs.mixins;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.WitherRoseBlock;
import net.minecraft.world.level.block.state.BlockState;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(WitherRoseBlock.class)
public abstract class WitherRoseBlockInject {
    @ModifyReturnValue(method = "mayPlaceOn", at = @At("RETURN"))
    private boolean hookCanPlantOnTop(boolean original, BlockState floor, BlockGetter world, BlockPos pos) { return original || floor.is(Blocks.WARPED_NYLIUM) || floor.is(Blocks.CRIMSON_NYLIUM); }
}