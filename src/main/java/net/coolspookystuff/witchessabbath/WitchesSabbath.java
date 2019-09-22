package net.coolspookystuff.witchessabbath;


import net.coolspookystuff.witchessabbath.block.AlderTreeLog;
import net.coolspookystuff.witchessabbath.block.MagicSaplingBlock;
import net.coolspookystuff.witchessabbath.block.MandrakeRootsBlock;
import net.coolspookystuff.witchessabbath.block.WitchesCauldronBlock;
import net.coolspookystuff.witchessabbath.block.WitchesOvenBlock;
import net.coolspookystuff.witchessabbath.block.entity.WitchesCauldronBlockEntity;
import net.coolspookystuff.witchessabbath.block.entity.WitchesOvenBlockEntity;
import net.coolspookystuff.witchessabbath.block.sapling.MagicSaplingGenerator;
import net.coolspookystuff.witchessabbath.entity.mob.MandrakeEntity;
import net.coolspookystuff.witchessabbath.entity.mob.WoseEntity;
import net.coolspookystuff.witchessabbath.entity.thrown.premade.WebBrewEntity;
import net.coolspookystuff.witchessabbath.item.AlteringLiquidItem;
import net.coolspookystuff.witchessabbath.item.SilverSwordItem;
import net.coolspookystuff.witchessabbath.item.WebBrewItem;
import net.coolspookystuff.witchessabbath.world.feature.AlderTreeFeature;
import net.coolspookystuff.witchessabbath.world.feature.RowanTreeFeature;
import net.fabricmc.api.ModInitializer;
import net.minecraft.block.Block;
import net.minecraft.block.FenceBlock;
import net.minecraft.block.LeavesBlock;
import net.minecraft.block.LogBlock;
import net.minecraft.block.Material;
import net.minecraft.block.MaterialColor;
import net.minecraft.block.entity.BlockEntityType;
import net.minecraft.entity.EntityCategory;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.BlockItem;
import net.minecraft.item.FoodComponent;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.util.Identifier;
import net.fabricmc.fabric.api.block.FabricBlockSettings;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.entity.FabricEntityTypeBuilder;
import net.fabricmc.fabric.api.tag.TagRegistry;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ToolMaterials;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.sound.SoundEvent;
import net.minecraft.tag.Tag;
import net.minecraft.util.registry.*;
import net.minecraft.world.gen.feature.DefaultFeatureConfig;

public class WitchesSabbath implements ModInitializer {

