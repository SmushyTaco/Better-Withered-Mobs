package com.smushytaco.better_withered_mobs.mixins;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(ItemStack.class)
public abstract class WitherSkeletonSkullIsFireproof {
    @Shadow
    public abstract Item getItem();
    @ModifyReturnValue(method = "canBeHurtBy", at = @At(value = "RETURN"))
    private boolean hookTakesDamageFrom(boolean original) { return getItem() != Items.WITHER_SKELETON_SKULL && original; }
}