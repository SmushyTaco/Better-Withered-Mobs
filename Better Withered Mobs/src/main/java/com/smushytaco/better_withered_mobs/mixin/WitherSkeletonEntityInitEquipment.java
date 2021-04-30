package com.smushytaco.better_withered_mobs.mixin;
import com.smushytaco.better_withered_mobs.BetterWitheredMobs;
import com.smushytaco.better_withered_mobs.mixin_logic.WitherSkeletonEntityInitEquipmentLogic;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityInitEquipment extends AbstractSkeletonEntity {
    protected WitherSkeletonEntityInitEquipment(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "initEquipment", at = @At("TAIL"))
    protected void addWitheringStoneSword(LocalDifficulty difficulty, CallbackInfo ci) {
        if (!BetterWitheredMobs.INSTANCE.getConfig().getWitherSkeletonsSpawnWithWitheringEnchantedStoneSwords()) return;
        this.equipStack(EquipmentSlot.MAINHAND,
                WitherSkeletonEntityInitEquipmentLogic.INSTANCE.stoneSwordWithWitheringEnchantment());
    }
}