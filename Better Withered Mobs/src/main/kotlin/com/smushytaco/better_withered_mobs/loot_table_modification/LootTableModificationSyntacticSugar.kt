package com.smushytaco.better_withered_mobs.loot_table_modification
import com.smushytaco.better_withered_mobs.mixins.ItemEntryItemAccessor
import com.smushytaco.better_withered_mobs.mixins.LootTablePoolsAccessor
import net.minecraft.item.Item
import net.minecraft.loot.LootPool
import net.minecraft.loot.LootTable
import net.minecraft.loot.entry.ItemEntry
object LootTableModificationSyntacticSugar {
    val ItemEntry.item: Item
        get() = (this as ItemEntryItemAccessor).item
    var LootTable.tablePools: Array<LootPool>
        get() = (this as LootTablePoolsAccessor).pools
        set(value) {
            (this as LootTablePoolsAccessor).pools = value
        }
}