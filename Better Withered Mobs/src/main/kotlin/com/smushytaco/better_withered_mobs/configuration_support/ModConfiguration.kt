package com.smushytaco.better_withered_mobs.configuration_support
import com.smushytaco.better_withered_mobs.BetterWitheredMobs
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.annotation.ConfigEntry.Gui.RequiresRestart
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
@Config(name = BetterWitheredMobs.MOD_ID)
class ModConfiguration: ConfigData {
    @Comment("Default value is yes. If set to yes wither skeletons will spawn with stone swords with the Withering enchantment of a random level. If set to no they won't.")
    val witherSkeletonsSpawnWithWitheringEnchantedStoneSwords = true
    @Comment("Default value is yes. If set to yes withered mobs won't drop regular Bones and will only drop Withered Bones. If set to no they'll drop both. After modifying this value if you're currently in a world you'll need to relaunch the world for things to take effect.")
    val witheredMobsDontDropRegularBones = true
    @Comment("Default value is yes. If set to yes you'll be able to anvil the withering enchantment onto axes. If set to no you won't be able to.")
    val witheringEnchantmentCanBeAnviledOntoAxes = true
    @Comment("Default value is yes. If set to yes you'll be able to anvil the withering enchantment onto tridents. If set to no you won't be able to.")
    val witheringEnchantmentCanBeAnviledOntoTridents = true
    @RequiresRestart
    @Comment("Default value is 5. This will determine the maximum level for the withering enchantment. If set to 0 or below the maximum level will be 1. Restart required for changes to take place.")
    val maximumLevelForWitheringEnchantment = 5
    @Comment("Default value is 60. This multiplier will determine how many ticks the withering enchantment will last for. This value will be multiplied with the level to determine the length of the withering effect given.")
    val tickMultiplierForWitheringEnchantment = 60
}