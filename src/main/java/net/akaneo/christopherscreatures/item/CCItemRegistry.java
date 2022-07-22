package net.akaneo.christopherscreatures.item;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.entity.CCEntityRegistry;
import net.akaneo.christopherscreatures.tab.CCCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class CCItemRegistry {
    public static final DeferredRegister<Item> DEF_REG = DeferredRegister.create(ForgeRegistries.ITEMS, ChristophersCreatures.MOD_ID);

    public static final RegistryObject<Item> GIRAFFE_HIDE = DEF_REG.register("giraffe_hide", () -> new Item(new Item.Properties().tab(CCCreativeModeTab.TAB_CC)));

    public static void registerItem(RegistryEvent.Register<Item> event) {
        event.getRegistry().register(new ForgeSpawnEggItem(CCEntityRegistry.GIRAFFE, 0xc09872, 0x653420, new Item.Properties().tab(CCCreativeModeTab.TAB_CC)).setRegistryName("cristopherscreatures:giraffe_spawn_egg"));
    }
}
