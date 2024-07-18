package com.novi.poffinhouse.util;

public class Capitalize {
    public static String getCapitalizedString(String text) {
         text = text.toLowerCase();
        return Character.toUpperCase(text.charAt(0)) + text.substring(1);
    }
}
