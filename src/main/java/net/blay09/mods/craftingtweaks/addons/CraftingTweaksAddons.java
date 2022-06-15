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

    protected static final String MODID_EXC = "extendedcrafting";

    public static void postInit(FMLPostInitializationEvent event) {
        if(Loader.isModLoaded("storagesilo")) {
            registerProvider("uk.binarycraft.storagesilo.blocks.craftingsilo.ContainerCraftingSilo", new ProviderCraftingSilo());
        }

        if (Loader.isModLoaded(MODID_EXC)) {
            extendedCrafting();
        }
    }

    private static void extendedCrafting() {
        registerSimpleProvider(MODID_EXC, "com.blakebr0.extendedcrafting.client.container.ContainerBasicTable");
        registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerAdvancedTable", new ProviderExtendedCrafting(new AdvancedTableRotation(), 25));
        registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerEliteTable", new ProviderExtendedCrafting(new EliteTableRotation(), 49));
        registerProvider("com.blakebr0.extendedcrafting.client.container.ContainerUltimateTable", new ProviderExtendedCrafting(new UltimateTableRotation(), 81));
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
