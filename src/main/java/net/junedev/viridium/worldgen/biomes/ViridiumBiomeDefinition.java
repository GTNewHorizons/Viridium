package net.junedev.viridium.worldgen.biomes;

import net.minecraftforge.common.BiomeDictionary;
import net.minecraftforge.common.BiomeManager;

public class ViridiumBiomeDefinition {

    public String name = "Default";
    public Generation generation = new Generation();
    public Climate climate = new Climate();
    public Terrain terrain = new Terrain();
    public Decoration decoration = new Decoration();
    public Appearance appearance = new Appearance();

    /// Generation
    public static final class Generation {

        public int weight = 10;
        public BiomeManager.BiomeType climateType = BiomeManager.BiomeType.WARM;
        public BiomeDictionary.Type[] dictionaryTypes = new BiomeDictionary.Type[0];
    }

    /// Climate
    public static final class Climate {

        public Float temperatureCelsius;
        public Float humidityPercent;
        public boolean rain = true;
        public boolean snow = false;

        /** 0 C is the freezing boundary, vanilla forbids (0.1, 0.2), it is the snowy region */
        public float minecraftTemperature() {
            if (temperatureCelsius == null) return 0.8F;
            float converted = 0.15F + temperatureCelsius / 25.0F;
            return temperatureCelsius <= 0 ? Math.min(0.1F, converted) : Math.max(0.2F, converted);
        }

        public float minecraftRainfall() {
            return humidityPercent == null ? 0.4F : Math.clamp(humidityPercent / 100.0F, 0, 1);
        }
    }

    /// Terrain
    public static final class Terrain {

        public Height height = new Height();
        public Surface surface = new Surface();
    }

    public static final class Height {

        public float baseHeight = 0.2f;
        public float heightVariation = 0.2f;
    }

    public static final class Surface {

        public String topBlockId;
        public int topBlockMeta;
        public String fillerBlockId;
        public SurfacePatch[] patches = new SurfacePatch[0];
    }

    public static final class SurfacePatch {

        public double minNoise;
        public String topBlockId;
        public Integer topBlockMeta;
        public String fillerBlockId;
    }

    /// Decoration
    public static final class Decoration {

        public int treesPerChunk = 0;
        public int grassPerChunk = 4;
        public int flowersPerChunk = 1;
        public int deadBushPerChunk = 0;
        public int reedsPerChunk = 0;
        public int waterlilyPerChunk = 0;
        public int mushroomsPerChunk = 0;
        public int bigMushroomsPerChunk = 0;
        public int waterLakeChance = 0;
        public int melonChance = 0;
        public TreeEntry[] trees = { new TreeEntry("minecraft:oak", 1) };
        public VegetationEntry[] grass = { new VegetationEntry("minecraft:tallgrass", 1, 1) };
        public VegetationEntry[] flowers = { new VegetationEntry("minecraft:red_flower", 0, 1) };
        public Features features = new Features();
    }

    public static final class Features {

        public Blob[] blobs = new Blob[0];
        public Blob[] bushes = new Blob[0];
        public SmallPatch[] smallPatches = new SmallPatch[0];
    }

    public static final class SmallPatch {

        public String blockId;
        public int radius;
        public int frequency = 5;
    }

    public static final class Blob {

        public String blockId;
        public int radius;
        public int frequency = 5;
    }

    public static final class TreeEntry {

        public String id;
        public String variant = "default";
        public int weight;

        public TreeEntry() {}

        public TreeEntry(String id, int weight) {
            this.id = id;
            this.weight = weight;
        }
    }

    public static final class VegetationEntry {

        public String blockId;
        public int meta;
        public int weight;

        public VegetationEntry(String blockId, int meta, int weight) {
            this.blockId = blockId;
            this.meta = meta;
            this.weight = weight;
        }
    }

    /// Appearance
    public static final class Appearance {

        public String mapColor;
        public String grassColor;
        public String foliageColor;
        public String waterColor;
    }
}
