package net.coolspookystuff.witchessabbath.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Material;
import net.minecraft.block.SaplingBlock;
import net.minecraft.block.sapling.SaplingGenerator;
import net.minecraft.sound.BlockSoundGroup;

public class MagicSaplingBlock extends SaplingBlock {

	public MagicSaplingBlock(SaplingGenerator generator) {
		super(generator, FabricBlockSettings.of(Material.PLANT).collidable(false).ticksRandomly().hardness(0).sounds(BlockSoundGroup.GRASS).build());
	}
}
