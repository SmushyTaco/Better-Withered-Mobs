package com.smushytaco.better_withered_mobs.mixins;
import net.minecraft.item.Item;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(Item.class)
public interface FireproofAccessor {
    @Accessor
    void setFireproof(boolean fireproof);
}