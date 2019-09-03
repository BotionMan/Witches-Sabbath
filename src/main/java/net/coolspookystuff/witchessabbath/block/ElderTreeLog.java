package net.coolspookystuff.witchessabbath.block;

import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.VexEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class ElderTreeLog extends LogBlock {

	public ElderTreeLog(MaterialColor materialColor_1, Settings block$Settings_1) {
		super(MaterialColor.WOOD, FabricBlockSettings.of(Material.WOOD).strength(1.0f, 1.0f).sounds(BlockSoundGroup.WOOD).build());
	}
		
		public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) { 
            VexEntity entSpawner = EntityType.VEX.create(world_1);
            entSpawner.setPositionAndAngles((double)blockPos_1.getX() + 0.5D, (double)blockPos_1.getY(), (double)blockPos_1.getZ() + 0.5D, 0.0F, 0.0F);        
            world_1.spawnEntity(entSpawner);
			
	}
	
}
