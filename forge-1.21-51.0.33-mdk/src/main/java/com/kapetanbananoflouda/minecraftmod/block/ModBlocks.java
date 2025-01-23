package com.kapetanbananoflouda.minecraftmod.block;

import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import com.kapetanbananoflouda.minecraftmod.items.ModItems;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import java.util.function.Supplier;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, MinecraftMod.MOD_ID);

    // =========================================================================================================
    // This is how to create blocks!
    public static final RegistryObject<Block> CAT_BLOCK = registerBlock("cat_block",
            ()-> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).requiresCorrectToolForDrops().sound(SoundType.ANVIL)));


    //gon write the blocks here
    //required loot table to drop anything
    public static final RegistryObject<Block> DEPON_BLOCK = registerBlock("depon_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f).sound(SoundType.GRASS)));


    public static final RegistryObject<Block> XANAX_BLOCK = registerBlock( "xanax_block",
            () -> new Block(BlockBehaviour.Properties.of()
                    .strength(1f)));



    //2 helper methods
    //1st for block item to assosciate with the block we register
    private static  <T extends Block> RegistryObject<T> registerBlock(String name, Supplier<T> block) {
        RegistryObject<T> toReturn = BLOCKS.register(name, block);
        registerBlockItem(name, toReturn);
        return  toReturn;
    }


    // Registers Block item and its association with our Block
    private static <T extends Block> void registerBlockItem(String name, RegistryObject<T> block) {
        ModItems.ITEMS.register(name, ()-> new BlockItem(block.get(), new Item.Properties()));
    }



    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}
