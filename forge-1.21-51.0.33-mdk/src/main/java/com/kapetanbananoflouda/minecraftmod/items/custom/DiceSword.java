package com.kapetanbananoflouda.minecraftmod.items.custom;

import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.SwordItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.Random;


public class DiceSword extends SwordItem {
    public DiceSword(Tier pTier, Properties pProperties)
    {
        super(pTier,pProperties);
    }

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        /*if (pPlayer.getCooldowns().isOnCooldown(this)) {

            return InteractionResultHolder.fail(pPlayer.getItemInHand(pUsedHand));
        }*/

        if (!pLevel.isClientSide) { // Ensure the message only appears on the server side and player is on the server side
            //add a player cooldown for this item for 30 seconds
            pPlayer.getCooldowns().addCooldown(this,600);


            Random dice = new Random();

            int number = dice.nextInt(20) +1; //d20 roll



            pPlayer.sendSystemMessage(Component.literal("You used the Dice blade!\nYour lucky number is: "+number));


            if(number == 20) //critical success
            {
                //adds strength )20 ticks = 1 sec
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_LARGE_BLAST, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 9));  //+9 for strength 10
                pPlayer.sendSystemMessage(Component.literal("\nGREAT SUCCESS\n"));

                return super.use(pLevel, pPlayer, pUsedHand);
            }

            if(number <= 19 && number >= 16 )
            {
                //fire res,absorption,regeneration
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_TWINKLE_FAR, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 9));  //+9 for strength 10
                pPlayer.sendSystemMessage(Component.literal("\nNICE\n"));

                return super.use(pLevel, pPlayer, pUsedHand);

            }

            if(number <= 15 && number >= 11)
            {
                //speed-haste-night vision-conduit power-water breathing
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200, 0));
                pPlayer.sendSystemMessage(Component.literal("\nGood\n"));

                return super.use(pLevel, pPlayer, pUsedHand);

            }

            if(number <= 10 && number >= 6)
            {
                //slowness - jump boost - blindness
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.ZOMBIE_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 0));
                pPlayer.sendSystemMessage(Component.literal("\nAlmost\n"));

                return super.use(pLevel, pPlayer, pUsedHand);
            }

            if(number <= 5 && number >= 2)
            {
                //poison - hunger - nausea
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                pPlayer.sendSystemMessage(Component.literal("\nBad Luck\n"));

                return super.use(pLevel, pPlayer, pUsedHand);
            }

            if(number == 1) //critical failure
            {
                //adds wither effect - weakness - mining fatigue)20 ticks = 1 sec
                pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.WITHER_SHOOT, SoundSource.PLAYERS, 1.0F, 1.0F);
                pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 0));
                pPlayer.sendSystemMessage(Component.literal("\nFUMBLE\n"));

                return super.use(pLevel, pPlayer, pUsedHand);

            }















            //play sound on player pos (different sound per number)
            //pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_TWINKLE, SoundSource.PLAYERS, 1.0F, 1.0F);
        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
