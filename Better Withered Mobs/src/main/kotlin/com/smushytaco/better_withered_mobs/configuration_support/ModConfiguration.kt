package com.smushytaco.better_withered_mobs.configuration_support
import com.smushytaco.better_withered_mobs.BetterWitheredMobs
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
@Config(name = BetterWitheredMobs.MOD_ID)
class ModConfiguration: ConfigData {
    @Comment("Default value is true. If set to true wither skeletons will spawn with stone swords with the Withering enchantment of a random level. If set to false they won't.")
    val witherSkeletonsSpawnWithWitheringEnchantedStoneSwords = true
}