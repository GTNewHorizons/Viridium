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

        public float temperature = 0.8F;
        public float rainfall = 0.4f;
        public boolean rain = true;
    }

    /// Terrain
    public static final class Terrain {

        public Height height = new Height();
        public Surface surface = new Surface();
    }

    public static final class Height {

        public float baseWeight = 0.2f;
        public float heightVariation = 0.2f;
    }

    public static final class Surface {

        public String topBlockId;
        public String fillerBlockId;
        public SurfacePatch[] patches = new SurfacePatch[0];
    }

    public static final class SurfacePatch {

        public double minNoise;
        public String topBlockId;
        public String fillerBlockId;
    }

    /// Decoration
    public static final class Decoration {

        public int treesPerChunk = 0;
        public int grassPerChunk = 4;
        public int flowersPerChunk = 1;
        public Features features = new Features();
    }

    public static final class Features {

        public Blob[] blobs = new Blob[0];
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

    /// Appearance
    public static final class Appearance {

        public String mapColor;
        public String grassColor;
        public String foliageColor;
    }
}
