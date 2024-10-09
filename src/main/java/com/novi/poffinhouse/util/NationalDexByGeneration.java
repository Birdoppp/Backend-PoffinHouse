package com.novi.poffinhouse.util;

import lombok.Getter;

@Getter
public enum NationalDexByGeneration {
    GENERATION_I(1L, 151L),
    GENERATION_II(152L, 251L),
    GENERATION_III(252L, 386L),
    GENERATION_IV(387L, 493L),
    GENERATION_V(494L, 649L),
    GENERATION_VI(650L, 721L),
    GENERATION_VII(722L, 809L),
    GENERATION_VIII(810L, 905L),
    GENERATION_IX(906L, 1025L);

    private final Long startIndex;
    private final Long maxIndex;

    NationalDexByGeneration(Long startIndex, Long maxIndex) {
        this.startIndex = startIndex;
        this.maxIndex = maxIndex;
    }

    public static Long getStartIndexByGeneration(Integer generation) {
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

    public static Long getMaxIndexByGeneration(Integer generation) {
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