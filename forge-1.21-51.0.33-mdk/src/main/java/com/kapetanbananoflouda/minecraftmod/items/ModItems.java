package com.kapetanbananoflouda.minecraftmod.items;

import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import net.minecraft.client.renderer.item.ItemProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, MinecraftMod.MOD_ID);
    // Explains to forge that these are our Items and where to register them. Effectively a list.


    // This is how to make items.
    public static  final RegistryObject<Item> TOAST = ITEMS.register("toast",
            ()-> new Item(new Item.Properties()));


    // Clover Item - Edible, Core item
    public static final  RegistryObject<Item> CLOVER = ITEMS.register("clover",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.CLOVER)));

    // Burnt clover item - Edible, Core Item
    public static final RegistryObject<Item> CRISP_CLOVER = ITEMS.register("crisp_clover",
            ()-> new Item(new Item.Properties().food(ModFoodProperties.CRISP_CLOVER)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


