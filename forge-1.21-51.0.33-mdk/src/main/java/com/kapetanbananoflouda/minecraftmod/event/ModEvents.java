package com.kapetanbananoflouda.minecraftmod.event;


import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import com.kapetanbananoflouda.minecraftmod.items.ModItems;
import com.kapetanbananoflouda.minecraftmod.potion.ModPotions;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.alchemy.PotionBrewing;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.brewing.BrewingRecipeRegisterEvent;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraft.world.level.Level;

@Mod.EventBusSubscriber(modid = MinecraftMod.MOD_ID, bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEvents {





    //public static void onBrewingRecipeRegister(BrewingRecipeRegisterEvent event) {
        //PotionBrewing.Builder builder = event.getBuilder();

        //builder.addMix(Potions.AWKWARD, ModItems.CRISP_CLOVER, ModPotions.LIQUID_UNLUCK.getHolder().get());

    //}*/ //Has some small issues
}

