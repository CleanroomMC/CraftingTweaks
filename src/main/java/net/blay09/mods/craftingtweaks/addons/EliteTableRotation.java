package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.RotationHandler;

public class EliteTableRotation implements RotationHandler {

    private static final int[][] COUNTER_CLOCKWISE = new int[][]{
            {7,  0,  1,  2,  3,  4,  5},
            {14, 15, 8,  9,  10, 11, 6},
            {21, 22, 23, 16, 17, 12, 13},
            {28, 29, 30, 24, 18, 19, 20},
            {35, 36, 31, 32, 25, 26, 27},
            {42, 37, 38, 39, 40, 33, 34},
            {43, 44, 45, 46, 47, 48, 41},
    };

    private static final int[][] CLOCKWISE = new int[][]{
            {1,  2,  3,  4,  5,  6,  13},
            {0,  9,  10, 11, 12, 19, 20},
            {7,  8,  17, 18, 25, 26, 27},
            {14, 15, 16, 24, 32, 33, 34},
            {21, 22, 23, 30, 31, 40, 41},
            {28, 29, 36, 37, 38, 39, 48},
            {35, 42, 43, 44, 45, 46, 47},
    };

    private static final int WIDTH = 7;

    @Override
    public boolean ignoreSlotId(int slotId) {
        return slotId == 24;
    }

    @Override
    public int rotateSlotId(int slotId, boolean counterClockwise) {
        int[][] rotation = counterClockwise ? COUNTER_CLOCKWISE : CLOCKWISE;
        return rotation[slotId / WIDTH][slotId % WIDTH];
    }
}
