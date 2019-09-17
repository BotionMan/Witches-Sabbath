package net.coolspookystuff.witchessabbath.block.entity;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventories;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.DefaultedList;
import net.minecraft.util.Tickable;

public class WitchesOvenBlockEntity extends BlockEntity implements WitchesOvenInventory  {

	public WitchesOvenBlockEntity() {
		super(WitchesSabbath.WITCHES_OVEN_BLOCK_ENTITY);
	}
	
    private final DefaultedList<ItemStack> items = DefaultedList.ofSize(2, ItemStack.EMPTY);
    
    public DefaultedList<ItemStack> getItems() {
        return items;
    }
    
    @Override
    public void fromTag(CompoundTag tag) {
        super.fromTag(tag);
        Inventories.fromTag(tag,items);
    }
 
    @Override
    public CompoundTag toTag(CompoundTag tag) {
        Inventories.toTag(tag,items);
        return super.toTag(tag);
    }

	@Override
	public int getInvSize() {
        return getItems().size();
    }

	@Override
	public boolean isInvEmpty() {
		for (int i = 0; i < getInvSize(); i++) {
     	      ItemStack stack = getInvStack(i);
     	      if (!stack.isEmpty()) {
     	    	  return false;
     	      }
		}
		return true;
	}

	@Override
	public ItemStack getInvStack(int slot) {
        return getItems().get(slot);
    }

	@Override
	public ItemStack takeInvStack(int slot, int count) {
		ItemStack result = Inventories.splitStack(getItems(), slot, count);
		if (!result.isEmpty()) {
			markDirty();
		}
		return result;
    }

	@Override
	public ItemStack removeInvStack(int slot) {
        return Inventories.removeStack(getItems(), slot);
    }

	@Override
	public void setInvStack(int slot, ItemStack stack) {
        getItems().set(slot, stack);
        if (stack.getCount() > getInvMaxStackAmount()) {
            stack.setCount(getInvMaxStackAmount());
        }
    }

	@Override
	public boolean canPlayerUseInv(PlayerEntity player) {
		return true;
	}

	@Override
	public void clear() {
        getItems().clear();
    }

}
