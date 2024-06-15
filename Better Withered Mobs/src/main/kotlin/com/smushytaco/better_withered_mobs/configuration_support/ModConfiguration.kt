package com.smushytaco.better_withered_mobs.configuration_support
import com.smushytaco.better_withered_mobs.BetterWitheredMobs
import me.shedaniel.autoconfig.ConfigData
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.cloth.clothconfig.shadowed.blue.endless.jankson.Comment
@Config(name = BetterWitheredMobs.MOD_ID)
class ModConfiguration: ConfigData {
    @Comment("Default value is yes. If set to yes withered mobs won't drop regular Bones and will only drop Withered Bones. If set to no they'll drop both. After modifying this value if you're currently in a world you'll need to relaunch the world for things to take effect.")
    val witheredMobsDontDropRegularBones = true
}