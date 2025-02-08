package com.kapetanbananoflouda.minecraftmod.items.custom;

import com.kapetanbananoflouda.minecraftmod.ModSounds;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundSource;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;

import java.util.Random;


public class DiceAxe  extends AxeItem {
    public DiceAxe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }


    // Insert here crico.exe

    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // insert here
        if (!pLevel.isClientSide) {
            //run only on server , to avoid double use (learned from experience)


            pPlayer.getCooldowns().addCooldown(this, 100); //add cooldown for roulette
            Random dice = new Random();
            int roll = dice.nextInt(20) + 1;


            pPlayer.sendSystemMessage(Component.literal("You used the Dice pick\n"));
            pPlayer.sendSystemMessage(Component.literal("You rolled : " + roll));


            switch (roll) {
                //300 ticks is  15 sec
                case 1:
                    pPlayer.sendSystemMessage(Component.literal("\nYou have grown §clethargic..§ftake a §erest§f."));
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.FAIL_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 600, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 600, 3));
                    break;
                case 2, 3, 4, 5:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, 300, 4));
                    pPlayer.sendSystemMessage(Component.literal("\nYour mind is shrouded, you should rest."));
                    break;
                case 6, 7, 8, 9, 10:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 2));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300, 1));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HARM, 20, 0));
                    pPlayer.sendSystemMessage(Component.literal("\nYou accidentally cut yourself, Ouch!"));
                    break;
                case 11, 12, 13, 14, 15:

                    // ===================== CHECK THIS ONE CRICO
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, 400/*, roll-9*/));//3,4,5,6,7 for buffs.
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 400, 2));
                    pPlayer.sendSystemMessage(Component.literal("\n§eYou feel a surge of Inspiration!"));
                    break;
                case 16, 17, 18, 19:


                    // ============================ CHECK THIS ONE OUT
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,roll-13));//3,4,5,6 for amp
                    // pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\nPLACEHOLDER"));
                    break;

                case 20:
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,400,roll));
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,9));
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.SUCCESS_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    //pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\n PLACEHOLDER!"));

                    break;
                default:
                    break;

            }


            return super.use(pLevel, pPlayer, pUsedHand);


        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}






