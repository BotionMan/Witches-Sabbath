package net.coolspookystuff.witchessabbath.block.entity;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.block.entity.LockableContainerBlockEntity;
import net.minecraft.container.PropertyDelegate;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.recipe.AbstractCookingRecipe;
import net.minecraft.recipe.Recipe;
import net.minecraft.recipe.RecipeFinder;
import net.minecraft.recipe.RecipeInputProvider;
import net.minecraft.recipe.RecipeType;
import net.minecraft.recipe.RecipeUnlocker;
import net.minecraft.tag.ItemTags;
import net.minecraft.tag.Tag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Identifier;
import net.minecraft.util.Tickable;
import net.minecraft.util.math.Direction;
import net.minecraft.util.math.MathHelper;

public abstract class WitchesOvenBlockEntity extends LockableContainerBlockEntity implements SidedInventory, RecipeUnlocker, RecipeInputProvider, Tickable {
	   private static final int[] TOP_SLOTS = new int[]{0};
	   private static final int[] BOTTOM_SLOTS = new int[]{2, 1};
	   private static final int[] SIDE_SLOTS = new int[]{1};
	   protected DefaultedList<ItemStack> inventory;
	   private int burnTime;
	   private int fuelTime;
	   private int cookTime;
	   private int cookTimeTotal;
	   protected final PropertyDelegate propertyDelegate;
	   private final Map<Identifier, Integer> recipesUsed;
	   protected final RecipeType<? extends AbstractCookingRecipe> recipeType;

	   protected WitchesOvenBlockEntity(BlockEntityType<?> blockEntityType_1, RecipeType<? extends AbstractCookingRecipe> recipeType_1) {
	      super(blockEntityType_1);
	      this.inventory = DefaultedList.ofSize(3, ItemStack.EMPTY);
	      this.propertyDelegate = new PropertyDelegate() {
	         public int get(int int_1) {
	            switch(int_1) {
	            case 0:
	               return WitchesOvenBlockEntity.this.burnTime;
	            case 1:
	               return WitchesOvenBlockEntity.this.fuelTime;
	            case 2:
	               return WitchesOvenBlockEntity.this.cookTime;
	            case 3:
	               return WitchesOvenBlockEntity.this.cookTimeTotal;
	            default:
	               return 0;
	            }
	         }

	         public void set(int int_1, int int_2) {
	            switch(int_1) {
	            case 0:
	            	WitchesOvenBlockEntity.this.burnTime = int_2;
	               break;
	            case 1:
	            	WitchesOvenBlockEntity.this.fuelTime = int_2;
	               break;
	            case 2:
	            	WitchesOvenBlockEntity.this.cookTime = int_2;
	               break;
	            case 3:
	            	WitchesOvenBlockEntity.this.cookTimeTotal = int_2;
	            }

	         }

	         public int size() {
	            return 4;
	         }
	      };
	      this.recipesUsed = Maps.newHashMap();
	      this.recipeType = recipeType_1;
	   }
	}
