package net.coolspookystuff.witchessabbath.block;

import java.util.Random;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.minecraft.block.Block.Settings;
import net.minecraft.block.BlockState;
import net.minecraft.block.BlockWithEntity;
import net.minecraft.block.Blocks;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.PillarBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.BlockView;
import net.minecraft.world.World;

public class AlderTreeLog extends PillarBlock {

	public AlderTreeLog(MaterialColor materialColor_1, Settings block$Settings_1) {
		super(FabricBlockSettings.of(Material.WOOD).strength(1.0f, 1.0f).sounds(BlockSoundGroup.WOOD).build());
	}
		
		public void onBlockRemoved(BlockState blockState_1, World world, BlockPos blockBreakPos, BlockState blockState_2, boolean boolean_1) { 
			Random treeRand = new Random();
			Random xrand = new Random();
			Random yrand = new Random();
			int chance;
			int randx = xrand.nextInt(20 - 10);
			int randz = yrand.nextInt(20 - 10);
			chance = treeRand.nextInt(5);
			if(chance == 0) {
				WoseEntity entSpawner = WitchesSabbath.WOSE.create(world);
				entSpawner.setPositionAndAngles((double)blockBreakPos.getX() + 0.5D, (double)blockBreakPos.getY(), (double)blockBreakPos.getZ() + randx, 0.0F, randz);        
				world.spawnEntity(entSpawner);
				  world.playSound(null, blockBreakPos, SoundEvents.BLOCK_ENDER_CHEST_OPEN, SoundCategory.AMBIENT, 1f, 1f);
			}			
		}	
	}
