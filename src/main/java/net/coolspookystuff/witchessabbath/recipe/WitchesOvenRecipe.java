package net.coolspookystuff.witchessabbath.recipe;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.Blocks;
import net.minecraft.item.ItemStack;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeSerializer;
import net.minecraft.recipe.RecipeType;
import net.minecraft.util.Identifier;

public class WitchesOvenRecipe extends AbstractCookingRecipe {
	   public WitchesOvenRecipe(Identifier identifier_1, String string_1, Ingredient ingredient_1, ItemStack itemStack_1, float float_1, int int_1) {
	      super(RecipeType.SMELTING, identifier_1, string_1, ingredient_1, itemStack_1, float_1, int_1);
	   }

	   @Environment(EnvType.CLIENT)
	   public ItemStack getRecipeKindIcon() {
	      return new ItemStack(Blocks.FURNACE);
	   }

	   public RecipeSerializer<?> getSerializer() {
	      return RecipeSerializer.SMELTING;
	   }
	}
