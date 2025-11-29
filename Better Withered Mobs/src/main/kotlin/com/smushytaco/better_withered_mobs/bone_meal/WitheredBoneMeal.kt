package com.smushytaco.better_withered_mobs.bone_meal
import net.minecraft.world.InteractionResult
import net.minecraft.world.item.Item
import net.minecraft.world.item.context.UseOnContext
import net.minecraft.world.level.block.Blocks
class WitheredBoneMeal(settings: Properties) : Item(settings) {
    override fun useOn(context: UseOnContext): InteractionResult {
        return if (context.level.getBlockState(context.clickedPos).block == Blocks.WARPED_NYLIUM && context.level.getBlockState(context.clickedPos.above()).block == Blocks.AIR ||
            context.level.getBlockState(context.clickedPos).block == Blocks.CRIMSON_NYLIUM && context.level.getBlockState(context.clickedPos.above()).block == Blocks.AIR
        ) {
            when(context.level.random.nextIntBetweenInclusive(0, 10)) {
                in 0..2 -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.WARPED_FUNGUS.defaultBlockState())
                in 3..4 -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.BROWN_MUSHROOM.defaultBlockState())
                in 5..7 -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.CRIMSON_FUNGUS.defaultBlockState())
                in 8..9 -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.RED_MUSHROOM.defaultBlockState())
                10 -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.WITHER_ROSE.defaultBlockState())
                else -> context.level.setBlockAndUpdate(context.clickedPos.above(), Blocks.WITHER_ROSE.defaultBlockState())
            }
            context.itemInHand.shrink(1)
            InteractionResult.SUCCESS
        } else {
            InteractionResult.FAIL
        }
    }
}