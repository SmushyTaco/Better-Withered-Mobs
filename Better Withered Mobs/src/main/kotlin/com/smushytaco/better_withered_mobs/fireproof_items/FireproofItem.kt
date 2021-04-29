package com.smushytaco.better_withered_mobs.fireproof_items
import net.minecraft.item.Item
open class FireproofItem(settings: Settings) : Item(settings) {
    override fun isFireproof() = true
}