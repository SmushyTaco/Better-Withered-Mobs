package com.smushytaco.better_withered_mobs.mixins;
import net.minecraft.item.Item;
import net.minecraft.item.Items;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;
@Mixin(Item.class)
@SuppressWarnings("ConstantConditions")
public abstract class WitherSkeletonSkullIsFireproof {
    @Inject(method = "isFireproof", at = @At("HEAD"), cancellable = true)
    private void hookIsFireproof(CallbackInfoReturnable<Boolean> cir) {
        Item item = (Item) (Object) this;
        if (item == Items.WITHER_SKELETON_SKULL) cir.setReturnValue(true);
    }
}