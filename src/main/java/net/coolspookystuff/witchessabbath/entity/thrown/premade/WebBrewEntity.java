package net.coolspookystuff.witchessabbath.entity.thrown.premade;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.thrown.ThrownEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.entity.thrown.ThrownPotionEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WebBrewEntity extends ThrownItemEntity implements FlyingItemEntity {

	public WebBrewEntity(EntityType<? extends WebBrewEntity> entityType_1, World world) {
		super(entityType_1, world);
	}

	@Override
	protected void onCollision(HitResult hitResult) {
		if (hitResult.getType() == HitResult.Type.BLOCK) {
			BlockPos blockPos;
			BlockState webBlockState = Blocks.COBWEB.getDefaultState() ;	
			BlockHitResult blockHitResult = (BlockHitResult)hitResult;
			blockPos = blockHitResult.getBlockPos();
			webBlockState.getBlock();			
			world.setBlockState(blockPos, webBlockState);
		
		}
		
		
	}

	@Override
	protected void initDataTracker() {
		// TODO Auto-generated method stub
		
	}
	@Override
	protected Item getDefaultItem() {
		// TODO Auto-generated method stub
		return WitchesSabbath.MANDRAKE_ROOT;
	}
}