	public static final ItemGroup WITCHES_SABBATH = FabricItemGroupBuilder.build(
			new Identifier("walpurgisnacht", "main"),
			() -> new ItemStack(WitchesSabbath.MANDRAKE_ROOT));
    public static final EntityType<WoseEntity> WOSE =
    		Registry.register(
    				Registry.ENTITY_TYPE,
    				new Identifier("walpurgisnacht", "wose"),
    				FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, WoseEntity::new).size(EntityDimensions.fixed(1, 6)).build()
    				);
    public static final EntityType<MandrakeEntity> MANDRAKE =
    		Registry.register(
    				Registry.ENTITY_TYPE,
    				new Identifier("walpurgisnacht", "mandrake"),
    				FabricEntityTypeBuilder.create(EntityCategory.AMBIENT, MandrakeEntity::new).size(EntityDimensions.fixed(0.3f, 0.7f)).build()
    				);
    public static final EntityType<WebBrewEntity>
    WEB_REW_ENTITY = Registry.register(Registry.ENTITY_TYPE, new Identifier("walpurgisnacht", "splash_potion"),
    	    FabricEntityTypeBuilder.create(EntityCategory.MISC, WebBrewEntity::new).build());

    public static final Tag<Block> MUATION_TABLE_TAG = TagRegistry.block(new Identifier("walpurgisnacht", "mutation_table"));
    
    public static final Item MANDRAKE_ROOT = new Item(new Item.Settings().group(WITCHES_SABBATH));
	public static Item ROWAN_BERRIES = new Item(new Item.Settings().group(WITCHES_SABBATH).food(new FoodComponent.Builder().snack().alwaysEdible().hunger(2).build()));
	
    public static final AlteringLiquidItem MUTANDIS_COPYKAT = new AlteringLiquidItem(new Item.Settings().group(WITCHES_SABBATH));

    public static final Item MANDRAKE_SEEDS = new Item(new Item.Settings().group(WITCHES_SABBATH));
    public static final WebBrewItem WEB_BREW = new WebBrewItem(new Item.Settings().group(WITCHES_SABBATH));

    public static final SilverSwordItem SILVER_SWORD = new SilverSwordItem(ToolMaterials.IRON, 3, -2.8f);
    
    public static final LogBlock ROWAN_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    public static final AlderTreeLog ALDER_TREE_LOG = new AlderTreeLog(MaterialColor.WOOD, FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    public static final LogBlock ALDER_LOG = new LogBlock(MaterialColor.WOOD, FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    
    public static final Block ROWAN_PLANKS = new Block(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());

    public static final FenceBlock ROWAN_FENCE = new FenceBlock(FabricBlockSettings.of(Material.WOOD).strength(2.0f, 2.0f).sounds(BlockSoundGroup.WOOD).build());
    
	public static final Block ALDER_SAPLING = new MagicSaplingBlock(new MagicSaplingGenerator(() -> new AlderTreeFeature(DefaultFeatureConfig::deserialize, true))); 
    public static final Block ROWAN_SAPLING = new MagicSaplingBlock(new MagicSaplingGenerator(() -> new RowanTreeFeature(DefaultFeatureConfig::deserialize, true))); 
    public static final LeavesBlock ALDER_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.25f, 0.25f).sounds(BlockSoundGroup.GRASS).build());
    public static final LeavesBlock ROWAN_LEAVES = new LeavesBlock(FabricBlockSettings.of(Material.LEAVES).strength(0.25f, 0.25f).sounds(BlockSoundGroup.GRASS).build());
    
    public static final MandrakeRootsBlock MANDRAKE_ROOTS = new MandrakeRootsBlock();
    
	public static final WitchesOvenBlock WITCHES_OVEN = new WitchesOvenBlock();
    public static final WitchesCauldronBlock WITCHES_CAULDRON = new WitchesCauldronBlock();

    
    public static BlockEntityType<WitchesOvenBlockEntity> WITCHES_OVEN_BLOCK_ENTITY;
    public static BlockEntityType<WitchesCauldronBlockEntity> WITCHES_CAULDRON_BLOCK_ENTITY;
    
    public static final Identifier 	WALPURGISNACHT_SOUND_1 = new Identifier("walpurgisnacht:wose_attack");
    public static SoundEvent WOSE_ATTACK = new SoundEvent(WALPURGISNACHT_SOUND_1);    
    public static final Identifier 	WALPURGISNACHT_SOUND_2 = new Identifier("walpurgisnacht:wose_die");
    public static SoundEvent WOSE_DIE = new SoundEvent(WALPURGISNACHT_SOUND_2);    
    public static final Identifier 	WALPURGISNACHT_SOUND_3 = new Identifier("walpurgisnacht:wose_hit");
    public static SoundEvent WOSE_HIT = new SoundEvent(WALPURGISNACHT_SOUND_3);   
    public static final Identifier 	WALPURGISNACHT_SOUND_4 = new Identifier("walpurgisnacht:wose_idle");
    public static SoundEvent WOSE_IDLE = new SoundEvent(WALPURGISNACHT_SOUND_4);
        
	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.
		System.out.println("If you can read this message, the mod is loading, and it didn't crash yet.");
		
        WITCHES_CAULDRON_BLOCK_ENTITY = Registry.register(Registry.BLOCK_ENTITY, "walpurgisnacht:witchescauldron", BlockEntityType.Builder.create(WitchesCauldronBlockEntity::new, WITCHES_CAULDRON).build(null));        
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "mandrake_root"), MANDRAKE_ROOT);
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "silver_sword"), SILVER_SWORD);
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "web_brew"), WEB_BREW);	
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_berries"), ROWAN_BERRIES);	

        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "mutandis_copykat"), MUTANDIS_COPYKAT);	


        
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "mandrake_seeds"), new BlockItem(MANDRAKE_ROOTS, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_log"), new BlockItem(ROWAN_LOG, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "alder_log"), new BlockItem(ALDER_LOG, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_planks"), new BlockItem(ROWAN_PLANKS, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "alder_leaves"), new BlockItem(ALDER_LEAVES, new Item.Settings().group(WITCHES_SABBATH)));        
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_leaves"), new BlockItem(ROWAN_LEAVES, new Item.Settings().group(WITCHES_SABBATH)));        
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "witches_cauldron"), new BlockItem(WITCHES_CAULDRON, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "witches_oven"), new BlockItem(WITCHES_OVEN, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_fence"), new BlockItem(ROWAN_FENCE, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "rowan_sapling"), new BlockItem(ROWAN_SAPLING, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "alder_sapling"), new BlockItem(ALDER_SAPLING, new Item.Settings().group(WITCHES_SABBATH)));
        Registry.register(Registry.ITEM, new Identifier("walpurgisnacht", "alder_tree_log"), new BlockItem(ALDER_TREE_LOG, new Item.Settings().group(WITCHES_SABBATH)));        

        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "witches_oven"), WITCHES_OVEN);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_sapling"), ROWAN_SAPLING);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "alder_sapling"), ALDER_SAPLING);


        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_log"), ROWAN_LOG);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "alder_leaves"), ALDER_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "alder_log"), ALDER_LOG);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "alder_tree_log"), ALDER_TREE_LOG);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_leaves"), ROWAN_LEAVES);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "witches_cauldron"), WITCHES_CAULDRON);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "mandrake_roots"), MANDRAKE_ROOTS);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_planks"), ROWAN_PLANKS);
        Registry.register(Registry.BLOCK, new Identifier("walpurgisnacht", "rowan_fence"), ROWAN_FENCE);
        
        Registry.register(Registry.SOUND_EVENT, WALPURGISNACHT_SOUND_1, WOSE_ATTACK);
        Registry.register(Registry.SOUND_EVENT, WALPURGISNACHT_SOUND_2, WOSE_DIE);
        Registry.register(Registry.SOUND_EVENT, WALPURGISNACHT_SOUND_3, WOSE_HIT);
        Registry.register(Registry.SOUND_EVENT, WALPURGISNACHT_SOUND_4, WOSE_IDLE);


        

	}
}
