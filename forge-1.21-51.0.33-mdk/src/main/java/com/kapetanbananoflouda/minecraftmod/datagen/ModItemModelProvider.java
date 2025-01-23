package com.kapetanbananoflouda.minecraftmod.datagen;

import com.kapetanbananoflouda.minecraftmod.MinecraftMod;
import com.kapetanbananoflouda.minecraftmod.items.ModItems;
import net.minecraft.data.PackOutput;
import net.minecraftforge.client.model.generators.ItemModelProvider;
import net.minecraftforge.common.data.ExistingFileHelper;

public class ModItemModelProvider extends ItemModelProvider {
    public ModItemModelProvider(PackOutput output, ExistingFileHelper existingFileHelper) {
        super(output, MinecraftMod.MOD_ID, existingFileHelper);
    }


    // This is where we put our items to get their .json files
    @Override
    protected void registerModels() {
        basicItem(ModItems.TOAST.get());


        basicItem(ModItems.CLOVER.get());
        basicItem(ModItems.CRISP_CLOVER.get());

    }
}
