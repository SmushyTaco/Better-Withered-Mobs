package com.smushytaco.better_withered_mobs.loot_table_modification
import com.smushytaco.better_withered_mobs.mixin.ItemEntryItemAccessor
import com.smushytaco.better_withered_mobs.mixin.LootPoolEntriesAccessor
import com.smushytaco.better_withered_mobs.mixin.LootTablePoolsAccessor
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.entry.LootPoolEntry
object LootTableModificationSyntacticSugar {
    val ItemEntry.item: Item
        get() = (this as ItemEntryItemAccessor).item
    val LootPool.entries: Array<LootPoolEntry>
        get() = (this as LootPoolEntriesAccessor).entries
    var LootTable.pools: Array<LootPool>
        get() = (this as LootTablePoolsAccessor).pools
        set(value) {
            (this as LootTablePoolsAccessor).pools = value
        }
}