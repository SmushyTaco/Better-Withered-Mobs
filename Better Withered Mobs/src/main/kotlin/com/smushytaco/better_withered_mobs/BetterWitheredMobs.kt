package com.smushytaco.better_withered_mobs
import com.smushytaco.better_withered_mobs.mixin.BrewingRecipeRegistryAccessor.invokeRegisterPotionRecipe
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.`object`.builder.v1.block.FabricBlockSettings
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback
import net.minecraft.block.*
import net.minecraft.block.dispenser.FallibleItemDispenserBehavior
import net.minecraft.item.*
import net.minecraft.loot.ConstantLootTableRange
import net.minecraft.loot.entry.ItemEntry
import net.minecraft.loot.function.SetCountLootFunction
import net.minecraft.util.ActionResult
import net.minecraft.util.Identifier
import net.minecraft.util.math.BlockPointer
import net.minecraft.util.math.Direction
import net.minecraft.util.registry.Registry
import kotlin.random.Random
import net.minecraft.item.ItemStack
import net.minecraft.block.Blocks
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder
import net.minecraft.item.ItemGroup
import net.minecraft.loot.UniformLootTableRange
import net.minecraft.loot.function.LootingEnchantLootFunction
import net.minecraft.potion.Potions
@Suppress("unused")
object BetterWitheredMobs : ModInitializer {
    private const val MOD_ID = "better_withered_mobs"
    override fun onInitialize() {
        invokeRegisterPotionRecipe(Potions.AWKWARD, Items.WITHER_ROSE, PotionOfDecay.POTION_OF_DECAY)
        invokeRegisterPotionRecipe(PotionOfDecay.POTION_OF_DECAY, Items.REDSTONE, PotionOfDecay.LONG_POTION_OF_DECAY)
        invokeRegisterPotionRecipe(PotionOfDecay.POTION_OF_DECAY, Items.GLOWSTONE_DUST, PotionOfDecay.STRONG_POTION_OF_DECAY)
        Registry.register(Registry.BLOCK, Identifier(MOD_ID, "withered_bone_block"), WITHERED_BONE_BLOCK)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone_block"), BlockItem(WITHERED_BONE_BLOCK, Item.Settings()
            .group(BETTER_WITHERED_MOBS_GROUP)))
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone"), WITHERED_BONE)
        Registry.register(Registry.ITEM, Identifier(MOD_ID, "withered_bone_meal"), WITHERED_BONE_MEAL)
        DispenserBlock.registerBehavior(WITHERED_BONE_MEAL, WitheredBoneMealDispenserBehavior)
        LootTableLoadingCallback.EVENT.register(LootTableLoadingCallback { _, _, id, supplier, _ ->
            if ("minecraft:entities/wither_skeleton" == id.toString() ||
                "betternether:entities/naga" == id.toString() ||
                "betternether:entities/skull" == id.toString()) {
                val lootFunctionOne =
                    SetCountLootFunction.builder(UniformLootTableRange(0.0F, 2.0F))
                val lootFunctionTwo =
                    LootingEnchantLootFunction.builder(UniformLootTableRange(0.0F, 1.0F))
                val poolBuilder = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootTableRange.create(1))
                    .with(ItemEntry.builder(WITHERED_BONE).apply(lootFunctionOne)).apply(lootFunctionTwo)
                supplier.pool(poolBuilder)
            }
        })
    }
    private val BETTER_WITHERED_MOBS_GROUP: ItemGroup = FabricItemGroupBuilder.build(
        Identifier(MOD_ID, "items")
    ) { ItemStack(WITHERED_BONE_BLOCK) }
    private val WITHERED_BONE_MEAL = WitheredBoneMeal(Item.Settings().group(BETTER_WITHERED_MOBS_GROUP))
    private val WITHERED_BONE = Item(Item.Settings().group(BETTER_WITHERED_MOBS_GROUP))
    private val WITHERED_BONE_BLOCK = PillarBlock(FabricBlockSettings.of(Material.STONE, MaterialColor.SAND).strength(2.0F, 2.0F))
}
class WitheredBoneMeal(settings: Settings) : Item(settings) {
    override fun useOnBlock(itemUsageContext_1: ItemUsageContext): ActionResult {
        return if (itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos).block == Blocks.WARPED_NYLIUM && itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos.up()).block == Blocks.AIR ||
            itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos).block == Blocks.CRIMSON_NYLIUM && itemUsageContext_1.world.getBlockState(itemUsageContext_1.blockPos.up()).block == Blocks.AIR) {
            when(Random.nextInt(11)) {
                in 0..2 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.RED_MUSHROOM.defaultState)
                10 -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
                else -> itemUsageContext_1.world.setBlockState(itemUsageContext_1.blockPos.up(), Blocks.WITHER_ROSE.defaultState)
            }
            itemUsageContext_1.stack.decrement(1)
            ActionResult.SUCCESS
        } else {
            ActionResult.FAIL
        }
    }
}
object WitheredBoneMealDispenserBehavior : FallibleItemDispenserBehavior() {
    override fun dispenseSilently(blockPointer_1: BlockPointer, itemStack_1: ItemStack): ItemStack {
        val direction: Direction = blockPointer_1.blockState.get(DispenserBlock.FACING)
        isSuccess = false
        if (blockPointer_1.world.getBlockState(blockPointer_1.blockPos.offset(direction).down()).block == Blocks.WARPED_NYLIUM && blockPointer_1.world.getBlockState(blockPointer_1.blockPos.offset(direction)).block == Blocks.AIR ||
            blockPointer_1.world.getBlockState(blockPointer_1.blockPos.offset(direction).down()).block == Blocks.CRIMSON_NYLIUM && blockPointer_1.world.getBlockState(blockPointer_1.blockPos.offset(direction)).block == Blocks.AIR) {
            when(Random.nextInt(11)) {
                in 0..2 -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.WARPED_FUNGUS.defaultState)
                in 3..4 -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.BROWN_MUSHROOM.defaultState)
                in 5..7 -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.CRIMSON_FUNGUS.defaultState)
                in 8..9 -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.RED_MUSHROOM.defaultState)
                10 -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.WITHER_ROSE.defaultState)
                else -> blockPointer_1.world.setBlockState(blockPointer_1.blockPos.offset(direction), Blocks.WITHER_ROSE.defaultState)
            }
            itemStack_1.decrement(1)
            isSuccess = true
        }
        return itemStack_1
    }
}