package com.smushytaco.better_withered_mobs.mixin;
import com.smushytaco.better_withered_mobs.BetterWitheredMobs;
import com.smushytaco.better_withered_mobs.WitheringEnchantment;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.AbstractSkeletonEntity;
import net.minecraft.entity.mob.WitherSkeletonEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.LocalDifficulty;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import java.util.Random;
@Mixin(WitherSkeletonEntity.class)
public abstract class WitherSkeletonEntityInitEquipment extends AbstractSkeletonEntity {
    protected WitherSkeletonEntityInitEquipment(EntityType<? extends AbstractSkeletonEntity> entityType, World world) {
        super(entityType, world);
    }
    @Inject(method = "initEquipment", at = @At("TAIL"))
    protected void addWitheringStoneSword(LocalDifficulty difficulty, CallbackInfo ci) {
        WitheringEnchantment witheringEnchantment = BetterWitheredMobs.INSTANCE.getWITHERING_ENCHANTMENT();
        ItemStack stoneSword = new ItemStack(Items.STONE_SWORD);
        int randomEnchantLevel = new Random()
                .nextInt((witheringEnchantment.getMaxLevel() - witheringEnchantment.getMinLevel()) + 1)
                + witheringEnchantment.getMinLevel();
        stoneSword.addEnchantment(witheringEnchantment, randomEnchantLevel);
        this.equipStack(EquipmentSlot.MAINHAND, stoneSword);
    }
}