package net.coolspookystuff.witchessabbath.entity.mob;


import net.minecraft.entity.EntityType;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.HostileEntity;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.world.World;

public class MandrakeEntity extends HostileEntity {
		public MandrakeEntity(EntityType<? extends HostileEntity> entityType_1, World world_1) {
			super(entityType_1, world_1);
		stepHeight = 3.0F;
	}
	protected void initAttributes() {
		super.initAttributes();
		this.getAttributeInstance(EntityAttributes.MAX_HEALTH).setBaseValue(5.0D);
		this.getAttributeInstance(EntityAttributes.MOVEMENT_SPEED).setBaseValue(0D);
		this.getAttributeInstance(EntityAttributes.KNOCKBACK_RESISTANCE).setBaseValue(-1.0D);
	}
		protected SoundEvent getAmbientSound() {
			return SoundEvents.ENTITY_GHAST_AMBIENT;
			}
		
		protected SoundEvent getHurtSound(DamageSource damageSource_1) {
			return SoundEvents.ENTITY_GHAST_HURT;
		}
	

		protected SoundEvent getDeathSound() {
			return SoundEvents.ENTITY_GHAST_DEATH;
		}
}
