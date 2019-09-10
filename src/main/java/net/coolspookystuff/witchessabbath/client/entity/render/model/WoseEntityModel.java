package net.coolspookystuff.witchessabbath.client.entity.render.model;

//Made with Blockbench

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.model.Cuboid;




public class WoseEntityModel<T extends Entity> extends EntityModel<WoseEntity> {
        private final Cuboid all;
        private final Cuboid trunk;
        private final Cuboid armr;
        private final Cuboid arml;
        private final Cuboid footr;
        private final Cuboid footl;

        public WoseEntityModel() {
        	textureWidth = 512;
            textureHeight = 512;

            all = new Cuboid(this);
            all.setRotationPoint(0.0F, 24.0F, 0.0F);

            trunk = new Cuboid(this);
            trunk.setRotationPoint(0.0F, 0.0F, 0.0F);
            all.addChild(trunk);
            trunk.addBox("          trunk", -8.0F, -81.0F, -8.0F, 16, 80, 16, 0.0F, 0, 160);
            trunk.addBox("          trunk", -40.0F, -81.0F, -40.0F, 80, 16, 80, 0.0F, 0, 0);
            trunk.addBox("          trunk", -24.0F, -97.0F, -24.0F, 48, 16, 48, 0.0F, 0, 96);

            armr = new Cuboid(this);
            armr.setRotationPoint(-8.0F, -72.0F, 0.0F);
            all.addChild(armr);
            armr.addBox("           armr", -5.0F, 0.0F, -5.0F, 6, 32, 10, 0.0F, 144, 96);
            armr.addBox("           armr", -6.0F, 32.0F, -26.0F, 8, 6, 32, 0.0F, 0, 38);
            armr.addBox("           armr", -5.0F, 33.0F, -31.0F, 6, 4, 5, 0.0F, 0, 48);

            arml = new Cuboid(this);
            arml.setRotationPoint(8.0F, -72.0F, 0.0F);
            all.addChild(arml);
            arml.addBox("           arml", -1.0F, 0.0F, -5.0F, 6, 32, 10, 0.0F, 0, 96);
            arml.addBox("           arml", -2.0F, 32.0F, -26.0F, 8, 6, 32, 0.0F, 0, 0);
            arml.addBox("           arml", -1.0F, 33.0F, -31.0F, 6, 4, 5, 0.0F, 48, 48);

            footr = new Cuboid(this);
            footr.setRotationPoint(8.0F, -6.0F, 0.0F);
            all.addChild(footr);
            footr.addBox("          footr", -1.0F, 0.0F, -7.0F, 9, 4, 6, 0.0F, 0, 38);
            footr.addBox("          footr", -1.0F, 0.0F, 1.0F, 9, 4, 6, 0.0F, 0, 0);

            footl = new Cuboid(this);
            footl.setRotationPoint(0.0F, 0.0F, 0.0F);
            all.addChild(footl);
            footl.addBox("          footl", -13.0F, -9.0F, 1.0F, 9, 4, 6, 0.0F, 0, 20);
            footl.addBox("          footl", -13.0F, -9.0F, -7.0F, 9, 4, 6, 0.0F, 0, 10);
        }

        @Override
        public void render(WoseEntity wose, float f, float f1, float f2, float f3, float f4, float f5) {
        	all.render(f5);
        }

        
        public void setAngles(WoseEntity wose, float f, float f1, float f2, float f3, float f4, float f5) {
        	armr.pitch = 0.0F;
        	armr.yaw = -0.3491F;
        	armr.roll = 0.5236F;
        
        	arml.pitch = 0.0F;
        	arml.yaw = 0.3491F;
        	arml.roll = -0.5236F;
        	
        	footr.pitch = 0.0F;
        	footr.yaw = 0.0F;
        	footr.roll = 0.4363F;
        	
        	footl.pitch = 0.0F;
        	footl.yaw =  0.0F;
        	footl.roll = -0.4363F;               	
        }
        
        public void setRotationAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
        }
}