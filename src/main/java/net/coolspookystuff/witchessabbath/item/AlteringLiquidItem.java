package net.coolspookystuff.witchessabbath.item;



import net.coolspookystuff.witchessabbath.WitchesSabbath;
import net.minecraft.block.Block;
import net.minecraft.block.BlockState;
import net.minecraft.item.Item;
import net.minecraft.item.ItemUsageContext;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.math.BlockPos;

public class AlteringLiquidItem<T> extends Item {
	
	public AlteringLiquidItem(Settings item$Settings) {
		super(item$Settings);
	}
	
	@Override
	public ActionResult useOnBlock(ItemUsageContext itemUsageContext) {
		if(!itemUsageContext.getWorld().isClient) {
			BlockState mutation = WitchesSabbath.MUATION_TABLE_TAG.getRandom(itemUsageContext.getWorld().random).getDefaultState();
			BlockPos mutPos = itemUsageContext.getBlockPos();
			BlockState block = itemUsageContext.getWorld().getBlockState(mutPos);
			Block blockAtPos = block.getBlock();
			double double_1 = (double)mutPos.getX();
			double double_2 = (double)mutPos.getY();
			double double_3 = (double)mutPos.getZ();
			if(WitchesSabbath.MUATION_TABLE_TAG.contains(blockAtPos)) {
				ItemStack itemStack = itemUsageContext.getStack();
				itemUsageContext.getWorld().setBlockState(mutPos, mutation);		
				itemStack.decrement(1);
				itemUsageContext.getWorld().addParticle(ParticleTypes.SMOKE, double_1, double_2, double_3, 0.0D, 0.0D, 0.0D);
			}
		}
		return ActionResult.SUCCESS;
	}
}