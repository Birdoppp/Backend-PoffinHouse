package com.novi.poffinhouse.util;

import lombok.Getter;

import static com.novi.poffinhouse.util.Capitalize.getCapitalizedString;

public class PreferencesEnum {
    public enum Flavor {
        SPICY,
        DRY,
        SWEET,
        BITTER,
        SOUR,
        NONE
    }

    @Getter
    public enum PokemonNature {
        HARDY(Flavor.NONE, Flavor.NONE),
        LONELY(Flavor.SPICY, Flavor.SOUR),
        BRAVE(Flavor.SPICY, Flavor.SWEET),
        ADAMANT(Flavor.SPICY, Flavor.DRY),
        NAUGHTY(Flavor.SPICY, Flavor.BITTER),
        BOLD(Flavor.SOUR, Flavor.SPICY),
        DOCILE(Flavor.NONE, Flavor.NONE),
        RELAXED(Flavor.SOUR, Flavor.SWEET),
        IMPISH(Flavor.SOUR, Flavor.DRY),
        LAX(Flavor.SOUR, Flavor.BITTER),
        TIMID(Flavor.SWEET, Flavor.SPICY),
        HASTY(Flavor.SWEET, Flavor.SOUR),
        SERIOUS(Flavor.NONE, Flavor.NONE),
        JOLLY(Flavor.SWEET, Flavor.DRY),
        NAIVE(Flavor.SWEET, Flavor.BITTER),
        MODEST(Flavor.DRY, Flavor.SPICY),
        MILD(Flavor.DRY, Flavor.SOUR),
        QUIET(Flavor.DRY, Flavor.SWEET),
        BASHFUL(Flavor.NONE, Flavor.NONE),
        RASH(Flavor.DRY, Flavor.BITTER),
        CALM(Flavor.BITTER, Flavor.SPICY),
        GENTLE(Flavor.BITTER, Flavor.SOUR),
        SASSY(Flavor.BITTER, Flavor.SWEET),
        CAREFUL(Flavor.BITTER, Flavor.DRY),
        QUIRKY(Flavor.NONE, Flavor.NONE);

        private final Flavor favorite;
        private final Flavor dislikes;

        PokemonNature(Flavor favorite, Flavor dislikes) {
            this.favorite = favorite;
            this.dislikes = dislikes;
        }

        public static void main(String[] args) {
            for (PokemonNature nature : PokemonNature.values()) {
                System.out.println("A Pokemon with a "+ getCapitalizedString(nature.name()) + " nature likes " + getCapitalizedString(nature.getFavorite().name()) + " berries and dislikes " + getCapitalizedString(nature.getDislikes().name()) + "berries.");
            }
        }
    }
}
