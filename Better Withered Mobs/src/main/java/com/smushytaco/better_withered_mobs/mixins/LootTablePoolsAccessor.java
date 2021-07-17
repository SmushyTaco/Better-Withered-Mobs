package com.smushytaco.better_withered_mobs.mixins;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(LootTable.class)
public interface LootTablePoolsAccessor {
    @Accessor
    LootPool[] getPools();
    @Accessor
    @Mutable
    void setPools(LootPool[] pools);
}