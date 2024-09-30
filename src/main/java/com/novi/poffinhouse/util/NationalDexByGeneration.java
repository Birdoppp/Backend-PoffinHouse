package com.novi.poffinhouse.util;

import lombok.Getter;

@Getter
public enum NationalDexByGeneration {
    GENERATION_I(1, 151),
    GENERATION_II(152, 251),
    GENERATION_III(252, 386),
    GENERATION_IV(387, 493),
    GENERATION_V(494, 649),
    GENERATION_VI(650, 721),
    GENERATION_VII(722, 809),
    GENERATION_VIII(810, 905),
    GENERATION_IX(906, 1025);

    private final int startIndex;
    private final int maxIndex;

    NationalDexByGeneration(int startIndex, int maxIndex) {
        this.startIndex = startIndex;
        this.maxIndex = maxIndex;
    }

    public static int getStartIndexByGeneration(int generation) {
        return switch (generation) {
            case 1 -> GENERATION_I.getStartIndex();
            case 2 -> GENERATION_II.getStartIndex();
            case 3 -> GENERATION_III.getStartIndex();
            case 4 -> GENERATION_IV.getStartIndex();
            case 5 -> GENERATION_V.getStartIndex();
            case 6 -> GENERATION_VI.getStartIndex();
            case 7 -> GENERATION_VII.getStartIndex();
            case 8 -> GENERATION_VIII.getStartIndex();
            case 9 -> GENERATION_IX.getStartIndex();
            default -> throw new IllegalArgumentException("Invalid generation: " + generation);
        };
    }

    public static int getMaxIndexByGeneration(int generation) {
        return switch (generation) {
            case 1 -> GENERATION_I.getMaxIndex();
            case 2 -> GENERATION_II.getMaxIndex();
            case 3 -> GENERATION_III.getMaxIndex();
            case 4 -> GENERATION_IV.getMaxIndex();
            case 5 -> GENERATION_V.getMaxIndex();
            case 6 -> GENERATION_VI.getMaxIndex();
            case 7 -> GENERATION_VII.getMaxIndex();
            case 8 -> GENERATION_VIII.getMaxIndex();
            case 9 -> GENERATION_IX.getMaxIndex();
            default -> throw new IllegalArgumentException("Invalid generation: " + generation);
        };
    }
}