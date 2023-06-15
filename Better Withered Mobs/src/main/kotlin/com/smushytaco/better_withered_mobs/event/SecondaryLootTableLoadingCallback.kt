package com.smushytaco.better_withered_mobs.event
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.fabricmc.fabric.api.loot.v2.LootTableSource
import net.minecraft.loot.LootManager
import net.minecraft.loot.LootTable
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
fun interface SecondaryLootTableLoadingCallback {
    fun interface LootTableSetter {
        fun set(supplier: LootTable)
    }
    fun onLootTableLoading(
        resourceManager: ResourceManager,
        lootManager: LootManager,
        id: Identifier,
        tableBuilder: LootTable.Builder,
        setter: LootTableSetter,
        source: LootTableSource
    )
    companion object {
        val EVENT: Event<SecondaryLootTableLoadingCallback> = EventFactory.createArrayBacked(SecondaryLootTableLoadingCallback::class.java)
        { listeners: Array<SecondaryLootTableLoadingCallback> ->
            SecondaryLootTableLoadingCallback { resourceManager: ResourceManager, lootManager: LootManager, id: Identifier, tableBuilder: LootTable.Builder, setter: LootTableSetter, source: LootTableSource ->
                for (callback in listeners) {
                    callback.onLootTableLoading(resourceManager, lootManager, id, tableBuilder, setter, source)
                }
            }
        }
    }
}