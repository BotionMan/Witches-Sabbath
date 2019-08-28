package net.coolspookystuff.witchessabbath.block.entity;
import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.world.BlockView;

public class WitchesOvenBlockEntity extends BlockEntity {

	
	public WitchesOvenBlockEntity() {
	      super(WitchesSabbath.WITCHES_OVEN_BLOCK_ENTITY);
	}

	public BlockEntity createBlockEntity(BlockView blockView) {
	   return new WitchesOvenBlockEntity();
	}	
}
