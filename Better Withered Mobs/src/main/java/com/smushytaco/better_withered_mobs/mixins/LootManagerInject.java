package com.smushytaco.better_withered_mobs.mixins;
import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.smushytaco.better_withered_mobs.mixin_logic.LootManagerInjectLogic;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootTable;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;
import net.minecraft.util.profiler.Profiler;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Map;
@Mixin(LootManager.class)
public abstract class LootManagerInject {
    @Shadow
    private Map<Identifier, LootTable> tables;
    @Inject(method = "apply", at = @At("RETURN"))
    private void apply(Map<Identifier, JsonObject> objectMap, ResourceManager manager, Profiler profiler, CallbackInfo info) {
        LootManager lootManager = (LootManager) (Object) this;
        tables = ImmutableMap.copyOf(LootManagerInjectLogic.INSTANCE.invokeEarlyLootTableLoadingCallback(tables, manager, lootManager));
    }
}