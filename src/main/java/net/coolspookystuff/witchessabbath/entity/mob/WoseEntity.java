package net.coolspookystuff.witchessabbath.entity.mob;

import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.ai.goal.GoToEntityTargetGoal;
import net.minecraft.entity.ai.goal.MeleeAttackGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.CreeperEntity;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.mob.Monster;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;
import net.minecraft.entity.mob.Monster;

public class WoseEntity extends HostileEntity {
	public int i;


	public WoseEntity(EntityType<? extends HostileEntity> entityType_1, World world_1) {
		super(entityType_1, world_1);
	    stepHeight = 3.0F;
	}
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(100.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0.5D);
		this.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(1.0D);
	}
	protected void initGoals() {
		goalSelector.add(1, new MeleeAttackGoal(this, 1.0D, true));
		goalSelector.add(2, new GoToEntityTargetGoal(this, 0.9D, 32.0F));
		targetSelector.add(1, new FollowTargetGoal<MobEntity>(this, MobEntity.class, 25, false, false, (livingEntity_1) ->{
			return livingEntity_1 instanceof Monster && !(livingEntity_1 instanceof CreeperEntity);
		}));
		targetSelector.add(2, new FollowTargetGoal<MobEntity>(this, MobEntity.class, 25, false, false, (livingEntity_1) ->{
			return livingEntity_1 instanceof PlayerEntity && !(livingEntity_1 instanceof CreeperEntity);
		}));
	}	

	public boolean tryAttack(Entity entity_1) {
		this.i = 10;
		this.world.sendEntityStatus(this, (byte)4);
		boolean boolean_1 = entity_1.damage(DamageSource.mob(this), (float)(7 + this.random.nextInt(15)));
		if (boolean_1) {
			entity_1.setVelocity(entity_1.getVelocity().add(0.0D, 0.4000000059604645D, 0.0D));
			this.dealDamage(this, entity_1);
		}

		this.playSound(WitchesSabbath.WOSE_ATTACK, 1.0F, 1.0F);
		return boolean_1;
	}

	protected SoundEvent getAmbientSound() {
		return WitchesSabbath.WOSE_IDLE;
	}
	
	protected SoundEvent getHurtSound(DamageSource damageSource_1) {
		return WitchesSabbath.WOSE_HIT;
	}
	

	protected SoundEvent getDeathSound() {
		return WitchesSabbath.WOSE_DIE;
	}
}
