package net.coolspookystuff.witchessabbath.block;

import java.util.Random;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class ElderTreeLog extends BlockWithEntity {

	public ElderTreeLog(MaterialColor materialColor_1, Settings block$Settings_1) {
		super(FabricBlockSettings.of(Material.WOOD).strength(1.0f, 1.0f).sounds(BlockSoundGroup.WOOD).build());
	}
		
		public void onBlockRemoved(BlockState blockState_1, World world_1, BlockPos blockPos_1, BlockState blockState_2, boolean boolean_1) { 
			Random treeRand = new Random();
			int chance;
			chance = treeRand.nextInt(49);
			if(chance == 0) {
				WoseEntity entSpawner = WitchesSabbath.WOSE.create(world_1);
				entSpawner.setPositionAndAngles((double)blockPos_1.getX() + 0.5D, (double)blockPos_1.getY(), (double)blockPos_1.getZ() + 5.0D, 0.0F, 5.0F);        
				world_1.spawnEntity(entSpawner);
			}			
	}

		@Override
		public BlockEntity createBlockEntity(BlockView var1) {
			// TODO Auto-generated method stub
			return null;
		}
	
}
