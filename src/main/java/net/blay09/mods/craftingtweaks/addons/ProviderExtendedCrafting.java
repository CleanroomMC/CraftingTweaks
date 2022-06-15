package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.RotationHandler;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;

public class ProviderExtendedCrafting extends BasicAddonProvider {

    private final RotationHandler rotationHandler;
    private final int gridSize;

    public ProviderExtendedCrafting(RotationHandler rotationHandler, int gridSize) {
        this(CraftingTweaksAddons.MODID_EXC, rotationHandler, gridSize);
    }

    public ProviderExtendedCrafting(String modid, RotationHandler rotationHandler, int gridSize) {
        super(modid);
        this.rotationHandler = rotationHandler;
        this.gridSize = gridSize;
    }

    @Override
    public int getCraftingGridSize(EntityPlayer entityPlayer, Container container, int id) {
        return gridSize;
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id, boolean counterClockwise) {
        defaultProvider.rotateGrid(this, id, entityPlayer, container, rotationHandler, counterClockwise);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int id) {
        return container.inventorySlots.get(getCraftingGridStart(entityPlayer, container, id)).inventory;
    }
}
