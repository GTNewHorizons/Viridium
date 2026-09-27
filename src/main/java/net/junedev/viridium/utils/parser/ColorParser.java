package net.junedev.viridium.utils.parser;

import net.junedev.viridium.Viridium;

public final class ColorParser {

    /** Parses a six-digit RGB color written as {@code #RRGGBB} or {@code 0xRRGGBB}. */
    public static Integer parseRgb(String value) {
        if (value == null) {
            Viridium.LOGGER.warn("Color must not be null");
            return null;
        }

        String hexadecimal = value.trim();

        if (hexadecimal.startsWith("#")) {
            hexadecimal = hexadecimal.substring(1);
        } else if (hexadecimal.startsWith("0x") || hexadecimal.startsWith("0X")) {
            hexadecimal = hexadecimal.substring(2);
        }

        if (!hexadecimal.matches("[0-9a-fA-F]{6}")) {
            Viridium.LOGGER.warn("Expected an RGB color in #RRGGBB format, got '{}'", value);
            return null;
        }

        return Integer.parseInt(hexadecimal, 16);
    }
}
