package com.kapetanbananoflouda.minecraftmod.items.custom;


import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.level.Level;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.HitResult;

//bazo edo ta functions barethika to copy paste
public interface toolFunctions {

    //bgalame kai nxn
    //ayto einai gia to pickaxe kyrios

    abstract void mineNxN(Level world, BlockPos pos, Player player, int size) ;


    //thank you gpt for solving the linear algebra math for me (perasa me 5)



    //ayto gia ola
    static BlockPos getBlockLookingAt(Player player, double maxDistance)
    {
        // pio bloc kkoitame? ayt otha kanoume mine gyro gyro
        HitResult result = player.pick(maxDistance, 0, false);

        if (result.getType() == HitResult.Type.BLOCK) {
            return ((BlockHitResult) result).getBlockPos(); // Return the block position
        }

        return null; // No block was hit
    }
}
