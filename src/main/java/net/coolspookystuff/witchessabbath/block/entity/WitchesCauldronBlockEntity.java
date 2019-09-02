package net.coolspookystuff.witchessabbath.block.entity;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.nbt.CompoundTag;

public class WitchesCauldronBlockEntity extends BlockEntity	 {
	public static int testint = 3;
	public WitchesCauldronBlockEntity() {
		super(WitchesSabbath.WITCHES_CAULDRON_BLOCK_ENTITY);
	}
	public CompoundTag toTag(CompoundTag tag) {
		super.toTag(tag);
		
		tag.putInt("testint", testint);
		
		return tag;
	}
	public void fromTag(CompoundTag tag) {
		   super.fromTag(tag);
		   testint = tag.getInt("number");
		}
}
