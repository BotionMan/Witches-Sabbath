package net.coolspookystuff.witchessabbath.client.entity.render.model;

//Made with Blockbench
//Paste this code into your mod.

import net.minecraft.client.render.entity.model.EntityModel;
import net.minecraft.entity.Entity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.model.Cuboid;




public class abcd<T extends Entity> extends EntityModel<WoseEntity> {
        private final Cuboid bone;
        private final Cuboid bone2;
        private final Cuboid bone3;

        public abcd() {
                textureWidth = 16;
                textureHeight = 16;

                bone = new Cuboid(this);
                bone.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(bone, -0.7854F, 0.0F, 0.0F);
                bone.addBox("           bone", -5.0F, -14.0F, 1.0F, 9, 7, 1, 0.0F, 0, 0);

                bone2 = new Cuboid(this);
                bone2.setRotationPoint(0.0F, 0.0F, 0.0F);
                setRotationAngle(bone2, -0.3491F, 0.0F, 0.0F);
                bone.addChild(bone2);
                bone2.addBox("          bone2", -1.0F, -12.0F, 0.0F, 5, 12, 1, 0.0F, 0, 0);

                bone3 = new Cuboid(this);
                bone3.setRotationPoint(0.0F, 24.0F, 0.0F);
                setRotationAngle(bone3, -0.4363F, -0.1745F, 0.0F);
                bone3.addBox("          bone3", -1.0F, -5.0F, -6.0F, 1, 5, 1, 0.0F, 0, 0);
                bone3.addBox("          bone3", -1.0F, -5.0F, -8.0F, 1, 5, 1, 0.0F, 0, 0);
        }

        @Override
        public void render(WoseEntity entity, float f, float f1, float f2, float f3, float f4, float f5) {
                bone.render(f5);
                bone3.render(f5);
        }
        public void setRotationAngle(Cuboid modelRenderer, float x, float y, float z) {
        modelRenderer.rotationPointX = x;
        modelRenderer.rotationPointY = y;
        modelRenderer.rotationPointZ = z;
        }
}