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

    public static ViridiumBiomeDefinition load(File file) {
        try (Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)) {
            return readDefinition(reader, file.getAbsolutePath());
        } catch (IOException | JsonParseException exception) {
            Viridium.LOGGER.error("Could not load biome definition " + file.getAbsolutePath(), exception);
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
                return readDefinition(reader, path);
            }
        } catch (IOException | JsonParseException exception) {
            Viridium.LOGGER.error("Could not read biome JSON: " + path, exception);
            return null;
        }
    }

    static ViridiumBiomeDefinition readDefinition(Reader reader, String source) {
        ViridiumBiomeDefinition definition = GSON.fromJson(reader, ViridiumBiomeDefinition.class);

        if (validateDefinition(definition, source)) return definition;

        throw new RuntimeException(
            "This code should not be reached, something went horribly wrong when validating the biome definitions.");
    }

    static boolean validateDefinition(ViridiumBiomeDefinition definition, String source) {
        if (definition == null || definition.name == null
            || definition.name.trim()
                .isEmpty()) {
            throw new JsonParseException(source + ": biome definition has no name");
        }
        // Gson maps unknown enum strings to null. Reject them before registration mutates biome IDs.
        if (definition.generation == null || definition.generation.climateType == null) {
            throw new JsonParseException(
                source + ": generation.climateType must be DESERT, WARM, COOL or ICY (Forge 1.7.10)");
        }
        if (definition.generation.dictionaryTypes == null) {
            throw new JsonParseException(source + ": generation.dictionaryTypes must be an array");
        }
        for (Object type : definition.generation.dictionaryTypes) {
            if (type == null) {
                throw new JsonParseException(source + ": generation.dictionaryTypes contains an unknown or null type");
            }
        }

        return true;
    }
}
