package net.akaneo.christopherscreatures.item;

import net.akaneo.christopherscreatures.ChristophersCreatures;
import net.akaneo.christopherscreatures.entity.*;
import net.akaneo.christopherscreatures.tab.CCCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.common.ForgeSpawnEggItem;
import net.minecraftforge.fml.common.Mod;

import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

@Mod.EventBusSubscriber(modid = ChristophersCreatures.MOD_ID, bus = Mod.EventBusSubscriber.Bus.MOD)

public class CCItemRegistry {
    public static final DeferredRegister<Item> DEF_REG = DeferredRegister.create(ForgeRegistries.ITEMS, ChristophersCreatures.MOD_ID);

    public static final RegistryObject<Item> GIRAFFE_HIDE = DEF_REG.register("giraffe_hide", () -> new Item(new Item.Properties().tab(CCCreativeModeTab.TAB_CC)));

    public static final RegistryObject<Item> GIRAFFE_SPAWN_EGG = DEF_REG.register("giraffe_spawn_egg", () -> new ForgeSpawnEggItem(CCEntityRegistry.GIRAFFE,0xC9A886, 0x733F2B, new Item.Properties().tab(CCCreativeModeTab.TAB_CC)));
    public static final RegistryObject<Item> LIONESS_SPAWN_EGG = DEF_REG.register("lioness_spawn_egg", () -> new ForgeSpawnEggItem(CCEntityRegistry.LIONESS,0xE0BA8E, 0xCDA174, new Item.Properties().tab(CCCreativeModeTab.TAB_CC)));

}
