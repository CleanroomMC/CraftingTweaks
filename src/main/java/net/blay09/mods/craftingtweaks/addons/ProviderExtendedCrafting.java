package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
import net.blay09.mods.craftingtweaks.api.RotationHandler;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;

import java.util.List;

public class ProviderExtendedCrafting implements TweakProvider<Container> {

    private final DefaultProviderV2 defaultProvider = CraftingTweaksAPI.createDefaultProviderV2();
    private final RotationHandler rotationHandler;
    private final int gridSize;

    public ProviderExtendedCrafting(RotationHandler rotationHandler, int gridSize) {
        this.rotationHandler = rotationHandler;
        this.gridSize = gridSize;
    }

    @Override
    public String getModId() {
        return CraftingTweaksAddons.MODID_EXC;
    }

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public int getCraftingGridStart(EntityPlayer entityPlayer, Container container, int id) {
        return 1;
    }

    @Override
    public int getCraftingGridSize(EntityPlayer entityPlayer, Container container, int id) {
        return gridSize;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int id, boolean forced) {
        defaultProvider.clearGrid(this, id, entityPlayer, container, false, forced);
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id, boolean counterClockwise) {
        defaultProvider.rotateGrid(this, id, entityPlayer, container, rotationHandler, counterClockwise);
    }

    @Override
    public void balanceGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.balanceGrid(this, id, entityPlayer, container);
    }

    @Override
    public void spreadGrid(EntityPlayer entityPlayer, Container container, int id) {
        defaultProvider.spreadGrid(this, id, entityPlayer, container);
    }

    @Override
    public boolean canTransferFrom(EntityPlayer entityPlayer, Container container, int id, Slot slot) {
        return defaultProvider.canTransferFrom(entityPlayer, container, slot);
    }

    @Override
    public boolean transferIntoGrid(EntityPlayer entityPlayer, Container container, int id, Slot slot) {
        return defaultProvider.transferIntoGrid(this, id, entityPlayer, container, slot);
    }

    @Override
    public ItemStack putIntoGrid(EntityPlayer entityPlayer, Container container, int id, ItemStack itemStack, int slotNumber) {
        return defaultProvider.putIntoGrid(this, id, entityPlayer, container, itemStack, slotNumber);
    }

    @Override
    public IInventory getCraftMatrix(EntityPlayer entityPlayer, Container container, int id) {
        return container.inventorySlots.get(getCraftingGridStart(entityPlayer, container, id)).inventory;
    }

    @Override
    public void initGui(GuiContainer guiContainer, List<GuiButton> list) {
        Slot firstSlot = guiContainer.inventorySlots.inventorySlots.get(getCraftingGridStart(FMLClientHandler.instance().getClientPlayerEntity(), guiContainer.inventorySlots, 0));
        int startX = firstSlot.xPos - 19;
        int startY = firstSlot.yPos;
        list.add(CraftingTweaksAPI.createRotateButtonRelative(0, guiContainer, startX, startY));
        list.add(CraftingTweaksAPI.createBalanceButtonRelative(0, guiContainer, startX, startY + 18));
        list.add(CraftingTweaksAPI.createClearButtonRelative(0, guiContainer, startX, startY + 36));
    }
}
