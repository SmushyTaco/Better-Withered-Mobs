package com.smushytaco.better_withered_mobs
import io.wispforest.owo.config.annotation.Config
import io.wispforest.owo.config.annotation.Modmenu
import io.wispforest.owo.config.annotation.RestartRequired
@Modmenu(modId = BetterWitheredMobs.MOD_ID)
@Config(name = BetterWitheredMobs.MOD_ID, wrapperName = "ModConfig")
@Suppress("UNUSED")
class ModConfiguration {
    @JvmField
    @RestartRequired
    var witheredMobsDontDropRegularBones = true
}