package net.coolspookystuff.witchessabbath;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.render.ColorProviderRegistry;
import net.minecraft.block.Blocks;
import net.minecraft.client.color.block.BlockColorProvider;
import net.coolspookystuff.witchessabbath.WitchesSabbath;

public class WitchesSabbathClient implements ClientModInitializer {
	
    @Override
    public void onInitializeClient() {
        ColorProviderRegistry.BLOCK.register((block, pos, world, layer) -> {
            BlockColorProvider provider = ColorProviderRegistry.BLOCK.get(Blocks.GRASS);
            return provider == null ? -1 : provider.getColor(block, pos, world, layer);
        },
        WitchesSabbath.ROWAN_LEAVES);
    }
}
