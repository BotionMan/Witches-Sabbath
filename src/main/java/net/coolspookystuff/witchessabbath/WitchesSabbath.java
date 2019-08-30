package net.coolspookystuff.witchessabbath;


import net.coolspookystuff.witchessabbath.block.RowanSaplingBlock;
import net.coolspookystuff.witchessabbath.block.TestBigBlock;
import net.coolspookystuff.witchessabbath.block.WitchesCauldronBlock;
import net.coolspookystuff.witchessabbath.block.WitchesOvenBlock;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.Material;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.util.registry.*;

public class WitchesSabbath implements ModInitializer {

	public static final ItemGroup WITCHES_SABBATH = FabricItemGroupBuilder.build(
			new Identifier("walpurgisnacht", "main"),
			() -> new ItemStack(WitchesSabbath.MANDRAKE_ROOT));
	
    public static final Item MANDRAKE_ROOT = new Item(new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH));
    
    public static final Block ROWAN_LOG = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    public static final WitchesOvenBlock WITCHES_OVEN = new WitchesOvenBlock();
    public static final LeavesBlock ROWAN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.25f, 0.25f).sounds(BlockSoundGroup.GRASS).build());
    public static final RowanSaplingBlock ROWAN_SAPLING = new RowanSaplingBlock(null);
    public static final WitchesCauldronBlock WITCHES_CAULDRON = new WitchesCauldronBlock();
    public static final TestBigBlock TEST_BIG_BLOCK = new TestBigBlock();
        
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("If you can read this message, the mod is loading, and it didn't crash yet.");


        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "mandrake_root"), MANDRAKE_ROOT);
        
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_log"), new BlockItem(ROWAN_LOG, new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "witches_oven"), new BlockItem(WITCHES_OVEN, new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_leaves"), new BlockItem(ROWAN_LEAVES, new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "witches_cauldron"), new BlockItem(WITCHES_CAULDRON, new Item.Settings().group(WitchesSabbath.WITCHES_SABBATH)));

        
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "witches_oven"), WITCHES_OVEN);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_log"), ROWAN_LOG);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_leaves"), ROWAN_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "witches_cauldron"), WITCHES_CAULDRON);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "test_big_block"), TEST_BIG_BLOCK);
	}
}
