package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.SimpleTweakProvider;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.inventory.Container;
import net.minecraftforge.fml.common.Loader;
import net.minecraftforge.fml.common.event.FMLPostInitializationEvent;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.annotation.Nullable;

public class CraftingTweaksAddons {

    public static final Logger logger = LogManager.getLogger();

    protected static final String MODID_EXC = "extendedcrafting",
            MODID_AVA = "avaritia",
            MODID_FOR = "forestry";

    public static void postInit(FMLPostInitializationEvent event) {
        // Extended Crafting: Basic, Advanced, Elite, and Ultimate Tables
        if (Loader.isModLoaded(MODID_EXC)) {
            registerSimpleProvider(MODID_EXC, "com.blakebr0.extendedcrafting.client.container.ContainerBasicTable");
            registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerAdvancedTable", new ProviderExtendedCrafting(new AdvancedTableRotation(), 25));
            registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerEliteTable", new ProviderExtendedCrafting(new EliteTableRotation(), 49));
            registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerUltimateTable", new ProviderExtendedCrafting(new UltimateTableRotation(), 81));
        }

        // Avaritia: Extreme Crafting Table
        if (Loader.isModLoaded(MODID_AVA)) {
            registerProvider("morph.avaritia.container.ContainerExtremeCrafting", new ProviderExtendedCrafting(MODID_AVA, new UltimateTableRotation(), 81));
        }

        // Forestry: Workbench
        if (Loader.isModLoaded(MODID_FOR)) {
            SimpleTweakProvider<?> provider = registerSimpleProvider(MODID_FOR, "forestry.worktable.gui.ContainerWorktable");
            if (provider != null) {
                provider.setPhantomItems(true);
                provider.setGrid(54, 9);
                provider.setTweakRotate(true, true, -16, 20);
                provider.setTweakBalance(true, true, -16, 38);
                provider.setTweakClear(true, true, -16, 56);
            }
        }
    }

    @SuppressWarnings("unchecked")
    @Nullable
    private static SimpleTweakProvider<?> registerSimpleProvider(String modid, String className) {
        try {
            return CraftingTweaksAPI.registerSimpleProvider(modid, (Class<? extends Container>) Class.forName(className));
        } catch (ClassNotFoundException e) {
            logger.error("Could not register Crafting Tweaks addon for {} - internal names have changed.", modid);
        }
        return null;
    }

    @SuppressWarnings("unchecked")
    private static <T extends Container> void registerProvider(String className, TweakProvider<T> provider) {
        try {
            CraftingTweaksAPI.registerProvider((Class<T>) Class.forName(className), provider);
        } catch (ClassNotFoundException e) {
            logger.error("Could not register Crafting Tweaks addon for {} - internal names have changed.", provider.getModId());
        }
    }
}
