package com.kapetanbananoflouda.minecraftmod.event;

import com.kapetanbananoflouda.minecraftmod.items.ModItems;
import net.minecraft.core.BlockPos;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.event.level.BlockEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

import java.util.Random;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE)
public class ModEventHandler {
//using event to not mess with original loot tables
    @SubscribeEvent
    public static void onGrassBreak(BlockEvent.BreakEvent event) {
        Level level = (Level) event.getLevel();
        BlockPos pos = event.getPos();
        BlockState state = event.getState();


        // Check if the broken block is Grass or Tall Grass
        if (!level.isClientSide() && state.is(Blocks.SHORT_GRASS) || state.is(Blocks.TALL_GRASS)) {
            ServerLevel serverLevel = (ServerLevel) level;

            //find the block
            BlockState blockState = level.getBlockState(pos);
            Block block = blockState.getBlock();
            ItemStack drop = new ItemStack(ModItems.CLOVER.get(), 1); // Drops 1 Clover item

            // Drop the item at the grass block's position
            Random dice = new Random();
            int roll = dice.nextInt(100) + 1;
            if(roll <= 25) //25% chance to drop
            {//drop
                block.popResource(serverLevel, pos, drop);

            }
        }
    }
}
