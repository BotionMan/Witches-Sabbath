package net.coolspookystuff.witchessabbath.block.sapling;

import java.util.Random;

import net.minecraft.block.sapling.SaplingGenerator;
import java.util.function.Supplier;
import net.minecraft.world.gen.feature.AbstractTreeFeature;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;



public class MagicSaplingGenerator extends SaplingGenerator {
	public final Supplier<AbstractTreeFeature<DefaultFeatureConfig>> featureSupplier;
	
	public MagicSaplingGenerator(Supplier<AbstractTreeFeature<DefaultFeatureConfig>> featureSupplier) {
		this.featureSupplier = featureSupplier;
	}

	@Override
	protected AbstractTreeFeature<DefaultFeatureConfig> createTreeFeature(Random rand) {
		return featureSupplier.get();
	}
	

}
