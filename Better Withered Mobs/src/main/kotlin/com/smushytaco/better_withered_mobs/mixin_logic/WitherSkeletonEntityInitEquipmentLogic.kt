package com.smushytaco.better_withered_mobs.mixin_logic
import com.smushytaco.better_withered_mobs.BetterWitheredMobs
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
object WitherSkeletonEntityInitEquipmentLogic {
    fun stoneSwordWithWitheringEnchantment(): ItemStack {
        val witheringEnchantment = BetterWitheredMobs.WITHERING_ENCHANTMENT
        val stoneSword = ItemStack(Items.STONE_SWORD)
        val enchantLevel = (witheringEnchantment.minLevel .. witheringEnchantment.maxLevel).random()
        stoneSword.addEnchantment(witheringEnchantment, enchantLevel)
        return stoneSword
    }
}