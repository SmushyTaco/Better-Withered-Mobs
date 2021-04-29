package com.smushytaco.better_withered_mobs.fireproof_items
import net.minecraft.block.Block
import net.minecraft.item.BlockItem
class FireproofBlockItem(block: Block, settings: Settings) : BlockItem(block, settings) {
    override fun isFireproof() = true
}