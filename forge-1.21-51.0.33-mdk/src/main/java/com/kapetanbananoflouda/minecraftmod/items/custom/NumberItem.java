package com.kapetanbananoflouda.minecraftmod.items.custom;

import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.context.UseOnContext;
import net.minecraft.world.level.Level;
import net.minecraft.network.chat.Component;
import java.util.Random;

public class NumberItem extends Item {
    public NumberItem(Properties pProperties)
    {
        super(pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {

        Random random = new Random();
        int lucky_num = random.nextInt(100);
        if (!pLevel.isClientSide) { // Ensure the message only appears on the server side
            pPlayer.sendSystemMessage(Component.literal("You used the custom item!\nYour lucky number is: "+lucky_num));
        }
        //animation

        //play sound on player pos
        pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_BLAST,SoundSource.PLAYERS, 1.0F, 1.0F);
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}


