package net.junedev.viridium.plants;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public final class PlantRegistry {

    private static final Map<String, PlantType<?>> plantsByRegistryName = new LinkedHashMap<>();

    private PlantRegistry() {}

    public static <B> PlantType<B> register(String registryName, PlantForm<B> form) {
        if (plantsByRegistryName.containsKey(registryName)) {
            throw new IllegalArgumentException("Plant '" + registryName + "' is registered twice");
        }
        PlantType<B> plant = new PlantType<>(registryName, form);
        plantsByRegistryName.put(registryName, plant);
        return plant;
    }

    public static void createAllBlocks() {
        for (PlantType<?> plant : plantsByRegistryName.values()) {
            plant.createBlocks();
        }
    }

    public static Collection<PlantType<?>> getAll() {
        return Collections.unmodifiableCollection(plantsByRegistryName.values());
    }

    public static List<PlantType<?>> getByCategory(PlantCategory category) {
        List<PlantType<?>> plantsInCategory = new ArrayList<>();
        for (PlantType<?> plant : plantsByRegistryName.values()) {
            if (plant.getCategory() == category) {
                plantsInCategory.add(plant);
            }
        }
        return plantsInCategory;
    }

    public static PlantType<?> get(String registryName) {
        return plantsByRegistryName.get(registryName);
    }
}
