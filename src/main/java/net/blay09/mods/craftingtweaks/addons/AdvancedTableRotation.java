package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.RotationHandler;

public class AdvancedTableRotation implements RotationHandler {

    private static final int[][] COUNTER_CLOCKWISE = new int[][]{
            {5,  0,  1,  2,  3},
            {10, 11, 6,  7,  4},
            {15, 16, 12, 8,  9},
            {20, 17, 18, 13, 14},
            {21, 22, 23, 24, 19},
    };

    private static final int[][] CLOCKWISE = new int[][]{
            {1,  2,  3,  4,  9},
            {0,  7,  8,  13, 14},
            {5,  6,  12, 18, 19},
            {10, 11, 16, 17, 24},
            {15, 20, 21, 22, 23},
    };

    private static final int WIDTH = 5;

    @Override
    public boolean ignoreSlotId(int slotId) {
        return slotId == 12;
    }

    @Override
    public int rotateSlotId(int slotId, boolean counterClockwise) {
        int[][] rotation = counterClockwise ? COUNTER_CLOCKWISE : CLOCKWISE;
        return rotation[slotId / WIDTH][slotId % WIDTH];
    }
}
