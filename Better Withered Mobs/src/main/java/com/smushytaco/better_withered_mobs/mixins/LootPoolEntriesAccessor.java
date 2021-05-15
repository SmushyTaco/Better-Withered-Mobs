package com.smushytaco.better_withered_mobs.mixins;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.entry.LootPoolEntry;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.gen.Accessor;
@Mixin(LootPool.class)
public interface LootPoolEntriesAccessor {
    @Accessor
    LootPoolEntry[] getEntries();
}