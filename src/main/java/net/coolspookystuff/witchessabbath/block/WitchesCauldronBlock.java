package net.coolspookystuff.witchessabbath.block;

import net.coolspookystuff.witchessabbath.block.entity.WitchesOvenBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class WitchesCauldronBlock extends Block {
	   public static final DirectionProperty FACING;  
	   protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 12.0D, 14.0D);

	   public WitchesCauldronBlock() {
		   super(FabricBlockSettings.of(Material.METAL).strength(1.0f, 1.0f).sounds(BlockSoundGroup.METAL).build());		   
	   }	        
	   public WitchesCauldronBlock(Block.Settings block$Settings_1) {
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
	   @Override
	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
		      return BOUNDING_SHAPE;
	   }
	   public void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		      stateFactory$Builder_1.add(FACING);
	   }
	   static {
		      FACING = HorizontalFacingBlock.FACING;

	}
}
