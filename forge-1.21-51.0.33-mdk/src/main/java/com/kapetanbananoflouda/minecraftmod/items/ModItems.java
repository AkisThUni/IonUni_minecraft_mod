package com.kapetanbananoflouda.minecraftmod.items;
//import num item
import com.kapetanbananoflouda.minecraftmod.items.custom.NumberItem;
import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
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

    //bazo to generator
    public static final RegistryObject<Item>  NUMBER_ITEM= ITEMS.register("number_generator",
            () -> new NumberItem(new Item.Properties().durability(32)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


