package net.makozort.creategrog.reg;


import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.content.block.BeerMugBlock;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.block.*;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredBlock;
import net.neoforged.neoforge.registries.DeferredRegister;

import java.util.function.Supplier;

public class Allblocks {
    public static final DeferredRegister.Blocks BLOCKS =
            DeferredRegister.createBlocks(CreateGrog.MODID);

    public static final DeferredBlock<Block> BEER_MUG = registerBlock("beer_mug_block",
            () -> new BeerMugBlock(BlockBehaviour.Properties.of().noOcclusion()));


    private static <T extends Block> DeferredBlock<T> registerBlock(String name, Supplier<T> block) {
        DeferredBlock<T> toReturn = BLOCKS.register(name, block);
        return toReturn;
    }



    private static <T extends Block> void registerBlockItem(String name, DeferredBlock<T> block) {
        AllItems.ITEMS.register(name, () -> new BlockItem(block.get(), new Item.Properties()));
    }

    public static void reg(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}