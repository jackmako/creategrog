package net.makozort.creategrog.reg;

import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.content.item.BeerMug;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateGrog.MODID);



    public static final DeferredItem<Item> BEER_MUG_EMPTY = ITEMS.register("beer_mug_empty",
            () -> new Item(new Item.Properties().stacksTo(64)));


    public static void reg (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }

}
