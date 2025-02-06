package com.kapetanbananoflouda.minecraftmod.items.custom;


import net.minecraft.network.chat.Component;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
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


    public static BlockPos getBlockLookingAt(Player player, Level world, double maxDistance) {
        // Ray trace to get the block the player is looking at
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
            int duration = 600; // 30 seconds (600 ticks)

            // Apply the effect

            pPlayer.sendSystemMessage(Component.literal("You used the Dice pick" ));
            BlockPos initPos = getBlockLookingAt(pPlayer,pLevel,3);
            mine3x3(pLevel,initPos,pPlayer);


        }
        return super.use(pLevel, pPlayer, pUsedHand);
    }

}




