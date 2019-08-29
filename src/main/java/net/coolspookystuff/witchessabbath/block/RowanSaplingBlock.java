package net.coolspookystuff.witchessabbath.block;

import java.util.Random;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.IWorld;

public class RowanSaplingBlock extends SaplingBlock {
	   private final SaplingGenerator rowangenerator;
	   
	   public RowanSaplingBlock(SaplingGenerator RowanSaplingGenerator) {
		   	super(RowanSaplingGenerator, FabricBlockSettings.of(Material.METAL).strength(1.0f, 1.0f).sounds(BlockSoundGroup.METAL).build());
	 	   	this.rowangenerator = RowanSaplingGenerator;
	 	   	this.setDefaultState((BlockState)((BlockState)this.stateFactory.getDefaultState()).with(STAGE, 0));
	   }
	   public void generate(IWorld iWorld_1, BlockPos blockPos_1, BlockState blockState_1, Random random_1) {
		      if ((Integer)blockState_1.get(STAGE) == 0) {
		         iWorld_1.setBlockState(blockPos_1, (BlockState)blockState_1.cycle(STAGE), 4);
		      } 
		      else {
		    	  this.rowangenerator.generate(iWorld_1, blockPos_1, blockState_1, random_1);
		      }
	   }
}	
