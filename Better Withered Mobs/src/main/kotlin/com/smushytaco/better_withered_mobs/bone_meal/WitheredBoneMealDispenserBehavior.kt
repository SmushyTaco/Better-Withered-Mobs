package com.smushytaco.better_withered_mobs.bone_meal
import net.minecraft.block.Blocks
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Direction
object WitheredBoneMealDispenserBehavior : FallibleItemDispenserBehavior() {
    override fun dispenseSilently(pointer: BlockPointer, itemStack: ItemStack): ItemStack {
        val direction: Direction = pointer.state[DispenserBlock.FACING]
        isSuccess = false
        if (pointer.world.getBlockState(pointer.pos.offset(direction).down()).block == Blocks.WARPED_NYLIUM && pointer.world.getBlockState(pointer.pos.offset(direction)).block == Blocks.AIR ||
            pointer.world.getBlockState(pointer.pos.offset(direction).down()).block == Blocks.CRIMSON_NYLIUM && pointer.world.getBlockState(pointer.pos.offset(direction)).block == Blocks.AIR
        ) {
            when(pointer.world.random.nextBetween(0, 10)) {
                in 0..2 -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.RED_MUSHROOM.defaultState)
                10 -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.WITHER_ROSE.defaultState)
                else -> pointer.world.setBlockState(pointer.pos.offset(direction), Blocks.WITHER_ROSE.defaultState)
            }
            itemStack.decrement(1)
            isSuccess = true
        }
        return itemStack
    }
}