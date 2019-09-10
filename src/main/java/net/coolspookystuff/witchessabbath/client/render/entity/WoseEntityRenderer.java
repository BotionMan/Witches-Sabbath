package net.coolspookystuff.witchessabbath.client.render.entity;

import net.coolspookystuff.witchessabbath.client.entity.render.model.WoseEntityModel;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.minecraft.client.render.entity.EntityRenderDispatcher;
import net.minecraft.client.render.entity.MobEntityRenderer;
import net.minecraft.util.Identifier;


public class WoseEntityRenderer extends MobEntityRenderer<WoseEntity, WoseEntityModel<WoseEntity>> {
	public WoseEntityRenderer(EntityRenderDispatcher entityRenderDispatcher_1)
	{
		super(entityRenderDispatcher_1, new WoseEntityModel<WoseEntity>(), 1);
	}
	protected Identifier getTexture(WoseEntity wose) {
	    return new Identifier("walpurgisnacht:textures/entity/wose.png");
	}

}
