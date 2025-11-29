package com.smushytaco.better_withered_mobs.bone_meal
import net.minecraft.core.Direction
import net.minecraft.core.dispenser.BlockSource
import net.minecraft.core.dispenser.OptionalDispenseItemBehavior
import net.minecraft.world.item.ItemStack
import net.minecraft.world.level.block.Blocks
import net.minecraft.world.level.block.DispenserBlock
object WitheredBoneMealDispenserBehavior : OptionalDispenseItemBehavior() {
    override fun execute(pointer: BlockSource, itemStack: ItemStack): ItemStack {
        val direction: Direction = pointer.state.getValue(DispenserBlock.FACING)
        isSuccess = false
        if (pointer.level.getBlockState(pointer.pos.relative(direction).below()).block == Blocks.WARPED_NYLIUM && pointer.level.getBlockState(pointer.pos.relative(direction)).block == Blocks.AIR ||
            pointer.level.getBlockState(pointer.pos.relative(direction).below()).block == Blocks.CRIMSON_NYLIUM && pointer.level.getBlockState(pointer.pos.relative(direction)).block == Blocks.AIR
        ) {
            when(pointer.level.random.nextIntBetweenInclusive(0, 10)) {
                in 0..2 -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.WARPED_FUNGUS.defaultBlockState())
                in 3..4 -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.BROWN_MUSHROOM.defaultBlockState())
                in 5..7 -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.CRIMSON_FUNGUS.defaultBlockState())
                in 8..9 -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.RED_MUSHROOM.defaultBlockState())
                10 -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.WITHER_ROSE.defaultBlockState())
                else -> pointer.level.setBlockAndUpdate(pointer.pos.relative(direction), Blocks.WITHER_ROSE.defaultBlockState())
            }
            itemStack.shrink(1)
            isSuccess = true
        }
        return itemStack
    }
}