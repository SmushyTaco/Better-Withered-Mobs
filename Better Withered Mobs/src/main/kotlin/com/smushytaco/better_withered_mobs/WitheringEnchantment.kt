package com.smushytaco.better_withered_mobs
import net.minecraft.enchantment.Enchantment
import net.minecraft.enchantment.EnchantmentTarget
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects.WITHER
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.TridentItem
class WitheringEnchantment: Enchantment(Rarity.VERY_RARE, EnchantmentTarget.WEAPON, arrayOf(EquipmentSlot.MAINHAND)) {
    override fun getMinLevel() = 1
    override fun getMaxLevel() = 5
    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (target is LivingEntity) {
            target.addStatusEffect(StatusEffectInstance(WITHER, 60 * level, level - 1))
        }
        super.onTargetDamaged(user, target, level)
    }
    override fun isAcceptableItem(stack: ItemStack): Boolean {
        return if (stack.item is AxeItem && BetterWitheredMobs.config.witheringEnchantmentCanBeAnviledOntoAxes) {
            true
        } else if (stack.item is TridentItem && BetterWitheredMobs.config.witheringEnchantmentCanBeAnviledOntoTridents) {
            true
        }else {
            super.isAcceptableItem(stack)
        }
    }
}