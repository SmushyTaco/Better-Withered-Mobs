package com.smushytaco.better_withered_mobs.fireproof_items
import com.smushytaco.better_withered_mobs.mixin.FireproofAccessor
import net.minecraft.item.Item
object FireproofSyntacticSugar {
    var Item.fireproof
        get() = isFireproof
        set(value) = (this as FireproofAccessor).setFireproof(value)
}