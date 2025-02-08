package com.kapetanbananoflouda.minecraftmod.sound;

import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.EventBus;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModSounds {
    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MinecraftMod.MOD_ID);


    // Great Success
    public static final RegistryObject<SoundEvent> SUCCESS_ROLL = registrySoundEvent("success_roll");

    // Failure
    public static final RegistryObject<SoundEvent> FAIL_ROLL = registrySoundEvent("fail_roll");

    // Eating Sounds.wav
    public static final RegistryObject<SoundEvent> XANAX_EATING = registrySoundEvent("xanax_eating");
    public static final RegistryObject<SoundEvent> DEPON_EATING = registrySoundEvent("depon_eating");
    // public static final RegistryObject<SoundEvent> SEX = registrySoundEvent("sex");




    private static RegistryObject<SoundEvent> registrySoundEvent(String name) {
        return  SOUND_EVENTS.register(name, ()->SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinecraftMod.MOD_ID, name)));

    }

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}
