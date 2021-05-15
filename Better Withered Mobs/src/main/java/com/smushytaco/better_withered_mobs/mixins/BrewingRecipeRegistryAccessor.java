package com.smushytaco.better_withered_mobs.mixins;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Invoker;
import net.minecraft.item.Item;
import net.minecraft.potion.Potion;
import net.minecraft.recipe.BrewingRecipeRegistry;
@Mixin(BrewingRecipeRegistry.class)
public interface BrewingRecipeRegistryAccessor {
    @Invoker
    @SuppressWarnings("unused")
    static void invokeRegisterPotionRecipe(Potion in, Item ingredient, Potion result) {}
}