package com.kapetanbananoflouda.minecraftmod.items;

import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;

public class ModFoodProperties {

    // Clover you pick up from breaking grass
    public static final FoodProperties CLOVER = new FoodProperties.Builder().nutrition(3).saturationModifier(0.6f)
            .effect(new MobEffectInstance(MobEffects.LUCK, 300), 1f)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 400), 0.25f) //Are you stupid? You pick up random clovers and eat them? Bad for your health
            .fast()
            .alwaysEdible()
            .build();

    // Clover when you decide to smelt it. Sure you can eat it but do you want to?
    public static final  FoodProperties CRISP_CLOVER = new FoodProperties.Builder()
            .effect(new MobEffectInstance(MobEffects.UNLUCK, 300),1f)
            .effect(new MobEffectInstance(MobEffects.HUNGER, 800, 2), 0.9f)
            .effect(new MobEffectInstance(MobEffects.CONFUSION, 800), 0.9f)
            .effect(new MobEffectInstance(MobEffects.POISON, 100, 3), 0.9f)
            .fast()
            .alwaysEdible()
            .build();
}
