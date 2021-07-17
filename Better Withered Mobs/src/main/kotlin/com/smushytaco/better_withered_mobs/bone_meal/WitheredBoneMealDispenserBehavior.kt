package com.smushytaco.better_withered_mobs.bone_meal
import net.minecraft.block.Blocks
import net.minecraft.block.DispenserBlock
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.item.ItemStack
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Direction
import kotlin.random.Random
object WitheredBoneMealDispenserBehavior : FallibleItemDispenserBehavior() {
    override fun dispenseSilently(blockPointer_1: BlockPointer, itemStack_1: ItemStack): ItemStack {
        val direction: Direction = blockPointer_1.blockState.get(DispenserBlock.FACING)
        isSuccess = false
        if (blockPointer_1.world.getBlockState(blockPointer_1.pos.offset(direction).down()).block == Blocks.WARPED_NYLIUM && blockPointer_1.world.getBlockState(blockPointer_1.pos.offset(direction)).block == Blocks.AIR ||
            blockPointer_1.world.getBlockState(blockPointer_1.pos.offset(direction).down()).block == Blocks.CRIMSON_NYLIUM && blockPointer_1.world.getBlockState(blockPointer_1.pos.offset(direction)).block == Blocks.AIR
        ) {
            when(Random.nextInt(11)) {
                in 0..2 -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.RED_MUSHROOM.defaultState)
                10 -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.WITHER_ROSE.defaultState)
                else -> blockPointer_1.world.setBlockState(blockPointer_1.pos.offset(direction), Blocks.WITHER_ROSE.defaultState)
            }
            itemStack_1.decrement(1)
            isSuccess = true
        }
        return itemStack_1
    }
}