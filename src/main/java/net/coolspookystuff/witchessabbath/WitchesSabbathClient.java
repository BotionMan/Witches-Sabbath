package net.coolspookystuff.witchessabbath;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.fabricmc.fabric.api.client.render.EntityRendererRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.minecraft.client.render.entity.ProjectileEntityRenderer;
import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.coolspookystuff.witchessabbath.client.render.entity.MandrakeEntityRenderer;
import net.coolspookystuff.witchessabbath.client.render.entity.WoseEntityRenderer;
import net.coolspookystuff.witchessabbath.entity.mob.MandrakeEntity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;

public class WitchesSabbathClient implements ClientModInitializer {
	
    @Override
    public void onInitializeClient() {
    	EntityRendererRegistry.INSTANCE.register(WoseEntity.class, (entityRenderDispatcher, context) -> new WoseEntityRenderer(entityRenderDispatcher));
    	EntityRendererRegistry.INSTANCE.register(MandrakeEntity.class, (entityRenderDispatcher, context) -> new MandrakeEntityRenderer(entityRenderDispatcher));
    	
    	ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
            BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
            return provider == null ? -1 : provider.getColor(block, pos, world, layer);
        },
        WitchesSabbath.ROWAN_LEAVES);
    }
}
