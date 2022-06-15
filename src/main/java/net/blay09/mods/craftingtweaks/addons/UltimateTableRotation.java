package net.blay09.mods.craftingtweaks.addons;

import net.blay09.mods.craftingtweaks.api.RotationHandler;

public class UltimateTableRotation implements RotationHandler {

    private static final int[][] COUNTER_CLOCKWISE = new int[][]{
            {9,  0,  1,  2,  3,  4,  5,  6,  7},
            {18, 19, 10, 11, 12, 13, 14, 15, 8},
            {27, 28, 29, 20, 21, 22, 23, 16, 17},
            {36, 37, 38, 39, 30, 31, 24, 25, 26},
            {45, 46, 47, 48, 40, 32, 33, 34, 35},
            {54, 55, 56, 49, 50, 41, 42, 43, 44},
            {63, 64, 57, 58, 59, 60, 51, 52, 53},
            {72, 65, 66, 67, 68, 69, 70, 61, 62},
            {73, 74, 75, 76, 77, 78, 79, 80, 71},
    };

    private static final int[][] CLOCKWISE = new int[][]{
            {1,  2,  3,  4,  5,  6,  7,  8,  17},
            {0,  11, 12, 13, 14, 15, 16, 25, 26},
            {9,  10, 21, 22, 23, 24, 33, 34, 35},
            {18, 19, 20, 31, 32, 41, 42, 43, 44},
            {27, 28, 29, 30, 40, 50, 51, 52, 53},
            {36, 37, 38, 39, 48, 49, 60, 61, 62},
            {45, 46, 47, 56, 57, 58, 59, 70, 71},
            {54, 55, 64, 65, 66, 67, 68, 69, 80},
            {63, 72, 73, 74, 75, 76, 77, 78, 79},
    };

    private static final int WIDTH = 9;

    @Override
    public boolean ignoreSlotId(int slotId) {
        return slotId == 40;
    }

    @Override
    public int rotateSlotId(int slotId, boolean counterClockwise) {
        int[][] rotation = counterClockwise ? COUNTER_CLOCKWISE : CLOCKWISE;
        return rotation[slotId / WIDTH][slotId % WIDTH];
    }
}
