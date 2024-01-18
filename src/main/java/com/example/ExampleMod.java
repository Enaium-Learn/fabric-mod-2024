package com.example;

import com.example.armor.EndArmorMaterial;
import com.example.block.EndHeartBlock;
import com.example.enchantment.FireBoomEnchantment;
import com.example.fluid.EndWaterFluid;
import com.example.item.EndHeartItem;
import com.example.tool.EndToolMaterial;
import net.fabricmc.api.ModInitializer;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.fabricmc.fabric.api.client.render.fluid.v1.FluidRenderHandlerRegistry;
import net.fabricmc.fabric.api.client.render.fluid.v1.SimpleFluidRenderHandler;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.*;
import net.minecraft.block.enums.Instrument;
import net.minecraft.client.render.block.FluidRenderer;
import net.minecraft.enchantment.Enchantment;
import net.minecraft.enchantment.EnchantmentTarget;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.fluid.FlowableFluid;
import net.minecraft.item.*;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.intprovider.UniformIntProvider;
import net.minecraft.world.biome.Biome;
import net.minecraft.world.gen.GenerationStep;
import net.minecraft.world.gen.feature.PlacedFeature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ExampleMod implements ModInitializer {
    public static final Logger LOGGER = LoggerFactory.getLogger("awesome");
    public static final Item END_HEART =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_heart"),
                    new EndHeartItem(new FabricItemSettings()));

    private static final Block END_HEART_BLOCK =
            Registry.register(Registries.BLOCK, new Identifier("awesome", "end_heart_block"),
                    new EndHeartBlock(FabricBlockSettings.create().strength(4.0f)));

    private static final BlockItem END_HEART_BLOCK_ITEM =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_heart_block"),
                    new BlockItem(END_HEART_BLOCK, new FabricItemSettings()));

    private static final Item END_HELMET =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_helmet"),
                    new ArmorItem(EndArmorMaterial.END, ArmorItem.Type.HELMET, new FabricItemSettings()));
    private static final Item END_CHESTPLATE =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_chestplate"),
                    new ArmorItem(EndArmorMaterial.END, ArmorItem.Type.CHESTPLATE, new FabricItemSettings()));
    private static final Item END_LEGGINGS =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_leggings"),
                    new ArmorItem(EndArmorMaterial.END, ArmorItem.Type.LEGGINGS, new FabricItemSettings()));
    private static final Item END_BOOTS =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_boots"),
                    new ArmorItem(EndArmorMaterial.END, ArmorItem.Type.BOOTS, new FabricItemSettings()));

    private static final Item END_SWORD =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_sword"),
                    new SwordItem(EndToolMaterial.END, 4, 0f, new FabricItemSettings()));

    private static final Block END_HEART_ORE =
            Registry.register(Registries.BLOCK, new Identifier("awesome", "end_heart_ore"),
                    new ExperienceDroppingBlock(UniformIntProvider.create(10, 17),
                            AbstractBlock.Settings.create().mapColor(MapColor.STONE_GRAY)
                                    .instrument(Instrument.BASEDRUM).requiresTool()
                                    .strength(3.0f, 3.0f)
                                    .sounds(BlockSoundGroup.NETHER_ORE)));
    private static final Block DEEPSLATE_END_HEART_ORE =
            Registry.register(Registries.BLOCK, new Identifier("awesome", "deepslate_end_heart_ore"),
                    new ExperienceDroppingBlock(UniformIntProvider.create(10, 17),
                            AbstractBlock.Settings.copy(END_HEART_ORE).mapColor(MapColor.STONE_GRAY)
                                    .instrument(Instrument.BASEDRUM).requiresTool()
                                    .strength(4.5f, 3.0f)
                                    .sounds(BlockSoundGroup.DEEPSLATE)));

    private static final RegistryKey<PlacedFeature> END_HEART_ORE_PLACED_FEATURE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("awesome", "end_heart_ore"));

    public static final Item END_HEART_ORE_ITEM = Registry.register(Registries.ITEM, new Identifier("awesome", "end_heart_ore"), new BlockItem(END_HEART_ORE, new FabricItemSettings()));
    public static final Item DEEPSLATE_END_HEART_ORE_ITEM = Registry.register(Registries.ITEM, new Identifier("awesome", "deepslate_end_heart_ore"), new BlockItem(DEEPSLATE_END_HEART_ORE, new FabricItemSettings()));

    private static final FireBoomEnchantment FIRE_BOOM_ENCHANTMENT =
            Registry.register(Registries.ENCHANTMENT, new Identifier("awesome", "fire_boom"),
                    new FireBoomEnchantment(Enchantment.Rarity.VERY_RARE, EnchantmentTarget.WEAPON, new EquipmentSlot[]{
                            EquipmentSlot.MAINHAND
                    }));

    public static final FlowableFluid STILL_END_WATER =
            Registry.register(Registries.FLUID, new Identifier("awesome", "end_water"),
                    new EndWaterFluid.Still());

    public static final FlowableFluid FLOWING_END_WATER =
            Registry.register(Registries.FLUID, new Identifier("awesome", "flowing_end_water"),
                    new EndWaterFluid.Flowing());

    public static final Item END_WATER_BUCKET =
            Registry.register(Registries.ITEM, new Identifier("awesome", "end_water_bucket"),
                    new BucketItem(STILL_END_WATER, new FabricItemSettings().recipeRemainder(Items.BUCKET).maxCount(1)));

    public static final Block END_WATER =
            Registry.register(Registries.BLOCK, new Identifier("awesome", "end_water"),
                    new FluidBlock(STILL_END_WATER, FabricBlockSettings.copy(Blocks.WATER)));

    public static final RegistryKey<PlacedFeature> END_WATER_PLACED_FEATURE =
            RegistryKey.of(RegistryKeys.PLACED_FEATURE, new Identifier("awesome", "end_water"));

    private static final ItemGroup ITEM_GROUP
            = Registry.register(Registries.ITEM_GROUP, new Identifier("awesome", "item_group"),
            FabricItemGroup.builder()
                    .icon(() -> new ItemStack(END_HEART))
                    .displayName(Text.translatable("itemGroup.awesome.item_group"))
                    .entries((content, entries) -> {
                        entries.add(END_HEART);
                        entries.add(END_HEART_BLOCK);
                        entries.add(END_HELMET);
                        entries.add(END_CHESTPLATE);
                        entries.add(END_LEGGINGS);
                        entries.add(END_BOOTS);
                        entries.add(END_SWORD);
                        entries.add(END_HEART_ORE);
                        entries.add(DEEPSLATE_END_HEART_ORE);
                        entries.add(END_WATER_BUCKET);
                    })
                    .build());

    @Override
    public void onInitialize() {
        LOGGER.info("Hello Fabric world!");
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.UNDERGROUND_ORES,
                END_HEART_ORE_PLACED_FEATURE);

        FluidRenderHandlerRegistry.INSTANCE.register(STILL_END_WATER, FLOWING_END_WATER,
                new SimpleFluidRenderHandler(
                        new Identifier("minecraft:block/water_still"),
                        new Identifier("minecraft:block/water_flow"),
                        0x730D95
                ));
        BiomeModifications.addFeature(BiomeSelectors.foundInOverworld(),
                GenerationStep.Feature.FLUID_SPRINGS, END_WATER_PLACED_FEATURE);
    }
}