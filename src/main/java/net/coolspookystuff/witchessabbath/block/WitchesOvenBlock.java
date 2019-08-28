package net.coolspookystuff.witchessabbath.block;

import net.coolspookystuff.witchessabbath.block.entity.WitchesOvenBlockEntity;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.world.BlockView;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class WitchesOvenBlock extends Block {
	   public static final DirectionProperty FACING;
	   
	    public WitchesOvenBlock() {
	        super(FabricBlockSettings.of(Material.METAL).strength(1.0f, 1.0f).sounds(BlockSoundGroup.METAL).build());
	    }
	        
	   public WitchesOvenBlock(Block.Settings block$Settings_1) {
		      super(block$Settings_1);

	   }
	   public BlockEntity createBlockEntity(BlockView blockView_1) {
		      return new WitchesOvenBlockEntity();
	   }
	   public BlockRenderLayer getRenderLayer() {
		   return BlockRenderLayer.CUTOUT;
	   }
	@Override
	   public BlockState getPlacementState(ItemPlacementContext itemPlacementContext_1) {
		      return (BlockState)this.getDefaultState().with(FACING, itemPlacementContext_1.getPlayerFacing().getOpposite());
		   }
	   public void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		      stateFactory$Builder_1.add(FACING);
	   }
	   static {
		      FACING = HorizontalFacingBlock.FACING;		
	}
}
