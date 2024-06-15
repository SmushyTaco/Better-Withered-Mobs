package com.smushytaco.better_withered_mobs
import net.minecraft.entity.effect.StatusEffectInstance
import net.minecraft.entity.effect.StatusEffects
import net.minecraft.item.Items
import net.minecraft.potion.Potion
import net.minecraft.potion.Potions
import net.minecraft.recipe.BrewingRecipeRegistry
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.registry.entry.RegistryEntry
import net.minecraft.util.Identifier
object PotionOfDecay {
    private fun register(name: String, potion: Potion): RegistryEntry<Potion> = Registry.registerReference(Registries.POTION, Identifier.of(BetterWitheredMobs.MOD_ID, name), potion)
    fun registerPotions() {
        POTION_OF_DECAY = register("potion_of_decay", Potion(StatusEffectInstance(StatusEffects.WITHER, 3600)))
        LONG_POTION_OF_DECAY = register("long_potion_of_decay", Potion("potion_of_decay", StatusEffectInstance(StatusEffects.WITHER, 9600)))
        STRONG_POTION_OF_DECAY = register("strong_potion_of_decay", Potion("potion_of_decay", StatusEffectInstance(StatusEffects.WITHER, 1800, 1)))

    }
    fun createPotionRecipes(builder: BrewingRecipeRegistry.Builder) {
        builder.registerPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, POTION_OF_DECAY)
        builder.registerPotionRecipe(POTION_OF_DECAY, Items.REDSTONE, LONG_POTION_OF_DECAY)
        builder.registerPotionRecipe(POTION_OF_DECAY, Items.GLOWSTONE_DUST, STRONG_POTION_OF_DECAY)
    }
    private lateinit var POTION_OF_DECAY: RegistryEntry<Potion>
    private lateinit var  LONG_POTION_OF_DECAY: RegistryEntry<Potion>
    private lateinit var  STRONG_POTION_OF_DECAY: RegistryEntry<Potion>
}