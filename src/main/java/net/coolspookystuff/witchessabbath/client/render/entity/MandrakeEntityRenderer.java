package net.coolspookystuff.witchessabbath.client.render.entity;

import net.coolspookystuff.witchessabbath.client.entity.render.model.MandrakeEntityModel;
import net.coolspookystuff.witchessabbath.entity.mob.MandrakeEntity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;


public class MandrakeEntityRenderer extends MobEntityRenderer<MandrakeEntity, MandrakeEntityModel<MandrakeEntity>> {
	public MandrakeEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1)
	{
		super(entityRenderDispatcher_1, new MandrakeEntityModel<MandrakeEntity>(), 1);
	}
	protected Identifier getTexture(WoseEntity wose) {
	    return new Identifier("walpurgisnacht:textures/entity/wose.png");
	}
	@Override
	protected Identifier getTexture(MandrakeEntity var1) {
		return new Identifier("walpurgisnacht:textures/entity/mandrake.png");
	}

}
