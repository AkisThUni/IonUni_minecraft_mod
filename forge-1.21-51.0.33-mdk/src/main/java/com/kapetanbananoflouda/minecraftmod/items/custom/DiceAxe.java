package com.kapetanbananoflouda.minecraftmod.items.custom;

import com.kapetanbananoflouda.minecraftmod.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;
import net.minecraft.tags.BlockTags;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.EquipmentSlot;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.AxeItem;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;

import java.util.Random;


public class DiceAxe  extends AxeItem implements toolFunctions
{
    public DiceAxe(Tier pTier, Properties pProperties) {
        super(pTier, pProperties);
    }


    // Insert here crico.exe -done
    @Override
    public void mineNxN(Level world, BlockPos pos, Player player, int size)
    {
        if (world.isClientSide) return; // Only run on the server

        int radius = (size + 1) ; // Ensures symmetry around the center

        for (int x = -radius; x <= radius; x++)
        {
            for (int y = -radius; y <= radius; y++)
            {
                for (int z = -radius; z <= radius; z++)
                {
                    BlockPos newPos = pos.offset(x, y, z);
                    BlockState state = world.getBlockState(newPos);

                    //only break if it is a log block
                    if(state.is(BlockTags.LOGS))
                    {
                        world.destroyBlock(newPos,true,player);
                    }
                }
            }
        }

    }



    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand) {
        // insert here
        if (!pLevel.isClientSide) {
            //run only on server , to avoid double use (learned from experience)
            if(pPlayer.hasEffect(MobEffects.LUCK)) //same as pickaxe scales with luck amplifier
            {

                //get the amp
                MobEffectInstance inst = pPlayer.getEffect(MobEffects.LUCK);
                int n = inst.getAmplifier() ; //for radius (the nxn functio nreduces this by 1)

                BlockPos initPos = toolFunctions.getBlockLookingAt(pPlayer,5);
                mineNxN(pLevel,initPos,pPlayer,n);
                return super.use(pLevel, pPlayer, pUsedHand);
            }

            pPlayer.getCooldowns().addCooldown(this, 100); //add cooldown for roulette
            Random dice = new Random();
            int roll = dice.nextInt(20) + 1;
            int effect_dur = 600; //30 sec
            //local variables cause for every item there will be an extra set at all times(definitely not casue we are bored to change it now)
            //damage on roll
            ItemStack stack = pPlayer.getItemInHand(pUsedHand);
            // Reduce durability by 1
            stack.hurtAndBreak(5, pPlayer, (pUsedHand == InteractionHand.MAIN_HAND
                    ? EquipmentSlot.MAINHAND
                    : EquipmentSlot.OFFHAND));


            pPlayer.sendSystemMessage(Component.literal("§k======================================\n"));
            pPlayer.sendSystemMessage(Component.literal("You used the Dice Axe\n"));
            pPlayer.sendSystemMessage(Component.literal("You rolled : " + roll));


            switch (roll) {
                //300 ticks is  15 sec
                case 1:
                    //big damage
                    stack.hurtAndBreak(300, pPlayer, (pUsedHand == InteractionHand.MAIN_HAND
                            ? EquipmentSlot.MAINHAND
                            : EquipmentSlot.OFFHAND));
                    //critical failure
                    pPlayer.sendSystemMessage(Component.literal("\nYou have grown §clethargic..§ftake a §erest§f."));
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.FAIL_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, effect_dur, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, effect_dur, 3));
                    break;
                case 2, 3, 4, 5:
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.WITHER_SPAWN, SoundSource.PLAYERS, 12.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, effect_dur, 4));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SLOWDOWN, effect_dur, 4));
                    pPlayer.sendSystemMessage(Component.literal("\n§4§lThe §6§lLorax §4§lis displeased."));
                    break;
                case 6, 7, 8, 9, 10:
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.ANVIL_LAND, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.LARGE_AMETHYST_BUD_BREAK, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 2));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300, 1));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HARM, 10, 0));
                    pPlayer.sendSystemMessage(Component.literal("\nYou accidentally §ccut §fyourself, Ouch!"));
                    break;
                case 11, 12, 13, 14, 15:

                    // ===================== CHECK THIS ONE CRICO - to chekara
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.PLAYER_LEVELUP, SoundSource.PLAYERS, 5.0F, 1.0F);
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_LARGE_BLAST, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, effect_dur, roll-10));//2,3,4,5,6
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effect_dur, 1));
                    pPlayer.sendSystemMessage(Component.literal("\n§eYou feel a surge of §lInspiration!"));
                    break;
                case 16, 17, 18, 19:
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.AMETHYST_BLOCK_CHIME, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pLevel.playSound(null,pPlayer.getX(),pPlayer.getY(),pPlayer.getZ(), SoundEvents.FIREWORK_ROCKET_LAUNCH, SoundSource.PLAYERS, 1.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,effect_dur,roll -13));//4,5,6,7 LUCK
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,effect_dur,roll - 13));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effect_dur, 2));

                    pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\nYour axe §eunleashes §fa portion of its true power..."));
                    break;

                case 20:
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.SUCCESS_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);

                    //should make a custom function to break all wooden logs in a radius
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,effect_dur,9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,effect_dur,9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DAMAGE_BOOST,effect_dur,9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED,effect_dur,2));
                    //pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\n §4§o§lJACKPOT - The §6NUMBERS §egrace §e§oyou with the power of §bRight Click!"));

                    //To repair the item, reduce the damage value on the ItemStack.
                    int amount = 300;
                    int newDamage = Math.max(stack.getDamageValue() - amount, 0); // if amount - damge < 0 ? damage =0
                    stack.setDamageValue(newDamage);//repair by reducing damage

                    break;
                default:
                    break;

            }

            pPlayer.sendSystemMessage(Component.literal("\n"));
            return super.use(pLevel, pPlayer, pUsedHand);


        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }


}






