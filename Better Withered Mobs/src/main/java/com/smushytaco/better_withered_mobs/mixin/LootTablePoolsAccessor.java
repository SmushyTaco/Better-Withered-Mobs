package com.smushytaco.better_withered_mobs.mixin;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(LootTable.class)
public interface LootTablePoolsAccessor {
    @Accessor
    LootPool[] getPools();
    @Accessor
    void setPools(LootPool[] pools);
}