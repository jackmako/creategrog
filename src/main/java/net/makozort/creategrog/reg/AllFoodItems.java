package net.makozort.creategrog.reg;

import net.makozort.creategrog.CreateGrog;
import net.makozort.creategrog.content.item.BeerMug;
import net.minecraft.world.item.Item;
import net.neoforged.bus.api.IEventBus;
import net.neoforged.neoforge.registries.DeferredItem;
import net.neoforged.neoforge.registries.DeferredRegister;

public class AllFoodItems {
    public static final DeferredRegister.Items ITEMS = DeferredRegister.createItems(CreateGrog.MODID);

    // this is here to make sure that it inits after the item it needs to transform into

    public static final DeferredItem<Item> BEER_MUG_FULL = ITEMS.register("beer_mug_full",
            () -> new BeerMug(new Item.Properties().food(AllFoodProperties.BEER_MUG).stacksTo(1)));


    public static void reg (IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
