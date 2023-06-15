package com.smushytaco.better_withered_mobs.bone_meal
import net.minecraft.block.Blocks
import net.minecraft.item.Item
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
class WitheredBoneMeal(settings: Settings) : Item(settings) {
    override fun useOnBlock(context: ItemUsageContext): ActionResult {
        return if (context.world.getBlockState(context.blockPos).block == Blocks.WARPED_NYLIUM && context.world.getBlockState(context.blockPos.up()).block == Blocks.AIR ||
            context.world.getBlockState(context.blockPos).block == Blocks.CRIMSON_NYLIUM && context.world.getBlockState(context.blockPos.up()).block == Blocks.AIR
        ) {
            when(context.world.random.nextBetween(0, 10)) {
                in 0..2 -> context.world.setBlockState(context.blockPos.up(), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> context.world.setBlockState(context.blockPos.up(), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> context.world.setBlockState(context.blockPos.up(), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> context.world.setBlockState(context.blockPos.up(), Blocks.RED_MUSHROOM.defaultState)
                10 -> context.world.setBlockState(context.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
                else -> context.world.setBlockState(context.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
            }
            context.stack.decrement(1)
            ActionResult.SUCCESS
        } else {
            ActionResult.FAIL
        }
    }
}