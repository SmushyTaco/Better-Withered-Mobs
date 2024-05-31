package com.smushytaco.better_withered_mobs
import net.minecraft.enchantment.Enchantment
import net.minecraft.entity.Entity
import net.minecraft.entity.EquipmentSlot
import net.minecraft.entity.LivingEntity
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects.WITHER
import net.minecraft.item.AxeItem
import net.minecraft.item.ItemStack
import net.minecraft.item.TridentItem
import net.minecraft.registry.tag.ItemTags
object WitheringEnchantment: Enchantment(properties(ItemTags.WEAPON_ENCHANTABLE, ItemTags.SWORD_ENCHANTABLE, 1, BetterWitheredMobs.config.maximumLevelForWitheringEnchantment.coerceAtLeast(1), leveledCost(1, 11), leveledCost(21, 11), 1, EquipmentSlot.MAINHAND)) {
    override fun onTargetDamaged(user: LivingEntity, target: Entity, level: Int) {
        if (target is LivingEntity) target.addStatusEffect(StatusEffectInstance(WITHER, (BetterWitheredMobs.config.tickMultiplierForWitheringEnchantment.coerceAtLeast(1) * level).coerceAtLeast(1), level - 1))
        super.onTargetDamaged(user, target, level)
    }
    override fun isAcceptableItem(stack: ItemStack): Boolean = (stack.item is AxeItem && BetterWitheredMobs.config.witheringEnchantmentCanBeAnviledOntoAxes) || (stack.item is TridentItem && BetterWitheredMobs.config.witheringEnchantmentCanBeAnviledOntoTridents) || super.isAcceptableItem(stack)
}