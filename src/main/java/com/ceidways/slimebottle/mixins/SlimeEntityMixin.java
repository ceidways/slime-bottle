package com.ceidways.slimebottle.mixins;

import com.ceidways.slimebottle.SlimeBottleMod;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.mob.SlimeEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.*;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;

@Mixin(SlimeEntity.class)
public abstract class SlimeEntityMixin extends MobEntity implements Monster {
    @Shadow public abstract boolean isSmall();
    protected SlimeEntityMixin(EntityType<? extends MobEntity> entityType, World world) {
        super(entityType, world);
    }
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack itemStack = player.getStackInHand(hand);
        Item item = itemStack.getItem();
        if (itemStack.isOf(Items.GLASS_BOTTLE) && this.isSmall()) {
            itemStack.decrement(1);
            this.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SLIME_DEATH, SoundCategory.NEUTRAL, 1.0f, 0.5f);
            this.world.playSound(player, player.getX(), player.getY(), player.getZ(), SoundEvents.ENTITY_SLIME_DEATH, SoundCategory.NEUTRAL, 2.0f, 1f);
            if (itemStack.isEmpty()) {
                player.setStackInHand(hand, new ItemStack(SlimeBottleMod.SLIME_BOTTLE));
            } else if (!player.getInventory().insertStack(new ItemStack(SlimeBottleMod.SLIME_BOTTLE))) {
                player.dropItem(new ItemStack(SlimeBottleMod.SLIME_BOTTLE), false);
            }
            this.discard();
            if (!world.isClient()) {
                player.incrementStat(Stats.USED.getOrCreateStat(item));
            }
            return ActionResult.success(this.world.isClient);
        } else {
            return super.interactMob(player, hand);
        }
    }
}
