package com.smushytaco.better_withered_mobs.mixins;
import com.llamalad7.mixinextras.injector.wrapoperation.Operation;
import com.llamalad7.mixinextras.injector.wrapoperation.WrapOperation;
import net.minecraft.component.ComponentType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.Unit;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
@Mixin(ItemStack.class)
public abstract class WitherSkeletonSkullIsFireproof {
    @WrapOperation(method = "takesDamageFrom", at = @At(value = "INVOKE", target = "Lnet/minecraft/item/ItemStack;contains(Lnet/minecraft/component/ComponentType;)Z"))
    private boolean hookTakesDamageFrom(ItemStack instance, ComponentType<Unit> componentType, Operation<Boolean> original) {
        return instance.getItem() == Items.WITHER_SKELETON_SKULL || original.call(instance, componentType);
    }
}