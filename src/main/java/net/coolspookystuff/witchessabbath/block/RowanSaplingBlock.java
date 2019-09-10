package net.coolspookystuff.witchessabbath.block;

import java.util.Random;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.tag.BlockTags;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.IWorld;


public class RowanSaplingBlock extends SaplingBlock {
	public RowanSaplingBlock(SaplingGenerator generator) {
		super(generator, null);
	}

	@Override
	public boolean canPlantOnTop(BlockState blockState, BlockView blockView, BlockPos pos) {
		if (BlockTags.DIRT_LIKE.contains(blockState.getBlock())) {
			return true;
		}
		return super.canPlantOnTop(blockState, blockView, pos);
	}
}