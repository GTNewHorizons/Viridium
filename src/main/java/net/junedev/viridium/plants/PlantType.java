package net.junedev.viridium.plants;

import java.util.List;
import java.util.Objects;

import net.minecraft.block.Block;

public final class PlantType<B> {

    private final String registryName;
    private final PlantForm<B> form;

    private B blocks;

    PlantType(String registryName, PlantForm<B> form) {
        this.registryName = Objects.requireNonNull(registryName, "registryName");
        this.form = Objects.requireNonNull(form, "form");
    }

    public String getRegistryName() {
        return registryName;
    }

    public PlantCategory getCategory() {
        return form.category();
    }

    public B getBlocks() {
        if (blocks == null) {
            throw new IllegalStateException(
                "Blocks of plant '" + registryName + "' were requested before they were created in preInit");
        }
        return blocks;
    }

    public List<Block> getBlocksWithItems() {
        return form.getBlocksWithItems(getBlocks());
    }

    void createBlocks() {
        if (blocks != null) {
            throw new IllegalStateException("Blocks of plant '" + registryName + "' were already created");
        }
        blocks = form.createBlocks(registryName);
    }
}
