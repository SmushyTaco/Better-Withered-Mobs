package com.smushytaco.better_withered_mobs
import com.smushytaco.better_withered_mobs.mixins.BrewingRecipeRegistryAccessor
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
object PotionOfDecay {
    private fun register(name: String, potion: Potion): Potion = Registry.register(Registries.POTION, name, potion)
    fun createPotionRecipes() {
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, POTION_OF_DECAY)
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(POTION_OF_DECAY, Items.REDSTONE, LONG_POTION_OF_DECAY)
        BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe(POTION_OF_DECAY, Items.GLOWSTONE_DUST, STRONG_POTION_OF_DECAY)
    }
    private val POTION_OF_DECAY = register("potion_of_decay", Potion(StatusEffectInstance(StatusEffects.WITHER, 3600)))
    private val LONG_POTION_OF_DECAY = register("long_potion_of_decay", Potion("potion_of_decay",
        StatusEffectInstance(StatusEffects.WITHER, 9600)))
    private val STRONG_POTION_OF_DECAY = register("strong_potion_of_decay", Potion("potion_of_decay",
        StatusEffectInstance(StatusEffects.WITHER, 1800, 1)))
}