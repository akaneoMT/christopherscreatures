package net.akaneo.christopherscreatures.tab;

import net.akaneo.christopherscreatures.item.CCItemRegistry;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;

public class CCCreativeModeTab {
    public static final CreativeModeTab TAB_CC = new CreativeModeTab("tab_cc") {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(CCItemRegistry.GIRAFFE_HIDE.get());
        }
    };
}
