package com.smushytaco.better_withered_mobs.mixins;
import com.google.common.collect.ImmutableList;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Mutable;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(LootTable.Builder.class)
public interface LootTablePoolsAccessor {
    @Accessor
    ImmutableList.Builder<LootPool> getPools();
    @Accessor
    @Mutable
    void setPools(ImmutableList.Builder<LootPool> pools);
}