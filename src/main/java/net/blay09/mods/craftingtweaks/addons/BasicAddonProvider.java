package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.CraftingTweaksAPI;
import net.blay09.mods.craftingtweaks.api.DefaultProviderV2;
import net.blay09.mods.craftingtweaks.api.TweakProvider;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.inventory.GuiContainer;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.Slot;
import net.minecraft.item.ItemStack;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

public abstract class BasicAddonProvider implements TweakProvider<Container> {

    protected final DefaultProviderV2 defaultProvider = CraftingTweaksAPI.createDefaultProviderV2();

    private final String modid;

    public BasicAddonProvider(String modid) {
        this.modid = modid;
    }

    @Override
    public String getModId() {
        return modid;
    }

    @Override
    public boolean load() {
        return true;
    }

    @Override
    public void clearGrid(EntityPlayer entityPlayer, Container container, int id, boolean forced) {
        defaultProvider.clearGrid(this, id, entityPlayer, container, false, forced);
    }

    @Override
    public void rotateGrid(EntityPlayer entityPlayer, Container container, int id, boolean counterClockwise) {
        defaultProvider.rotateGrid(this, id, entityPlayer, container, counterClockwise);
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
    @SideOnly(Side.CLIENT)
    public void initGui(GuiContainer guiContainer, List<GuiButton> list) {
        Slot firstSlot = guiContainer.inventorySlots.inventorySlots.get(getCraftingGridStart(FMLClientHandler.instance().getClientPlayerEntity(), guiContainer.inventorySlots, 0));
        int startX = firstSlot.xPos - 19;
        int startY = firstSlot.yPos;
        list.add(CraftingTweaksAPI.createRotateButtonRelative(0, guiContainer, startX, startY));
        list.add(CraftingTweaksAPI.createBalanceButtonRelative(0, guiContainer, startX, startY + 18));
        list.add(CraftingTweaksAPI.createClearButtonRelative(0, guiContainer, startX, startY + 36));
    }
}
