package net.coolspookystuff.witchessabbath.client.entity.render.model;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.coolspookystuff.witchessabbath.entity.mob.MandrakeEntity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.model.Cuboid;



public class MandrakeEntityModel<T extends Entity> extends EntityModel<MandrakeEntity> {
        private final Cuboid Body;
        private final Cuboid bone;
        private final Cuboid legr;
        private final Cuboid legl;
        private final Cuboid leaf;
        private final Cuboid leaf2;

        public MandrakeEntityModel () {
                textureWidth = 64;
                textureHeight = 64;

                Body = new Cuboid(this);
                Body.setRotationPoint(0.0F, 24.0F, 0.0F);

                bone = new Cuboid(this);
                bone.setRotationPoint(0.0F, 0.0F, 0.0F);
                Body.addChild(bone);
                bone.addBox("           bone", -4.0F, -9.0F, -3.0F, 8, 9, 7, 0.0F, 0, 0);
                bone.addBox("           bone", -3.0F, -12.0F, -2.0F, 6, 3, 5, 0.0F, 0, 16);
                bone.addBox("           bone", -2.0F, -13.0F, 0.0F, 4, 1, 2, 0.0F, 17, 16);
                bone.addBox("           bone", -5.0F, -8.0F, 1.0F, 1, 1, 0, 0.0F, 17, 17);
                bone.addBox("           bone", -6.0F, -7.0F, 1.0F, 1, 3, 0, 0.0F, 0, 16);
                bone.addBox("           bone", -7.0F, -4.0F, 1.0F, 1, 3, 0, 0.0F, 4, 4);
                bone.addBox("           bone", 4.0F, -7.0F, 1.0F, 1, 2, 0, 0.0F, 2, 16);
                bone.addBox("           bone", 5.0F, -5.0F, 1.0F, 1, 4, 0, 0.0F, 4, 0);

                legr = new Cuboid(this);
                legr.setRotationPoint(0.0F, 0.0F, 0.0F);
                Body.addChild(legr);
                legr.addBox("           legr", -3.25F, -2.0F, -7.75F, 3, 2, 5, 0.0F, 23, 0);

                legl = new Cuboid(this);
                legl.setRotationPoint(0.0F, 0.0F, 0.0F);
                Body.addChild(legl);
                legl.addBox("           legl", 0.25F, -2.0F, -7.75F, 3, 2, 5, 0.0F, 17, 19);

                leaf = new Cuboid(this);
                leaf.setRotationPoint(0.0F, 0.0F, 0.0F);
                Body.addChild(leaf);
                leaf.addBox("           leaf", 2.0F, -19.0F, 1.0F, 1, 7, 0, 0.0F, 2, 0);
                leaf.addBox("           leaf", 0.0F, -16.0F, 1.0F, 2, 1, 0, 0.0F, 17, 20);
                leaf.addBox("           leaf", 3.0F, -18.0F, 1.0F, 2, 1, 0, 0.0F, 0, 20);

                leaf2 = new Cuboid(this);
                leaf2.setRotationPoint(0.0F, 0.0F, 0.0F);
                Body.addChild(leaf2);
                leaf2.addBox("          leaf2", -3.0F, -19.0F, 1.0F, 1, 7, 0, 0.0F, 0, 0);
                leaf2.addBox("          leaf2", -5.0F, -16.0F, 1.0F, 2, 1, 0, 0.0F, 17, 19);
                leaf2.addBox("          leaf2", -2.0F, -18.0F, 1.0F, 2, 1, 0, 0.0F, 0, 19);
        }

        @Override
        public void render(MandrakeEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
                Body.render(f5);
        }
        public void setAngles(MandrakeEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
        	legr.pitch = 0.0F;
        	legr.yaw = 0.3491F;
        	legr.roll = 0.0F;
        	
        	legl.pitch = 0.0F;
        	legl.yaw = -0.3491F;
        	legl.roll = 0.0F;
        	
        	leaf.pitch = 0.0F;
        	leaf.yaw = 0.0F;
        	leaf.roll = -0.2618F;
        	
        	leaf2.pitch = 0.0F;
        	leaf2.yaw = 0.0F;
        	leaf2.roll = 0.2618F;
        	
        	
        	
        }
        public void setRotationAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
        }
}