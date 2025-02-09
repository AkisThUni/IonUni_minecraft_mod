package com.kapetanbananoflouda.minecraftmod.items.custom;

import com.kapetanbananoflouda.minecraftmod.ModSounds;
import net.minecraft.core.BlockPos;
import net.minecraft.network.chat.Component;
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

        int radius = (size + 1) / 2; // Ensures symmetry around the center

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
            int effect_dur = 600;
            //local variables cause for every item there will be an extra set at all times(definitely not casue we are bored to change it now)
            //damage on roll
            ItemStack stack = pPlayer.getItemInHand(pUsedHand);
            // Reduce durability by 1
            stack.hurtAndBreak(5, pPlayer, (pUsedHand == InteractionHand.MAIN_HAND
                    ? EquipmentSlot.MAINHAND
                    : EquipmentSlot.OFFHAND));




            pPlayer.sendSystemMessage(Component.literal("You used the Dice Axe\n"));
            pPlayer.sendSystemMessage(Component.literal("You rolled : " + roll));


            switch (roll) {
                //300 ticks is  15 sec
                case 1:
                    pPlayer.sendSystemMessage(Component.literal("\nYou have grown §clethargic..§ftake a §erest§f."));
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.FAIL_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.WITHER, 200, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, effect_dur, 9));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HUNGER, effect_dur, 3));
                    break;
                case 2, 3, 4, 5:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DARKNESS, effect_dur, 4));
                    pPlayer.sendSystemMessage(Component.literal("\nYour mind is shrouded, you should rest."));
                    break;
                case 6, 7, 8, 9, 10:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 100, 2));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN, 300, 1));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.HARM, 20, 0));
                    pPlayer.sendSystemMessage(Component.literal("\nYou accidentally cut yourself, Ouch!"));
                    break;
                case 11, 12, 13, 14, 15:

                    // ===================== CHECK THIS ONE CRICO - to chekara
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED, effect_dur, roll-9));//3,4,5,6,7 for buffs.
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.MOVEMENT_SPEED, effect_dur, 2));
                    pPlayer.sendSystemMessage(Component.literal("\n§eYou feel a surge of Inspiration!"));
                    break;
                case 16, 17, 18, 19:
                    //pos to break
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,effect_dur,roll -13));

                    // ============================ CHECK THIS ONE OUT
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,roll-13));//3,4,5,6 for amp
                    pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\nYour axe unleashes a portion of its true power..."));
                    break;

                case 20:
                    //should make a custom function to break all wooden logs in a radius
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,400,roll));
                    //pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,9));
                    pLevel.playSound(null, pPlayer.getX(), pPlayer.getY(), pPlayer.getZ(), ModSounds.SUCCESS_ROLL.get(), SoundSource.PLAYERS, 10.0F, 1.0F);
                    //pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\n PLACEHOLDER!"));

                    //To repair the item, reduce the damage value on the ItemStack.
                    int amount = 300;
                    int newDamage = Math.max(stack.getDamageValue() - amount, 0); // if amount - damge < 0 ? damage =0
                    stack.setDamageValue(newDamage);//repair by reducing damage

                    break;
                default:
                    break;

            }


            return super.use(pLevel, pPlayer, pUsedHand);


        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }


}






