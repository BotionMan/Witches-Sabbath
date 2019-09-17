package net.coolspookystuff.witchessabbath.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;
import net.coolspookystuff.witchessabbath.block.entity.WitchesOvenBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;

public class WitchesOvenBlock extends Block implements BlockEntityProvider  {
	   public static final DirectionProperty FACING;
	   protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(2.0D, 3.0D, 2.0D, 14.0D, 15.0D, 14.0D);
	   
	   public WitchesOvenBlock() {
		   super(FabricBlockSettings.of(Material.PART).strength(0.5f, 0.5f).sounds(BlockSoundGroup.LANTERN).build());
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
	   public BlockEntity createBlockEntity(BlockView blockView) {
	      return new WitchesOvenBlockEntity();
	   }
	   public void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		      stateFactory$Builder_1.add(FACING);
	   }
	   @Override
	   public boolean activate(BlockState blockState, World world, BlockPos blockPos, PlayerEntity player, Hand hand, BlockHitResult blockHitResult) {
		   if (world.isClient) return true;
		   Inventory blockEntity = (Inventory) world.getBlockEntity(blockPos);
	 
		   
		   if (!player.getStackInHand(hand).isEmpty()) {
			   // Check what is the first open slot and put an item from the player's hand there
			   if (blockEntity.getInvStack(0).isEmpty()) {
				   // Put the stack the player is holding into the inventory
				   blockEntity.setInvStack(0, player.getStackInHand(hand).copy());
				   // Remove the stack from the player's hand
				   player.getStackInHand(hand).setCount(0);
			   } else {
				   if (!blockEntity.getInvStack(1).isEmpty()) {
					   // Give the player the stack in the inventory
					   player.inventory.offerOrDrop(world, blockEntity.getInvStack(1));
					   // Remove the stack from the inventory
					   blockEntity.removeInvStack(1);
				   } else if (!blockEntity.getInvStack(0).isEmpty()) {
					   player.inventory.offerOrDrop(world, blockEntity.getInvStack(0));
					   blockEntity.removeInvStack(0);
				   } else if (blockEntity.getInvStack(1).isEmpty()) {
					   blockEntity.setInvStack(1, player.getStackInHand(hand).copy());
				   player.getStackInHand(hand).setCount(0);

				   }
			   }
		   } 
	   return true;
	}
	   static {
		   FACING = HorizontalFacingBlock.FACING;	
	   }
	   
	 
	   
}
