package com.kapetanbananoflouda.minecraftmod.datagen;

import com.kapetanbananoflouda.minecraftmod.block.ModBlocks;
import com.kapetanbananoflouda.minecraftmod.items.ModItems;
import net.minecraft.core.HolderLookup;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.*;
import net.minecraftforge.common.crafting.conditions.IConditionBuilder;

import java.util.concurrent.CompletableFuture;

public class ModRecipeProvider extends RecipeProvider implements IConditionBuilder {
    public ModRecipeProvider(PackOutput pOutput, CompletableFuture<HolderLookup.Provider> pRegistries) {
        super(pOutput, pRegistries);
    }

    @Override
    protected void buildRecipes(RecipeOutput pRecipeOutput) {
        // This is how to make a shaped Recipe
        ShapedRecipeBuilder.shaped(RecipeCategory.MISC, ModBlocks.CAT_BLOCK.get())
                .pattern("AA")
                .pattern("AA")
                .define('A', ModItems.TOAST.get())
                .unlockedBy(getHasName(ModItems.TOAST.get()), has(ModItems.TOAST.get()))
                // How the recipe" "Unlocks, Not useful rn
                .save(pRecipeOutput);


        // This is how to make a shapeless recipe
        ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, ModItems.TOAST.get(), 9)
                .requires(ModBlocks.CAT_BLOCK.get())
                .unlockedBy(getHasName(ModBlocks.CAT_BLOCK.get()), has(ModBlocks.CAT_BLOCK.get()))
                .save(pRecipeOutput);

        // Insane thing here.jpeg




    }
}
