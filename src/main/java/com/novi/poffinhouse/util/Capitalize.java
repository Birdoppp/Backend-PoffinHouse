package com.novi.poffinhouse.util;

public class Capitalize {
    public static String getCapitalizedString(String text) {
        if (text == null || text.isEmpty()) {
            return text;
        }
        text = text.toLowerCase(); // Convert the entire string to lowercase first.
        return Character.toUpperCase(text.charAt(0)) + text.substring(1); // Capitalize the first character.
    }
}
