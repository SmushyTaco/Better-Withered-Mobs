package com.smushytaco.better_withered_mobs.mixins;
import com.google.common.collect.ImmutableMap;
import com.llamalad7.mixinextras.injector.ModifyReturnValue;
import com.smushytaco.better_withered_mobs.event.SecondaryLootTableLoadingCallback;
import net.fabricmc.fabric.api.loot.v2.FabricLootTableBuilder;
import net.fabricmc.fabric.impl.loot.LootUtil;
import net.minecraft.loot.LootDataKey;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.resource.ResourceManager;
import net.minecraft.resource.ResourceReloader;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
@Mixin(value = LootManager.class, priority = 999)
public abstract class LootManagerInject {
    @Shadow
    private Map<LootDataKey<?>, ?> keyToValue;
    @ModifyReturnValue(method = "reload", at = @At("RETURN"))
    @SuppressWarnings("unused")
    private CompletableFuture<Void> hookReload(CompletableFuture<Void> original, ResourceReloader.Synchronizer synchronizer, ResourceManager manager, Profiler prepareProfiler, Profiler applyProfiler, Executor prepareExecutor, Executor applyExecutor) {
        LootManager lootManager = (LootManager) (Object) this;
        return original.thenRun(() -> applyLootTableEvents(manager, lootManager));
    }
    @Unique
    @SuppressWarnings("all")
    private void applyLootTableEvents(ResourceManager resourceManager, LootManager lootManager) {
        HashMap<LootDataKey<?>, Object> newTables = new HashMap<>();
        this.keyToValue.forEach((dataKey, entry) -> {
            if (dataKey == LootManager.EMPTY_LOOT_TABLE || !(entry instanceof LootTable table)) {
                newTables.put(dataKey, entry);
                return;
            }
            LootTable.Builder builder = FabricLootTableBuilder.copyOf(table);
            SecondaryLootTableLoadingCallback.Companion.getEVENT().invoker().onLootTableLoading(resourceManager, lootManager, dataKey.id(), builder, s -> newTables.put(dataKey, s), LootUtil.determineSource(dataKey.id(), resourceManager));
            newTables.computeIfAbsent(dataKey, i -> builder.build());
        });
        this.keyToValue = ImmutableMap.copyOf(newTables);
    }
}