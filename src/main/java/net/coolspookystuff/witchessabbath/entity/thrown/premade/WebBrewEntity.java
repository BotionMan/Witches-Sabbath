package net.coolspookystuff.witchessabbath.entity.thrown.premade;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.BlazeEntity;
import net.minecraft.entity.thrown.SnowballEntity;
import net.minecraft.entity.thrown.ThrownItemEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.particle.ItemStackParticleEffect;
import net.minecraft.particle.ParticleEffect;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.util.hit.BlockHitResult;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.util.hit.HitResult;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

public class WebBrewEntity extends ThrownItemEntity {
	
	   public WebBrewEntity(EntityType<? extends WebBrewEntity> entityType, World world) {
		      super(entityType, world);
		   }



		   public WebBrewEntity(World world_1, double double_1, double double_2, double double_3) {
		      super(EntityType.SNOWBALL, double_1, double_2, double_3, world_1);
		   }

		   protected Item getDefaultItem() {
		      return Items.SNOWBALL;
		   }

		   @Environment(EnvType.CLIENT)
		   private ParticleEffect getParticleParameters() {
		      ItemStack itemStack_1 = this.getItem();
		      return (ParticleEffect)(itemStack_1.isEmpty() ? ParticleTypes.ITEM_SNOWBALL : new ItemStackParticleEffect(ParticleTypes.ITEM, itemStack_1));
		   }

		   @Environment(EnvType.CLIENT)
		   public void handleStatus(byte byte_1) {
		      if (byte_1 == 3) {
		         ParticleEffect particleEffect_1 = this.getParticleParameters();

		         for(int int_1 = 0; int_1 < 8; ++int_1) {
		            this.world.addParticle(particleEffect_1, this.x, this.y, this.z, 0.0D, 0.0D, 0.0D);
		         }
		      }
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
}
