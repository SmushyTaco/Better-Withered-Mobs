package com.smushytaco.better_withered_mobs.mixin_logic
import com.google.common.collect.ImmutableMap
import com.smushytaco.better_withered_mobs.event.SecondaryLootTableLoadingCallback
import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder
import net.minecraft.loot.LootManager
import net.minecraft.loot.LootTable
import net.minecraft.resource.ResourceManager
import net.minecraft.util.Identifier
object LootManagerInjectLogic {
    fun invokeEarlyLootTableLoadingCallback(tables: Map<Identifier, LootTable>, manager: ResourceManager, lootManager: LootManager): Map<Identifier, LootTable> {
        val newSuppliers = hashMapOf<Identifier, LootTable>()
        tables.forEach { (id, supplier) ->
            val builder = FabricLootTableBuilder.copyOf(supplier)
            SecondaryLootTableLoadingCallback.EVENT.invoker().onLootTableLoading(manager, lootManager, id, builder) {
                newSuppliers[id] = it
            }
            newSuppliers.computeIfAbsent(id) {
                builder.build()
            }
        }
        return ImmutableMap.copyOf(newSuppliers)
    }
}