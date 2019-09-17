package net.coolspookystuff.witchessabbath.block;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.coolspookystuff.witchessabbath.block.entity.WitchesCauldronBlockEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockPlacementEnvironment;
import net.minecraft.block.BlockRenderLayer;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.HorizontalFacingBlock;
import net.minecraft.block.Material;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.pattern.BlockPattern;
import net.minecraft.block.pattern.BlockPatternBuilder;
import net.minecraft.block.pattern.CachedBlockPosition;
import net.minecraft.entity.EntityContext;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemPlacementContext;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.predicate.block.BlockStatePredicate;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.state.StateFactory;
import net.minecraft.state.property.DirectionProperty;
import net.minecraft.state.property.IntProperty;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Hand;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class WitchesCauldronBlock extends Block  {
	   public static final DirectionProperty FACING;  
	   protected static final VoxelShape BOUNDING_SHAPE = Block.createCuboidShape(2.0D, 2.0D, 2.0D, 14.0D, 12.0D, 14.0D);
	   public static final IntProperty LEVEL;
	   public  final int testint2 = WitchesCauldronBlockEntity.testint;
	   private static BlockPattern cauldronBubblePattern;

	   public WitchesCauldronBlock() {
		   super(FabricBlockSettings.of(Material.PART).strength(0.5f, 0.5f).sounds(BlockSoundGroup.LANTERN).build());
		   this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(LEVEL, 0));
	   }	        
	   public WitchesCauldronBlock(Block.Settings block$Settings_1) {
		      super(block$Settings_1);
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
		      stateFactory$Builder_1.add(LEVEL);
	   }
	   @Override
	   public boolean activate(BlockState blockState_1, World world_1, BlockPos blockPos_1, PlayerEntity playerEntity_1, Hand hand_1, BlockHitResult blockHitResult_1) {
		      ItemStack itemStack_1 = playerEntity_1.getStackInHand(hand_1);
		      if (itemStack_1.isEmpty()) {
		    	  return true;
		      } else {
		    	  int fullness = (Integer)blockState_1.get(LEVEL);
		    	  Item handitem = itemStack_1.getItem();
		    	  if (handitem == Items.WATER_BUCKET) {
		    		  if (fullness < 1 && !world_1.isClient) {
		    			  if (!playerEntity_1.abilities.creativeMode) {
		    				  playerEntity_1.setStackInHand(hand_1, new ItemStack(Items.BUCKET));
		    			  }	
		    			  this.setLevel(world_1, blockPos_1, blockState_1, 3);
		    			  world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.ITEM_BUCKET_EMPTY, SoundCategory.BLOCKS, 1.0F, 1.0F);
		    		  }

		    		  return true;
		    	  } else if (handitem == Items.BUCKET) {
		    		  if (fullness == 3 && !world_1.isClient) {
		    			  if (!playerEntity_1.abilities.creativeMode) {
		    				  itemStack_1.decrement(1);
		    				  if (itemStack_1.isEmpty()) {
		    					  playerEntity_1.setStackInHand(hand_1, new ItemStack(Items.WATER_BUCKET));
		    				  } else if (!playerEntity_1.inventory.insertStack(new ItemStack(Items.WATER_BUCKET))) {
		    					  playerEntity_1.dropItem(new ItemStack(Items.WATER_BUCKET), false);
		    				  }
		    			  }
		    			  this.setLevel(world_1, blockPos_1, blockState_1, 0);
		    			  world_1.playSound((PlayerEntity)null, blockPos_1, SoundEvents.ITEM_BUCKET_FILL, SoundCategory.BLOCKS, 1.0F, 1.0F);
		    		  }
		            
		    		  return true;
		    	  } else {
		    		  return false;
		    	  }
		      }
	   }
	   public void setLevel(World world_1, BlockPos blockPos_1, BlockState blockState_1, int int_1) {
		      world_1.setBlockState(blockPos_1, (BlockState)blockState_1.with(LEVEL, MathHelper.clamp(int_1, 0, 3)), 2);
		      world_1.updateHorizontalAdjacent(blockPos_1, this);
		   }
	   @Override
	   public boolean canPlaceAtSide(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, BlockPlacementEnvironment blockPlacementEnvironment_1) {
		   return false;
	   }
	   public BlockEntity createBlockEntity(BlockView blockView) {
	      return new WitchesCauldronBlockEntity();
	   }
		
	   public BlockPattern cauldronBubblePattern() {
		   return BlockPatternBuilder.start()
				   .aisle("c", "f")
				   .where('c', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(WitchesSabbath.WITCHES_CAULDRON)))
				   .where('c', CachedBlockPosition.matchesBlockState(BlockStatePredicate.forBlock(Blocks.FIRE)))
				   .build();
		   
	   }
	   static {
		   LEVEL = Properties.LEVEL_3;
		   FACING = HorizontalFacingBlock.FACING;

	}
	
}
