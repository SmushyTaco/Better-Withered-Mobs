package com.smushytaco.better_withered_mobs
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMeal
import com.smushytaco.better_withered_mobs.bone_meal.WitheredBoneMealDispenserBehavior
import com.smushytaco.better_withered_mobs.configuration_support.ModConfiguration
import com.smushytaco.better_withered_mobs.fireproof_items.FireproofBlockItem
import com.smushytaco.better_withered_mobs.fireproof_items.FireproofItem
import com.smushytaco.better_withered_mobs.fireproof_items.FireproofSyntacticSugar.fireproof
import me.shedaniel.autoconfig.AutoConfig
import me.shedaniel.autoconfig.annotation.Config
import me.shedaniel.autoconfig.serializer.GsonConfigSerializer
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.block.DispenserBlock
import net.minecraft.block.Material
import net.minecraft.block.MaterialColor
import net.minecraft.block.PillarBlock
import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.item.Items
import net.minecraft.loot.UniformLootTableRange
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.LootingEnchantLootFunction
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry
object BetterWitheredMobs : ModInitializer {
    const val MOD_ID = "better_withered_mobs"
    lateinit var config: ModConfiguration
        private set
    override fun onInitialize() {
        Items.WITHER_SKELETON_SKULL.fireproof = true
        PotionOfDecay.createPotionRecipes()

        AutoConfig.register(ModConfiguration::class.java) { definition: Config?, configClass: Class<ModConfiguration?>? ->
            GsonConfigSerializer(definition, configClass)
        }
        config = AutoConfig.getConfigHolder(ModConfiguration::class.java).config

        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "withered_bone_block"), WITHERED_BONE_BLOCK)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone_block"), FireproofBlockItem(WITHERED_BONE_BLOCK, Item.Settings()
            .group(BETTER_WITHERED_MOBS_GROUP)))
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone"), WITHERED_BONE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone_meal"), WITHERED_BONE_MEAL)
        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, WitheredBoneMealDispenserBehavior)
        Registry.register(Registry.ENCHANTMENT, Identifier(MOD_ID, "withering"), WITHERING_ENCHANTMENT)

        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { _, _, id, supplier, _ ->
            if ("minecraft:entities/wither_skeleton" == id.toString() ||
                "betternether:entities/naga" == id.toString() ||
                "betternether:entities/skull" == id.toString()) {
                val lootFunctionOne =
                    SetCountLootFunction.builder(UniformLootTableRange(0.0F, 2.0F))
                val lootFunctionTwo =
                    LootingEnchantLootFunction.builder(UniformLootTableRange(0.0F, 1.0F))
                val poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(UniformLootTableRange(0.0F, 2.0F))
                    .with(ItemEntry.builder(WITHERED_BONE).apply(lootFunctionOne)).apply(lootFunctionTwo)
                supplier.pool(poolBuilder)
            }
        })
    }
    val WITHERING_ENCHANTMENT = WitheringEnchantment()
    private val BETTER_WITHERED_MOBS_GROUP = FabricItemGroupBuilder
        .build(Identifier(MOD_ID, "items")) { ItemStack(WITHERED_BONE_BLOCK) }
    private val WITHERED_BONE_MEAL = WitheredBoneMeal(Item.Settings().group(BETTER_WITHERED_MOBS_GROUP))
    private val WITHERED_BONE = FireproofItem(Item.Settings().group(BETTER_WITHERED_MOBS_GROUP))
    private val WITHERED_BONE_BLOCK = PillarBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 2.0F))
}