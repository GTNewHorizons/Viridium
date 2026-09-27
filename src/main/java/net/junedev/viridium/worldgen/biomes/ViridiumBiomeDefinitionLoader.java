package net.junedev.viridium.worldgen.biomes;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;

import net.junedev.viridium.Viridium;

import com.google.gson.Gson;
import com.google.gson.JsonParseException;

public final class ViridiumBiomeDefinitionLoader {

    private static final Gson GSON = new Gson();

    // TODO: Add checks when loading!

    public static ViridiumBiomeDefinition load(File file) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {

            ViridiumBiomeDefinition definition = GSON.fromJson(reader, ViridiumBiomeDefinition.class);

            if (definition == null || definition.name == null || definition.name.isEmpty()) {
                Viridium.LOGGER.error("Biome definition has no name: {}", file.getAbsolutePath());
                return null;
            }

            return definition;
        } catch (IOException | JsonParseException exception) {
            Viridium.LOGGER.error("Could not load biome definition {}", file.getAbsolutePath());
            return null;
        }
    }

    public static ViridiumBiomeDefinition loadBuiltin(String fileName) {
        String path = "/data/viridium/biomes/" + fileName;

        try (InputStream stream = Viridium.class.getResourceAsStream(path)) {
            if (stream == null) {
                Viridium.LOGGER.error("Stream not found for {}", path);
                return null;
            }

            try (Reader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                ViridiumBiomeDefinition definition = GSON.fromJson(reader, ViridiumBiomeDefinition.class);

                if (definition == null || definition.name == null || definition.name.isEmpty()) {
                    Viridium.LOGGER.error("Biome definition has no name: {}", path);
                    return null;
                }

                return definition;
            }
        } catch (IOException exception) {
            Viridium.LOGGER.error("Could not read biome JSON: {}", path);
            return null;
        }
    }
}
