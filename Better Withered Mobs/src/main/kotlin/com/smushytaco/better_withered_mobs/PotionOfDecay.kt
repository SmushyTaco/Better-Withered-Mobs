package com.smushytaco.better_withered_mobs
import net.minecraft.core.Holder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.effect.MobEffectInstance
import net.minecraft.world.effect.MobEffects
import net.minecraft.world.item.Items
import net.minecraft.world.item.alchemy.Potion
import net.minecraft.world.item.alchemy.PotionBrewing
import net.minecraft.world.item.alchemy.Potions
object PotionOfDecay {
    private fun register(name: String, potion: Potion): Holder<Potion> = Registry.registerForHolder(BuiltInRegistries.POTION, ResourceLocation.fromNamespaceAndPath(BetterWitheredMobs.MOD_ID, name), potion)
    fun registerPotions() {
        POTION_OF_DECAY = register("potion_of_decay", Potion("potion_of_decay", MobEffectInstance(MobEffects.WITHER, 3600)))
        LONG_POTION_OF_DECAY = register("long_potion_of_decay", Potion("potion_of_decay", MobEffectInstance(MobEffects.WITHER, 9600)))
        STRONG_POTION_OF_DECAY = register("strong_potion_of_decay", Potion("potion_of_decay", MobEffectInstance(
            MobEffects.WITHER, 1800, 1)))

    }
    fun createPotionRecipes(builder: PotionBrewing.Builder) {
        builder.addMix(Potions.AWKWARD, Items.WITHER_ROSE, POTION_OF_DECAY)
        builder.addMix(POTION_OF_DECAY, Items.REDSTONE, LONG_POTION_OF_DECAY)
        builder.addMix(POTION_OF_DECAY, Items.GLOWSTONE_DUST, STRONG_POTION_OF_DECAY)
    }
    private lateinit var POTION_OF_DECAY: Holder<Potion>
    private lateinit var LONG_POTION_OF_DECAY: Holder<Potion>
    private lateinit var STRONG_POTION_OF_DECAY: Holder<Potion>
}