package net.coolspookystuff.witchessabbath.block;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.CropBlock;
import net.minecraft.block.Material;
import net.minecraft.entity.EntityContext;
import net.minecraft.item.ItemConvertible;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.shape.VoxelShape;
import net.minecraft.world.BlockView;

public class MandrakeRootsBlock extends CropBlock {
	   private static final VoxelShape SHAPE = Block.createCuboidShape(0.0D, 0.0D, 0.0D, 16.0D, 9.0D, 16.0D);
	   
	   public MandrakeRootsBlock() {
		   super(FabricBlockSettings.of(Material.REPLACEABLE_PLANT).sounds(BlockSoundGroup.CROP).build());
		   this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(this.getAgeProperty(), 0));
	   }
	   @Environment(EnvType.CLIENT)
	   protected ItemConvertible getSeedsItem() {
		      return WitchesSabbath.MANDRAKE_SEEDS;
	   }
	   protected ItemConvertible getCropItem() {
		   return WitchesSabbath.MANDRAKE_ROOT;
	   }
	   protected boolean canPlantOnTop(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1) {
		   return blockState_1.getBlock() == Blocks.FARMLAND;
	   }


	   public VoxelShape getOutlineShape(BlockState blockState_1, BlockView blockView_1, BlockPos blockPos_1, EntityContext entityContext_1) {
	      return SHAPE;
	   }
}
