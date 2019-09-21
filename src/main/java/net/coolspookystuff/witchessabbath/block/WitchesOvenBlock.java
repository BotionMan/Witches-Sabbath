package net.coolspookystuff.witchessabbath.block;

import net.minecraft.block.Block;
import net.minecraft.block.BlockEntityProvider;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.AbstractFurnaceBlockEntity;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.FurnaceBlockEntity;
import net.minecraft.container.NameableContainerProvider;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.stat.Stats;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.util.Hand;
import net.minecraft.util.ItemScatterer;
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
	   public BlockEntity createBlockEntity(BlockView blockView_1) {
		   return new FurnaceBlockEntity();
	   }

	   protected void openContainer(World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1) {
		   BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
		   if (blockEntity_1 instanceof WitchesOvenBlockEntity) {
			   playerEntity_1.openContainer((NameableContainerProvider)blockEntity_1);
			   playerEntity_1.incrementStat(Stats.INTERACT_WITH_FURNACE);
		   }

	   }
	   public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
		   if (!world_1.isClient) {
			   this.openContainer(world_1, blockPos_1, playerEntity_1);
		   }

		   return true;
	   }
	   public void onPlaced(World world_1, BlockPos blockPos_1, BlockState blockState_1, LivingEntity livingEntity_1, ItemStack itemStack_1) {
		      if (itemStack_1.hasCustomName()) {
		         BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
		         if (blockEntity_1 instanceof WitchesOvenBlockEntity) {
		            ((WitchesOvenBlockEntity)blockEntity_1).setCustomName(itemStack_1.getName());
		         }
		      }

		   }

		   public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) {
		      if (blockState_1.getBlock() != blockState_2.getBlock()) {
		         BlockEntity blockEntity_1 = world_1.getBlockEntity(blockPos_1);
		         if (blockEntity_1 instanceof AbstractFurnaceBlockEntity) {
		            ItemScatterer.spawn(world_1, (BlockPos)blockPos_1, (Inventory)((AbstractFurnaceBlockEntity)blockEntity_1));
		            world_1.updateHorizontalAdjacent(blockPos_1, this);
		         }

		         super.onBlockRemoved(blockState_1, world_1, blockPos_1, blockState_2, boolean_1);
		      }
		   }
	   
	   public void appendProperties(StateFactory.Builder<Block, BlockState> stateFactory$Builder_1) {
		      stateFactory$Builder_1.add(FACING);
	   }
	   static {
		   FACING = HorizontalFacingBlock.FACING;	
	   }
	   
	 
	   
}
