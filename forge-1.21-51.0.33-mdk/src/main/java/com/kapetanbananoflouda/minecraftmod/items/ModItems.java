package com.kapetanbananoflouda.minecraftmod.items;
//import num item
import com.kapetanbananoflouda.minecraftmod.items.custom.NumberItem;
import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
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


    //bazo depon kai xanax
    //register the item (kane ayto gia kathe item)
    public static final RegistryObject<Item> DEPON_PACKET = ITEMS.register("depon_packet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6) // Hunger restored (6 = 3 meat drumsticks)
            .saturationModifier(0.6F) // Saturation modifier (how long the hunger lasts)
            .alwaysEdible() // Allows eating even if the player is full
            .build())) );

    public static final RegistryObject<Item> XANAX_PACKET = ITEMS.register("xanax_packet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(6) // Hunger restored (6 = 3 meat drumsticks)
            .saturationModifier(0.6F) // Saturation modifier (how long the hunger lasts)
            .alwaysEdible() // Allows eating even if the player is full
            .effect(new MobEffectInstance(MobEffects.POISON, 150, 3),1f)
            .build())) );

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}


