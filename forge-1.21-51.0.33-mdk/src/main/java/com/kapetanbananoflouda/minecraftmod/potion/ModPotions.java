package com.kapetanbananoflouda.minecraftmod.potion;

import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.item.alchemy.Potion;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModPotions {

    public static final DeferredRegister<Potion> POTIONS =
            DeferredRegister.create(ForgeRegistries.POTIONS, MinecraftMod.MOD_ID);


    // Luck Potions
    public  static final RegistryObject<Potion> LIQUID_LUCK = POTIONS.register("liquid_luck",
            () -> new Potion(new MobEffectInstance(MobEffects.LUCK, 300, 0)));

    // Unluck Potions
    public  static final RegistryObject<Potion> LIQUID_UNLUCK = POTIONS.register("liquid_unluck",
            () -> new Potion(new MobEffectInstance(MobEffects.UNLUCK, 300, 0)));



    public static void register(IEventBus eventBus) {
        POTIONS.register(eventBus);
    }
}
