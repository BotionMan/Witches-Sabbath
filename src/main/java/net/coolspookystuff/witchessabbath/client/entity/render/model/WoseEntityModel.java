package net.coolspookystuff.witchessabbath.client.entity.render.model;

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.model.Cuboid;




public class WoseEntityModel<T extends Entity> extends EntityModel	<WoseEntity> {
        private final Cuboid bone;
        private final Cuboid footl;
        private final Cuboid footr;
        private final Cuboid bodyr;
        private final Cuboid bodyl;
        private final Cuboid trunk;

        public WoseEntityModel() {
                textureWidth = 512;
                textureHeight = 512;

                bone = new Cuboid(this);
                bone.setRotationPoint(0.0F, 24.0F, 0.0F);

                footl = new Cuboid(this);
                footl.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(footl, 0.0F, 0.0F, -0.4363F);
                bone.addChild(footl);
                footl.addBox("          footl", -13.0F, -9.0F, -7.0F, 9, 4, 6, 0.0F, 0, 0);
                footl.addBox("          footl", -13.0F, -9.0F, 1.0F, 9, 4, 6, 0.0F, 0, 0);

                footr = new Cuboid(this);
                footr.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(footr, 0.0F, 0.0F, 0.4363F);
                bone.addChild(footr);
                footr.addBox("          footr", 4.0F, -9.0F, 1.0F, 9, 4, 6, 0.0F, 0, 0);
                footr.addBox("          footr", 4.0F, -9.0F, -7.0F, 9, 4, 6, 0.0F, 0, 0);

                bodyr = new Cuboid(this);
                bodyr.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(bodyr, 0.0F, -0.5236F, -0.5236F);
                bone.addChild(bodyr);
                bodyr.addBox("          bodyr", 37.0F, -57.0F, -25.0F, 6, 32, 10, 0.0F, 144, 96);
                bodyr.addBox("          bodyr", 36.0F, -25.0F, -26.0F, 8, 6, 30, 0.0F, 0, 36);
                bodyr.addBox("          bodyr", 37.0F, -24.0F, 4.0F, 6, 4, 7, 0.0F, 0, 11);

                bodyl = new Cuboid(this);
                bodyl.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(bodyl, 0.0F, 0.5236F, 0.5236F);
                bone.addChild(bodyl);
                bodyl.addBox("         bodyl2", -43.0F, -57.0F, -25.0F, 6, 32, 10, 0.0F, 0, 96);
                bodyl.addBox("         bodyl2", -44.0F, -25.0F, -26.0F, 8, 6, 30, 0.0F, 0, 0);
                bodyl.addBox("         bodyl2", -43.0F, -24.0F, 4.0F, 6, 4, 7, 0.0F, 0, 0);

                trunk = new Cuboid(this);
                trunk.setRotationPoint(9.0F, 0.0F, 20.0F);
                bone.addChild(trunk);
                trunk.addBox("          trunk", -17.0F, -81.0F, -28.0F, 16, 80, 16, 0.0F, 0, 160);
                trunk.addBox("          trunk", -49.0F, -81.0F, -60.0F, 80, 16, 80, 0.0F, 0, 0);
                trunk.addBox("          trunk", -33.0F, -97.0F, -44.0F, 48, 16, 48, 0.0F, 0, 96);
        }

        @Override
        public void render(WoseEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
                bone.render(f5);
        }
        public void setAngles(WoseEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        	bodyr.pitch = 0.0F;
        	bodyr.yaw = -0.5236F;
        	bodyr.roll = -0.5236F;
        	
        	bone.yaw = 3.1416F;
        
        	bodyl.pitch = 0.0F;
        	bodyl.yaw = 0.5236F;
        	bodyl.roll = 0.5236F;
        	
        	footl.pitch = 0.0F;
        	footl.yaw =  0.0F;
        	footl.roll = -0.4363F;
        	
        	footr.pitch = 0.0F;
        	footr.yaw = 0.0F;
        	footr.roll = 0.4363F;
        	
        	
        }
        public void woseAttackArmChange(WoseEntity woseEntity, float float_1, float float_2, float float_3) {
            int int_1 = woseEntity.ii();
            if (int_1 > 0) {
                this.bodyl.pitch = -2.0F + 1.5F * this.wosearmfloat((float)int_1 - float_3, 10.0F);
                this.bodyr.pitch = -2.0F + 1.5F * this.wosearmfloat((float)int_1 - float_3, 10.0F);
             } else {
                int int_2 = woseEntity.aa();
                if (int_2 > 0) {
                   this.bodyl.pitch = -0.8F + 0.025F * this.wosearmfloat((float)int_2, 70.0F);
                   this.bodyr.pitch = 0.0F;
                } else {
                   this.bodyl.pitch = (-0.2F + 1.5F * this.wosearmfloat(float_1, 13.0F)) * float_2;
                   this.bodyr.pitch = (-0.2F - 1.5F * this.wosearmfloat(float_1, 13.0F)) * float_2;
                }
            	
            }
        }
        private float wosearmfloat(float float_1, float float_2) {
        	return (Math.abs(float_1 % float_2 - float_2 * 0.5F) - float_2 * 0.25F) / (float_2 * 0.25F);
        }
        
        public void setRotationAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
        }
}