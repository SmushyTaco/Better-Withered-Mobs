package com.smushytaco.better_withered_mobs.mixins;
import com.google.common.collect.ImmutableList;
import net.minecraft.world.level.storage.loot.LootPool;
import net.minecraft.world.level.storage.loot.LootTable;
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