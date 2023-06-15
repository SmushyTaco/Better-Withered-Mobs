package com.smushytaco.better_withered_mobs.mixins;
import com.smushytaco.better_withered_mobs.BetterWitheredMobs;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.util.math.random.Random;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityInitEquipment extends AbstractSkeletonEntity {
    protected WitherSkeletonEntityInitEquipment(EntityType<? extends AbstractSkeletonEntity> entityType, World world) { super(entityType, world); }
    @Inject(method = "updateEnchantments", at = @At("RETURN"))
    protected void addWitheringStoneSword(Random random, LocalDifficulty localDifficulty, CallbackInfo ci) { if (BetterWitheredMobs.INSTANCE.getConfig().getWitherSkeletonsSpawnWithWitheringEnchantedStoneSwords()) getMainHandStack().addEnchantment(BetterWitheredMobs.INSTANCE.getWITHERING_ENCHANTMENT(), random.nextBetween(BetterWitheredMobs.INSTANCE.getWITHERING_ENCHANTMENT().getMinLevel(), BetterWitheredMobs.INSTANCE.getWITHERING_ENCHANTMENT().getMaxLevel())); }
}