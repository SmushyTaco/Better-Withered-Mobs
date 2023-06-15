package com.smushytaco.better_withered_mobs.mixins;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(Item.class)
public abstract class WitherSkeletonSkullIsFireproof {
    @ModifyReturnValue(method = "isFireproof", at = @At("RETURN"))
    @SuppressWarnings({"unused", "RedundantCast"})
    private boolean hookIsFireproof(boolean original) { return original || ((Item) (Object) this) == Items.WITHER_SKELETON_SKULL; }
}