package com.smushytaco.better_withered_mobs.bone_meal
import com.smushytaco.better_withered_mobs.fireproof_items.FireproofItem
import net.minecraft.block.Blocks
import net.minecraft.item.ItemUsageContext
import net.minecraft.util.ActionResult
import kotlin.random.Random
class WitheredBoneMeal(settings: Settings) : FireproofItem(settings) {
    override fun useOnBlock(itemUsageContext_1: ItemUsageContext): ActionResult {
        return if (itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos).block == Blocks.WARPED_NYLIUM && itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos.up()).block == Blocks.AIR ||
            itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos).block == Blocks.CRIMSON_NYLIUM && itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos.up()).block == Blocks.AIR
        ) {
            when(Random.nextInt(11)) {
                in 0..2 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.RED_MUSHROOM.defaultState)
                10 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
                else -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
            }
            itemUsageContext_1.stack.decrement(1)
            ActionResult.SUCCESS
        } else {
            ActionResult.FAIL
        }
    }
}