package net.akaneo.christopherscreatures.item;

import net.akaneo.christopherscreatures.ChristophersCreaturesMain;
import net.akaneo.christopherscreatures.entity.EntityMain;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ItemMain {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, ChristophersCreaturesMain.MOD_ID);

    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = ITEMS.register("giraffe_spawn_egg",
            () -> new ForgeSpawnEggItem(EntityMain.GIRAFFE,0xc09872, 0x653420,
                    new Item.Properties().tab(CreativeModeTab.TAB_MISC)));


    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
