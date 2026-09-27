package net.junedev.viridium.utils.parser;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;

public final class BlockParser {

    public static Block getBlock(String id) {
        if (id == null || id.trim()
            .isEmpty()) {
            Viridium.LOGGER.warn("Block id must not be empty");
            return null;
        }

        Block block = Block.getBlockFromName(id);

        if (block == null) {
            Viridium.LOGGER.warn("Unknown block id: '{}'", id);
            return null;
        }

        return block;
    }

    public static Block getBlock(String id, Block fallback) {
        Block result = getBlock(id);
        if (result == null) return fallback;
        return result;
    }
}
