package com.smushytaco.better_withered_mobs
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMeal
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMealDispenserBehavior
import com.smushytaco.better_withered_mobs.configuration_support.ModConfiguration
import com.smushytaco.better_withered_mobs.event.SecondaryLootTableLoadingCallback
import com.smushytaco.better_withered_mobs.loot_table_modification.LootTableModificationSyntacticSugar.item
import com.smushytaco.better_withered_mobs.loot_table_modification.LootTableModificationSyntacticSugar.tablePools
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.minecraft.block.*
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.loot.LootPool
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.LootingEnchantLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.loot.provider.number.ConstantLootNumberProvider
import net.minecraft.loot.provider.number.UniformLootNumberProvider
import net.minecraft.registry.Registries
import net.minecraft.registry.Registry
import net.minecraft.sound.BlockSoundGroup
import net.minecraft.text.Text
import net.minecraft.util.Identifier
object BetterWitheredMobs : ModInitializer {
    const val MOD_ID = "better_withered_mobs"
    lateinit var config: ModConfiguration
        private set
    override fun onInitialize() {
        PotionOfDecay.createPotionRecipes()

        AutoConfig.register(ModConfiguration::class.java) { definition: Config, configClass: Class<ModConfiguration> ->
            GsonConfigSerializer(definition, configClass)
        }
        config = AutoConfig.getConfigHolder(ModConfiguration::class.java).config

        Registry.register(Registries.BLOCK, Identifier(MOD_ID, "withered_bone_block"), WITHERED_BONE_BLOCK)
        val witheredBoneBlockItem = Registry.register(Registries.ITEM, Identifier(MOD_ID, "withered_bone_block"), BlockItem(WITHERED_BONE_BLOCK, Item.Settings().fireproof()))
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.add(witheredBoneBlockItem) })
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "withered_bone"), WITHERED_BONE)
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.add(WITHERED_BONE) })
        Registry.register(Registries.ITEM, Identifier(MOD_ID, "withered_bone_meal"), WITHERED_BONE_MEAL)
        ItemGroupEvents.modifyEntriesEvent(BETTER_WITHERED_MOBS_GROUP).register(ItemGroupEvents.ModifyEntries { it.add(WITHERED_BONE_MEAL) })
        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, WitheredBoneMealDispenserBehavior)
        Registry.register(Registries.ENCHANTMENT, Identifier(MOD_ID, "withering"), WITHERING_ENCHANTMENT)
        SecondaryLootTableLoadingCallback.EVENT.register(SecondaryLootTableLoadingCallback { _, lootManager, id, _, setter ->
            if ("minecraft:entities/wither_skeleton" == id.toString() ||
                "betternether:entities/naga" == id.toString() ||
                "betternether:entities/skull" == id.toString()) {
                // Removes Bone Drops
                if (config.witheredMobsDontDropRegularBones) {
                    val lootPoolsToRemove = arrayListOf<LootPool>()
                    lootManager.getTable(id).pools.forEach { lootPool ->
                        lootPool.entries.forEach { lootPoolEntry ->
                            if (lootPoolEntry is ItemEntry && Registries.ITEM.getRawId(lootPoolEntry.item) ==
                                Registries.ITEM.getRawId(Items.BONE)
                            ) {
                                lootPoolsToRemove.add(lootPool)
                            }
                        }
                    }
                    lootPoolsToRemove.forEach { lootPool ->
                        val lootPoolListClone = lootManager.getTable(id).pools.toMutableList()
                        lootPoolListClone.removeAll { it == lootPool }
                        lootManager.getTable(id).tablePools = lootPoolListClone.toTypedArray()
                    }
                }
                // Adds Withered Bone Drops
                val lootFunctionOne =
                    SetCountLootFunction.builder(UniformLootNumberProvider.create(0.0F, 2.0F))
                val lootFunctionTwo =
                    LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(0.0F, 1.0F))
                val poolBuilder = LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1.0F))
                    .with(ItemEntry.builder(WITHERED_BONE).apply(lootFunctionOne)).apply(lootFunctionTwo)
                val lootPoolListClone = lootManager.getTable(id).pools.toMutableList()
                lootPoolListClone.add(poolBuilder.build())
                lootManager.getTable(id).tablePools = lootPoolListClone.toTypedArray()
                // This applies changes to loot table.
                setter.set(lootManager.getTable(id))
            }
        })
    }
    val WITHERING_ENCHANTMENT = WitheringEnchantment()
    private val BETTER_WITHERED_MOBS_GROUP = FabricItemGroup.builder(Identifier(MOD_ID, MOD_ID)).displayName(Text.literal("Better Withered Mobs")).icon { ItemStack(WITHERED_BONE_BLOCK) }.build()
    private val WITHERED_BONE_MEAL = WitheredBoneMeal(Item.Settings().fireproof())
    private val WITHERED_BONE = Item(Item.Settings().fireproof())
    private val WITHERED_BONE_BLOCK = PillarBlock(FabricBlockSettings.of(Material.STONE, MapColor.PALE_YELLOW)
        .requiresTool().strength(2.0F).sounds(BlockSoundGroup.BONE))
}