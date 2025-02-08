package com.kapetanbananoflouda.minecraftmod;

import net.minecraft.resources.ResourceLocation;
import net.minecraft.sounds.SoundEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import javax.swing.*;

public class ModSounds {

    public static final DeferredRegister<SoundEvent> SOUND_EVENTS =
            DeferredRegister.create(ForgeRegistries.SOUND_EVENTS, MinecraftMod.MOD_ID);


    private static RegistryObject<SoundEvent> registrySoundEvent(String name) {
        return SOUND_EVENTS.register(name, () -> SoundEvent.createVariableRangeEvent(ResourceLocation.fromNamespaceAndPath(MinecraftMod.MOD_ID, name)));
    }


    // ===============================================================================================================
    //============================                    SOUNDS                        ==================================
    // ===============================================================================================================

    // Following Sounds effectivelly play when Nat20 or Nat 1 happens.

    // Great Success.ogg
    public static final RegistryObject<SoundEvent> SUCCESS_ROLL = registrySoundEvent("success_roll");



    // Failure Sound.ogg
    public static final RegistryObject<SoundEvent> FAIL_ROLL = registrySoundEvent("fail_roll");

    public static void register(IEventBus eventBus) {
        SOUND_EVENTS.register(eventBus);
    }
}

