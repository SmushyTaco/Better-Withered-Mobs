package com.smushytaco.better_withered_mobs
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.potion.Potion
import net.minecraft.util.registry.Registry
object PotionOfDecay {
    private fun register(name: String, potion: Potion): Potion = Registry.register(Registry.POTION, name, potion)
    val POTION_OF_DECAY = register("potion_of_decay", Potion(StatusEffectInstance(StatusEffects.WITHER, 3600)))
    val LONG_POTION_OF_DECAY = register("long_potion_of_decay", Potion("potion_of_decay",
        StatusEffectInstance(StatusEffects.WITHER, 9600)))
    val STRONG_POTION_OF_DECAY = register("strong_potion_of_decay", Potion("potion_of_decay",
        StatusEffectInstance(StatusEffects.WITHER, 1800, 1)))
}