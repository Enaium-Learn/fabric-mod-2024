package com.example.enchantment;

import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.GameRules;
import net.minecraft.world.World;

public class FireBoomEnchantment extends Enchantment {
    public FireBoomEnchantment(Rarity rarity, EnchantmentTarget target, EquipmentSlot[] slotTypes) {
        super(rarity, target, slotTypes);
    }

    @Override
    public void onTargetDamaged(LivingEntity user, Entity target, int level) {
        boolean bl = user.getWorld().getGameRules().getBoolean(GameRules.DO_MOB_GRIEFING);
        user.getWorld().createExplosion(user, target.getX(), target.getY(), target.getZ(), 1f, bl, World.ExplosionSourceType.MOB);
    }
}
