package com.novi.poffinhouse.util;

import lombok.Getter;

//import static com.novi.poffinhouse.util.Capitalize.getCapitalizedString;

public class PreferencesEnum {
    public enum FLAVOR {
        SPICY,
        DRY,
        SWEET,
        BITTER,
        SOUR,
        NONE
    }

    @Getter
    public enum NATURE {
        HARDY(FLAVOR.NONE, FLAVOR.NONE),
        LONELY(FLAVOR.SPICY, FLAVOR.SOUR),
        BRAVE(FLAVOR.SPICY, FLAVOR.SWEET),
        ADAMANT(FLAVOR.SPICY, FLAVOR.DRY),
        NAUGHTY(FLAVOR.SPICY, FLAVOR.BITTER),
        BOLD(FLAVOR.SOUR, FLAVOR.SPICY),
        DOCILE(FLAVOR.NONE, FLAVOR.NONE),
        RELAXED(FLAVOR.SOUR, FLAVOR.SWEET),
        IMPISH(FLAVOR.SOUR, FLAVOR.DRY),
        LAX(FLAVOR.SOUR, FLAVOR.BITTER),
        TIMID(FLAVOR.SWEET, FLAVOR.SPICY),
        HASTY(FLAVOR.SWEET, FLAVOR.SOUR),
        SERIOUS(FLAVOR.NONE, FLAVOR.NONE),
        JOLLY(FLAVOR.SWEET, FLAVOR.DRY),
        NAIVE(FLAVOR.SWEET, FLAVOR.BITTER),
        MODEST(FLAVOR.DRY, FLAVOR.SPICY),
        MILD(FLAVOR.DRY, FLAVOR.SOUR),
        QUIET(FLAVOR.DRY, FLAVOR.SWEET),
        BASHFUL(FLAVOR.NONE, FLAVOR.NONE),
        RASH(FLAVOR.DRY, FLAVOR.BITTER),
        CALM(FLAVOR.BITTER, FLAVOR.SPICY),
        GENTLE(FLAVOR.BITTER, FLAVOR.SOUR),
        SASSY(FLAVOR.BITTER, FLAVOR.SWEET),
        CAREFUL(FLAVOR.BITTER, FLAVOR.DRY),
        QUIRKY(FLAVOR.NONE, FLAVOR.NONE);

        private final FLAVOR favorite;
        private final FLAVOR dislikes;

        NATURE(FLAVOR favorite, FLAVOR dislikes) {
            this.favorite = favorite;
            this.dislikes = dislikes;
        }

//        public static void main(String[] args) {
//            for (NATURE nature : NATURE.values()) {
//                System.out.println("A Pokemon with a " + getCapitalizedString(nature.name()) + " nature likes " + getCapitalizedString(nature.getFavorite().name()) + " berries and dislikes " + getCapitalizedString(nature.getDislikes().name()) + "berries.");
//            }
//        }
    }
}
