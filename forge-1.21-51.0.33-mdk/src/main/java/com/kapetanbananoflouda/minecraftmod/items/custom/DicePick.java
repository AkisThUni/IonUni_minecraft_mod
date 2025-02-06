package com.kapetanbananoflouda.minecraftmod.items.custom;



import net.minecraft.core.Holder;
import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.effect.MobEffect;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.Mob;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.level.Level;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;
import org.jetbrains.annotations.NotNull;


import java.util.Random;


public class DicePick extends PickaxeItem {
    public DicePick(Tier pTier, Properties pProperties) {
        super(pTier, pProperties); //constructor apo pickaxe
    }

    //thank you gpt for solving the linear algebra math for me (perasa me 5)
    private void mine3x3(Level world, BlockPos pos, Player player) {

        // Break the central block first (fixes the issue)
        if (world.getBlockState(pos).getDestroySpeed(world, pos) >= 0) {
            world.destroyBlock(pos, true, player);
        }//ntax tora kati kanoume


        for (int x = -1; x <= 1; x++) {
            for (int y = -1; y <= 1; y++) {
                for (int z = -1; z <= 1; z++) {
                    BlockPos newPos = pos.offset(x, y, z);
                    if (!newPos.equals(pos)) { // Don't break the original block twice
                        world.destroyBlock(newPos, true, player);
                    }
                }
            }
        }
    }

    //bgalame kai nxn
    private void mineNxN(Level world, BlockPos pos, Player player, int size) {
        if (world.isClientSide) return; // Only run on the server

        int radius = (size - 1) / 2; // Ensures symmetry around the center

        for (int x = -radius; x <= radius; x++) {
            for (int y = -radius; y <= radius; y++) {
                for (int z = -radius; z <= radius; z++) {
                    BlockPos newPos = pos.offset(x, y, z);

                    // Prevent breaking unbreakable blocks like bedrock
                    if (world.getBlockState(newPos).getDestroySpeed(world, newPos) >= 0) {
                        world.destroyBlock(newPos, true, player);
                    }
                }
            }
        }
    }

    public static BlockPos getBlockLookingAt(Player player, double maxDistance)
    {
        // pio bloc kkoitame? ayt otha kanoume mine gyro gyro
        HitResult result = player.pick(maxDistance, 0, false);

        if (result.getType() == HitResult.Type.BLOCK) {
            return ((BlockHitResult) result).getBlockPos(); // Return the block position
        }

        return null; // No block was hit
    }




    @Override
    public InteractionResultHolder<ItemStack> use(Level pLevel, Player pPlayer, InteractionHand pUsedHand)
    {

        if (!pLevel.isClientSide) { // Only run on server
            if(pPlayer.hasEffect(MobEffects.LUCK))//gotta add super miner effect
            {

                //get the amp
                MobEffectInstance inst = pPlayer.getEffect(MobEffects.LUCK);
                int n = inst.getAmplifier() +1; //for radius

                BlockPos initPos = getBlockLookingAt(pPlayer,5);
                mineNxN(pLevel,initPos,pPlayer,n);
                return super.use(pLevel, pPlayer, pUsedHand);
            }

             // 30 seconds (600 ticks)
            pPlayer.getCooldowns().addCooldown(this,100); //add cooldown for roulette
            Random dice = new Random();
            int roll = dice.nextInt(20)+1;




            pPlayer.sendSystemMessage(Component.literal("You used the Dice pick\n" ));
            pPlayer.sendSystemMessage(Component.literal("You rolled : "+roll ));


            switch (roll)
            {
                //300 ticks is  15 sec
                case 1:
                    pPlayer.sendSystemMessage(Component.literal("\nFAIL : You become lazy..." ));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SLOWDOWN,300,9));
                    break;
                case 2,3,4,5:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.GLOWING,300,9));
                    pPlayer.sendSystemMessage(Component.literal("\nOK : You are brilliant..." ));
                    break;
                case 6,7,8,9,10:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.BLINDNESS, 100, 2));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,300,1));
                    pPlayer.sendSystemMessage(Component.literal("\nGood? : Who turned off the lights?" ));
                    break;
                case 11,12,13,14,15:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,300,roll-9));//3,4,5,6,7 for buffs
                    pPlayer.sendSystemMessage(Component.literal("\nGREAT : Your pickaxe is slightly buffed!!" ));
                    break;
                case 16,17,18,19:

                    pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,roll-13));//3,4,5,6 for amp
                    pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\nGOOD : Your pickaxe is greatly buffed!" ));
                    break;

                case 20:
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.DIG_SPEED,400,roll));
                    pPlayer.addEffect(new MobEffectInstance(MobEffects.LUCK,300,9));
                    pPlayer.getCooldowns().removeCooldown(this); // Remove cooldown from this item for mining
                    pPlayer.sendSystemMessage(Component.literal("\nEXCELLENT : Jackpot!!!" ));

                    break;
                default:
                    break;

            }




            

        }
        return super.use(pLevel, pPlayer, pUsedHand);

    }

}




