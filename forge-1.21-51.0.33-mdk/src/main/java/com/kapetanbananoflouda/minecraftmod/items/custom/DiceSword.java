package com.kapetanbananoflouda.minecraftmod.items.custom;

import com.kapetanbananoflouda.minecraftmod.ModSounds;
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
        //on use a d20 dice will be rolled thus deciding the tier of the effect to be granted
        //then a selection will be made to select a specific effect among those int the tier

        if (!pLevel.isClientSide) { // Ensure the message only appears on the server side and player is on the server side
            //add a player cooldown for this item for 30 seconds 600 ticks
            pPlayer.getCooldowns().addCooldown(this,60);


            Random dice = new Random();

            int roll = dice.nextInt(20) +1; //d20 roll
            int effect_selector;


            pPlayer.sendSystemMessage(Component.literal("@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()\n"));
            pPlayer.sendSystemMessage(Component.literal("You used the Dice blade!--Your lucky number is: "+ roll ));


            //should probably make an effect selector/ printing function cause this is getting big


            switch (roll){
                case 20: //critical success ->best tier
                    //adds strength (20 ticks = 1 sec) and resistance 10
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), ModSounds.SUCCESS_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST, 200, 9));  //+9 for strength 10
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_RESISTANCE, 200, 9));
                    pPlayer.sendSystemMessage(Component.literal("\n! GREAT SUCCESS !\n\nYOU BECOME SIGNIFICANTLY STRONGER\n"));
                    break;

                case 19,18,17,16: //significant buff tier
                    //fire res,absorption,regeneration
                    effect_selector = dice.nextInt(3)+1;
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_TWINKLE_FAR, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.sendSystemMessage(Component.literal("\n! EXCELLENT !"));

                    if( effect_selector == 1)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.REGENERATION, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYOU ARE GRANTED THE POWER OF REGENERATION!!!\n"));
                    }
                    else if (effect_selector == 2)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.ABSORPTION, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYOU ARE GRANTED THE POWER OF ABSORPTION!!!\n"));
                    }
                    else
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.FIRE_RESISTANCE, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYOU ARE GRANTED THE POWER OF FIRE RESISTANCE!!!\n"));
                    }

                    break;

                case 15,14,13,12,11: //good buff tier
                    effect_selector = dice.nextInt(5)+1;
                    //speed/haste-night vision-conduit power-water breathing - jump
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.sendSystemMessage(Component.literal("\n! Good !"));

                    if(effect_selector == 1)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, 200,  0));
                        pPlayer.sendSystemMessage(Component.literal("\nYou feel faster!\n"));
                    }
                    else if(effect_selector == 2)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.NIGHT_VISION, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYou can see in the dark!\n"));
                    }
                    else if(effect_selector == 3)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.CONDUIT_POWER, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYour affinity with water increases!\n"));
                    }
                    else if(effect_selector == 4)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.JUMP, 200, 2));
                        pPlayer.sendSystemMessage(Component.literal("\nYour legs surge with strength!\n"));
                    }
                    else
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYou feel lighter!\n"));
                    }

                    break;

                case 10,9,8,7,6: //unfortunate tier
                    //slowness - blindness
                    effect_selector = dice.nextInt(3) +1;
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.ZOMBIE_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.sendSystemMessage(Component.literal("\n! Unfortunate !"));

                    if(effect_selector == 1)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, 200, 1));
                        pPlayer.sendSystemMessage(Component.literal("\nYour movement becomes sluggish...\n"));
                    }
                    else if(effect_selector == 2)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYour eyes fail you...\n"));
                    }
                    else
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nGravity has forsaken you...\n"));
                    }
                    break;

                case 5,4,3,2:
                    //poison - hunger - nausea - unlucky
                    effect_selector = dice.nextInt(4) + 1;
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.CAT_HURT, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.sendSystemMessage(Component.literal("\n! GREAT MISFORTUNE !"));

                    if(effect_selector == 1)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.POISON, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nA poisonous illness befalls you..\n"));
                    }
                    else if(effect_selector == 2)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYour strength leaves you...\n"));
                    }
                    else if(effect_selector == 3)
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0));
                        pPlayer.sendSystemMessage(Component.literal("\nYour head spins...\n"));
                    }
                    else
                    {
                        pPlayer.addEffect(new MobEffectInstance(MobEffects.UNLUCK, 1200, 2));
                        pPlayer.sendSystemMessage(Component.literal("\nYou are cursed with great misfortune...\n"));
                    }
                    break;

                case 1:
                    //critical failure
                    //adds wither effect - weakness - mining fatigue)20 ticks = 1 sec
                    pPlayer.sendSystemMessage(Component.literal("\n! FUMBLE !"));
                    pPlayer.sendSystemMessage(Component.literal("\nYOU ARE SIGNIFICANTLY WEAK...\n"));
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), ModSounds.FAIL_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 9));


                    break;

                default:
                    break;
            }
            pPlayer.sendSystemMessage(Component.literal("@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()@#$%^&*()\n"));

        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }
}
