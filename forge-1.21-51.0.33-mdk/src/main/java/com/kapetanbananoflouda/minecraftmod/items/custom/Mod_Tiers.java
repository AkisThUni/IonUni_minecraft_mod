package com.kapetanbananoflouda.minecraftmod.items.custom;
import net.minecraft.world.item.Items;

import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Tier;

import net.minecraft.world.item.Tiers;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.block.Block;

import com.kapetanbananoflouda.minecraftmod.items.ModItems;
public class Mod_Tiers {
    public static final Tier DICE_TIER = new Tier() {
        @Override
        public float getSpeed() {
            return 10.0F; // Mining speed (faster than diamond)
        }

        @Override
        public int getUses() {
            return 2000; // Custom durability
        }

        @Override
        public float getAttackDamageBonus() {
            return 5.0F; // Extra attack damage (base damage for tools)
        }

        @Override
        public TagKey<Block> getIncorrectBlocksForDrops() {
            return Tiers.DIAMOND.getIncorrectBlocksForDrops();
        }



        @Override
        public int getEnchantmentValue() {
            return 15; // Enchantability
        }

        @Override
        public Ingredient getRepairIngredient() {
            return Ingredient.of(Items.EMERALD); // Repair with depon
        }
    };

}
