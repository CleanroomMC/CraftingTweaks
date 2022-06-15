# Crafting Tweaks

A continuation of CraftingTweaks intended to fix bugs and add much more mod compatibility

## IMC API
Most crafting grids can be registered using the IMC API.

In order to register your container for Crafting Tweaks, send an IMC message as follows:

```java
NBTTagCompound tagCompound = new NBTTagCompound();
tagCompound.setString("ContainerClass", YourCraftingContainer.class.getName());

// tagCompound.setInteger("ValidContainerPredicate", YourContainerPredicate.class.getName()); // requires RegisterProviderV3 or higher
// tagCompound.setInteger("GetGridStartFunction", YourGridStartFunction.class.getName()); // requires RegisterProviderV3 or higher

// tagCompound.setInteger("GridSlotNumber", 1);
// tagCompound.setInteger("GridSize", 9);
// tagCompound.setBoolean("HideButtons", false);
// tagCompound.setBoolean("PhantomItems", true);

// tagCompound.setInteger("ButtonOffsetX", -16); // ignored if AlignToGrid is set
// tagCompound.setInteger("ButtonOffsetY", 16); // ignored if AlignToGrid is set
// ***** OR *****
// tagCompound.setString("AlignToGrid", "left");

// NBTTagCompound tweakRotate = new NBTTagCompound();
// tweakRotate.setBoolean("Enabled", true);
// tweakRotate.setBoolean("ShowButton", true);
// tweakRotate.setInteger("ButtonX", 0); // ignored if AlignToGrid is set
// tweakRotate.setInteger("ButtonY", 18); // ignored if AlignToGrid is set
// tagCompound.setTag("TweakRotate", tweakRotate);
// [...] (same structure for "TweakBalance" and "TweakClear")

FMLInterModComms.sendMessage("craftingtweaks", "RegisterProvider", tagCompound);
// or FMLInterModComms.sendMessage("craftingtweaks", "RegisterProviderV3", tagCompound); if using V3 features
```

The commented out lines are optional (the example above shows the default value).

The fields are described below:
* **ContainerClass**: The full class name (including package name) of your container class with the crafting grid.
* **ContainerValidPredicate**: (V3 or higher) The full class name (including package name) of an optional container callback; a class that implements Function<Container, Boolean> to determine whether a container is a valid crafting container
* **GetGridStartFunction**: (V3 or higher) The full class name (including package name) of an optional slot callback; a class t hat implements Function<Container, Integer> to determine the first slot of a crafting grid (overrides GridSlotNumber)
* **GridSlotNumber**: The slotNumber of the first slot in the crafting matrix (this is the index within Container.inventorySlots, NOT the index within the IInventory)
* **GridSize**: The size of the crafting grid (probably 9)
* **HideButtons**: If you don't want Crafting Tweak's buttons to show up (but you want the hotkeys to work), set this to true
* **PhantomItems**: If your crafting grid contains phantom items, set this to true. This will make the clear operation delete the grid instead of putting the items into the player inventory.
* **ButtonOffsetX**: X-Offset to apply to all tweak buttons, relative to the upper left corner of the GuiContainer. AlignToGrid will override this option.
* **ButtonOffsetY**: Y-Offset to apply to all tweak buttons, relative to the upper left corner of the GuiContainer. AlignToGrid will override this option.
* **AlignToGrid**: Can be "up", "down", "left" or "right". Will automatically position buttons next to the grid.
* **TweakRotate**: A tag compound containing settings for the rotate tweak (see below)
* **TweakBalance**: A tag compound containing settings for the balance tweak (see below)
* **TweakClear**: A tag compound containing settings for the clear tweak (see below)
* **Tweak...**: Contains the following settings for tweaks:
  * **Enabled**: Set this to false if this tweak should be disabled for this container
  * **ShowButton**: Set this to false if the button for this tweak should not be added
  * **ButtonX**: X-Position of the tweak button relative to ButtonOffsetX. AlignToGrid will override this option.
  * **ButtonY**: Y-Position of the tweak button relative to ButtonOffsetY. AlignToGrid will override this option.

*Note*: If you're specifying custom button positions, they should be 18 pixels apart from each other. If you simply want the buttons next to the crafting grid, use AlignToGrid. If you just want to move all buttons at once, use ButtonOffsetX/Y. The buttons are set out vertically by default.
