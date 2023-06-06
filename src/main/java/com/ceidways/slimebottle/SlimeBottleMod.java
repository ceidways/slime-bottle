package com.ceidways.slimebottle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


import static net.minecraft.item.Items.GLASS_BOTTLE;

public class SlimeBottleMod implements ModInitializer {

    public static final String MOD_ID = "slimebottle";

    // food stats
    public static final FoodComponent SLIME_BOTTLE_FOOD = (new FoodComponent.Builder()).hunger(6).saturationModifier(0.4F).statusEffect(new StatusEffectInstance(StatusEffects.NAUSEA, 600, 9), 1.0F).build();
    //HONEY_BOTTLE = new FoodComponent.Builder().hunger(6).saturationModifier(0.1f).build();
    // an instance of our new item
    public static final SlimeBottleItem SLIME_BOTTLE = new SlimeBottleItem(new FabricItemSettings().recipeRemainder(GLASS_BOTTLE).food(SLIME_BOTTLE_FOOD).group(ItemGroup.FOOD).maxCount(16));

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MOD_ID, "slime_bottle"), SLIME_BOTTLE);
    }
}
