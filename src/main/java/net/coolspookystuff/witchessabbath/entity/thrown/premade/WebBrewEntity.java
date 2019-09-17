package net.coolspookystuff.witchessabbath.entity.thrown.premade;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.FlyingItemEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.entity.thrown.ThrownPotionEntity;
import net.minecraft.item.Item;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WebBrewEntity extends ThrownPotionEntity implements FlyingItemEntity {

	public WebBrewEntity(EntityType<? extends WebBrewEntity> entityType, World world) {
		super(EntityType.POTION, world);
    }

	@Override
	protected void onCollision(HitResult hitResult) {
		if (hitResult.getType() == HitResult.Type.BLOCK) {
			BlockPos blockPos;
			int i = 1;
			BlockState webBlockState = Blocks.COBWEB.getDefaultState();	
			BlockHitResult blockHitResult = (BlockHitResult)hitResult;
			blockPos = blockHitResult.getBlockPos();
			webBlockState.getBlock();			
			world.setBlockState(blockPos.up(i), webBlockState);		
			if (!this.world.isClient) {
				this.world.sendEntityStatus(this, (byte)3);
				this.remove();
			}

		}		
	}
	protected Item getDefaultItem() {
		return WitchesSabbath.MANDRAKE_ROOT;
	}
}
