package com.smushytaco.better_withered_mobs
import com.google.common.collect.ImmutableList
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMeal
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMealDispenserBehavior
import com.smushytaco.better_withered_mobs.mixins.ItemEntryItemAccessor
import com.smushytaco.better_withered_mobs.mixins.LootTablePoolsAccessor
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.loot.v3.LootTableEvents
import net.fabricmc.fabric.api.registry.FabricBrewingRecipeRegistryBuilder
import net.minecraft.core.Registry
import net.minecraft.core.registries.BuiltInRegistries
import net.minecraft.core.registries.Registries
import net.minecraft.data.registries.VanillaRegistries
import net.minecraft.network.chat.Component
import net.minecraft.resources.ResourceKey
import net.minecraft.resources.ResourceLocation
import net.minecraft.world.item.BlockItem
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.level.block.DispenserBlock
import net.minecraft.world.level.block.RotatedPillarBlock
import net.minecraft.world.level.block.SoundType
import net.minecraft.world.level.block.state.BlockBehaviour
import net.minecraft.world.level.block.state.properties.NoteBlockInstrument
import net.minecraft.world.level.material.MapColor
import net.minecraft.world.level.storage.loot.LootPool
import net.minecraft.world.level.storage.loot.entries.LootItem
import net.minecraft.world.level.storage.loot.functions.EnchantedCountIncreaseFunction
import net.minecraft.world.level.storage.loot.functions.SetItemCountFunction
import net.minecraft.world.level.storage.loot.providers.number.ConstantValue
import net.minecraft.world.level.storage.loot.providers.number.UniformGenerator
object BetterWitheredMobs : ModInitializer {
    const val MOD_ID = "better_withered_mobs"
    private val config = ModConfig.createAndLoad()
    override fun onInitialize() {
        PotionOfDecay.registerPotions()
        FabricBrewingRecipeRegistryBuilder.BUILD.register { builder ->
            PotionOfDecay.createPotionRecipes(builder)
        }
        Registry.register(BuiltInRegistries.BLOCK, WITHERED_BONE_BLOCK_IDENTIFIER, WITHERED_BONE_BLOCK)
        val witheredBoneBlockItem = Registry.register(
            BuiltInRegistries.ITEM, WITHERED_BONE_BLOCK_IDENTIFIER, BlockItem(WITHERED_BONE_BLOCK, Item.Properties().fireResistant().useBlockDescriptionPrefix().setId(
                ResourceKey.create(Registries.ITEM, WITHERED_BONE_BLOCK_IDENTIFIER))))
        Registry.register(
            BuiltInRegistries.CREATIVE_MODE_TAB, BETTER_WITHERED_MOBS_GROUP, FabricItemGroup.builder().title(
                Component.literal("Better Withered Mobs")).icon { ItemStack(WITHERED_BONE_BLOCK) }.build())
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.accept(witheredBoneBlockItem) })
        Registry.register(BuiltInRegistries.ITEM, WITHERED_BONE_IDENTIFIER, WITHERED_BONE)
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.accept(WITHERED_BONE) })
        Registry.register(BuiltInRegistries.ITEM, WITHERED_BONE_MEAL_IDENTIFIER, WITHERED_BONE_MEAL)
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.accept(WITHERED_BONE_MEAL) })
        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, WitheredBoneMealDispenserBehavior)
        LootTableEvents.MODIFY.register { registryKey, builder, _, _ ->
            builder as LootTablePoolsAccessor
            if (registryKey.location() != ResourceLocation.fromNamespaceAndPath("minecraft", "entities/wither_skeleton") && registryKey.location() != ResourceLocation.fromNamespaceAndPath("betternether", "entities/naga") && registryKey.location() != ResourceLocation.fromNamespaceAndPath("betternether", "entities/skull")) return@register
            val lootFunctionOne = SetItemCountFunction.setCount(UniformGenerator.between(0.0F, 2.0F))
            val lootFunctionTwo = EnchantedCountIncreaseFunction.lootingMultiplier(VanillaRegistries.createLookup(), UniformGenerator.between(0.0F, 1.0F))
            val poolBuilder = LootPool.lootPool().setRolls(ConstantValue.exactly(1.0F)).add(LootItem.lootTableItem(WITHERED_BONE).apply(lootFunctionOne)).apply(lootFunctionTwo)
            builder.withPool(poolBuilder)
            if (!config.witheredMobsDontDropRegularBones) return@register
            val pools = builder.pools.build().toMutableList()
            pools.removeIf { lootPool ->
                lootPool.entries.any { lootPoolEntry ->
                    lootPoolEntry is LootItem && BuiltInRegistries.ITEM.getId((lootPoolEntry as ItemEntryItemAccessor).item.value()) == BuiltInRegistries.ITEM.getId(
                        Items.BONE)
                }
            }
            builder.pools = ImmutableList.builder<LootPool>().addAll(pools)
        }
    }
    private val BETTER_WITHERED_MOBS_GROUP = ResourceKey.create(Registries.CREATIVE_MODE_TAB, ResourceLocation.fromNamespaceAndPath(MOD_ID, MOD_ID))
    private val WITHERED_BONE_MEAL_IDENTIFIER = ResourceLocation.fromNamespaceAndPath(MOD_ID, "withered_bone_meal")
    private val WITHERED_BONE_MEAL = WitheredBoneMeal(
        Item.Properties().fireResistant().setId(
            ResourceKey.create(
                Registries.ITEM, WITHERED_BONE_MEAL_IDENTIFIER)))
    private val WITHERED_BONE_IDENTIFIER = ResourceLocation.fromNamespaceAndPath(MOD_ID, "withered_bone")
    private val WITHERED_BONE = Item(Item.Properties().fireResistant().setId(ResourceKey.create(Registries.ITEM, WITHERED_BONE_IDENTIFIER)))
    private val WITHERED_BONE_BLOCK_IDENTIFIER = ResourceLocation.fromNamespaceAndPath(MOD_ID, "withered_bone_block")
    private val WITHERED_BONE_BLOCK = RotatedPillarBlock(
        BlockBehaviour.Properties.of().mapColor(MapColor.COLOR_BLACK).instrument(
            NoteBlockInstrument.XYLOPHONE).requiresCorrectToolForDrops().strength(2.0F).sound(SoundType.BONE_BLOCK).setId(
            ResourceKey.create(Registries.BLOCK, WITHERED_BONE_BLOCK_IDENTIFIER)))
}