package com.smushytaco.better_withered_mobs.event
import net.fabricmc.fabric.api.event.Event
import net.fabricmc.fabric.api.event.EventFactory
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder
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
        manager: LootManager,
        id: Identifier,
        supplier: FabricLootSupplierBuilder,
        setter: LootTableSetter
    )
    companion object {
        val EVENT: Event<SecondaryLootTableLoadingCallback> = EventFactory.createArrayBacked(SecondaryLootTableLoadingCallback::class.java)
        { listeners: Array<SecondaryLootTableLoadingCallback> ->
            SecondaryLootTableLoadingCallback { resourceManager: ResourceManager, manager: LootManager, id: Identifier, supplier: FabricLootSupplierBuilder, setter: LootTableSetter ->
                for (callback in listeners) {
                    callback.onLootTableLoading(resourceManager, manager, id, supplier, setter)
                }
            }
        }
    }
}