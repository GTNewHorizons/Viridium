package net.junedev.viridium.worldgen.biomes;

import net.junedev.viridium.utils.parser.BlockParser;
import net.junedev.viridium.worldgen.features.WorldGenBush;
import net.junedev.viridium.worldgen.features.WorldGenSmallPatch;
import net.minecraft.init.Blocks;
import net.minecraft.world.biome.BiomeDecorator;
import net.minecraft.world.biome.BiomeGenBase;
import net.minecraft.world.gen.feature.WorldGenBlockBlob;
import net.minecraftforge.event.terraingen.DecorateBiomeEvent;
import net.minecraftforge.event.terraingen.TerrainGen;

public class ViridiumBiomeDecorator extends BiomeDecorator {

    public ViridiumBiomeDefinition.SmallPatch[] smallPatches = new ViridiumBiomeDefinition.SmallPatch[0];
    public ViridiumBiomeDefinition.Blob[] blobs = new ViridiumBiomeDefinition.Blob[0];
    public ViridiumBiomeDefinition.Blob[] bushes = new ViridiumBiomeDefinition.Blob[0];

    @Override
    protected void genDecorations(BiomeGenBase biomeGenBase) {
        super.genDecorations(biomeGenBase);

        if (!TerrainGen
            .decorate(currentWorld, randomGenerator, chunk_X, chunk_Z, DecorateBiomeEvent.Decorate.EventType.CUSTOM)) {
            return;
        }

        for (ViridiumBiomeDefinition.SmallPatch patch : smallPatches) {
            if (randomGenerator.nextInt(patch.frequency) == 0) {
                int x = chunk_X + randomGenerator.nextInt(16) + 8;
                int z = chunk_Z + randomGenerator.nextInt(16) + 8;
                int y = currentWorld.getHeightValue(x, z);

                new WorldGenSmallPatch(BlockParser.getBlock(patch.blockId, Blocks.beacon), patch.radius)
                    .generate(currentWorld, randomGenerator, x, y, z);
            }
        }

        for (ViridiumBiomeDefinition.Blob blob : blobs) {
            if (randomGenerator.nextInt(blob.frequency) == 0) {
                int x = chunk_X + randomGenerator.nextInt(16) + 8;
                int z = chunk_Z + randomGenerator.nextInt(16) + 8;
                int y = currentWorld.getHeightValue(x, z);

                new WorldGenBlockBlob(BlockParser.getBlock(blob.blockId, Blocks.diamond_block), blob.radius)
                    .generate(currentWorld, randomGenerator, x, y, z);
            }
        }

        for (ViridiumBiomeDefinition.Blob bush : bushes) {
            if (randomGenerator.nextInt(bush.frequency) == 0) {
                int x = chunk_X + randomGenerator.nextInt(16) + 8;
                int z = chunk_Z + randomGenerator.nextInt(16) + 8;
                int y = currentWorld.getHeightValue(x, z);

                new WorldGenBush(BlockParser.getBlock(bush.blockId, Blocks.brick_block), bush.radius)
                    .generate(currentWorld, randomGenerator, x, y, z);
            }
        }
    }
}
