package com.kapetanbananoflouda.minecraftmod.items;
//import num item
import com.kapetanbananoflouda.minecraftmod.items.custom.*; //import all the custom items
import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.*;
import net.minecraft.world.level.levelgen.structure.templatesystem.rule.blockentity.Clear;
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
            .nutrition(1) // Hunger restored (6 = 3 meat drumsticks)
            .saturationModifier(0.6F) // Saturation modifier (how long the hunger lasts)
            .alwaysEdible()
            // Allows eating even if the player is full
            .build())) );

    public static final RegistryObject<Item> XANAX_PACKET = ITEMS.register("xanax_packet", () -> new Item(new Item.Properties().food(new FoodProperties.Builder()
            .nutrition(1) // Hunger restored (6 = 3 meat drumsticks)
            .saturationModifier(0.6F) // Saturation modifier (how long the hunger lasts)
            .alwaysEdible() // Allows eating even if the player is full
            .effect(new MobEffectInstance(MobEffects.POISON, 150, 3),1f)
            .build())) );


    //DICE BLADE GO WOOOOOO (definetely bot gambling)
    public static final RegistryObject<Item> DICE_BLADE = ITEMS.register("dice_blade",
            () -> new DiceSword(
                    Mod_Tiers.DICE_TIER,//base tier (base stats)

                    new Item.Properties()   //give sword atributes on top of dice tier ones
                            .attributes(SwordItem.createAttributes(Mod_Tiers.DICE_TIER,3,-3f))


            ));
    // ======================== Dice Pickaxe
    public static final RegistryObject<Item> DICE_PICK = ITEMS.register("dice_pick",
            () -> new DicePick(
                    Mod_Tiers.DICE_TIER,//base tier (base stats)

                    new Item.Properties()   //give sword atributes on top of dice tier ones
                            .attributes(PickaxeItem.createAttributes(Mod_Tiers.DICE_TIER,3,-3f))


            ));
    // ======================== Dice Axe
    public static  final RegistryObject<Item> DICE_AXE = ITEMS.register("dice_axe",
            () -> new DiceAxe(
                    Mod_Tiers.DICE_TIER,
                    new Item.Properties()
                            .attributes(AxeItem.createAttributes(Mod_Tiers.DICE_TIER,3,-3f))

            ));

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


