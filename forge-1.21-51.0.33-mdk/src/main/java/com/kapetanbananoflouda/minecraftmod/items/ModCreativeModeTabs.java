package com.kapetanbananoflouda.minecraftmod.items;

import com.google.common.eventbus.EventBus;
import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import com.kapetanbananoflouda.minecraftmod.block.ModBlocks;
import net.minecraft.core.registries.Registries;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;

public class ModCreativeModeTabs {

    public static final DeferredRegister<CreativeModeTab> CREATIVE_MODE_TABS =
            DeferredRegister.create(Registries.CREATIVE_MODE_TAB, MinecraftMod.MOD_ID);

    // =============================================================================
    // This is how to make a creative tab.
    // This is a Creative Tab for all the items of the mod and mob spawners and whatever
    public static final RegistryObject<CreativeModeTab> NUMEROLOGY_ITEMS_TAB = CREATIVE_MODE_TABS.register("numerology_items_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModItems.TOAST.get()))
                    .title(Component.translatable("creativetab.uniminecraftmod.numerology_items"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModItems.TOAST.get());


                    }).build());


    // This is a creative tab for all the blocks of the mod.
    public static final RegistryObject<CreativeModeTab> NUMEROLOGY_BLOCKS_TAB = CREATIVE_MODE_TABS.register("numerology_blocks_tab",
            () -> CreativeModeTab.builder().icon(() -> new ItemStack(ModBlocks.CAT_BLOCK.get()))
                    .withTabsBefore(NUMEROLOGY_ITEMS_TAB.getId())
                    .title(Component.translatable("creativetab.uniminecraftmod.numerology_blocks"))
                    .displayItems((itemDisplayParameters, output) -> {
                        output.accept(ModBlocks.CAT_BLOCK.get());


                    }).build());


    public static void register(IEventBus eventBus) {
        CREATIVE_MODE_TABS.register(eventBus);
    }

}
