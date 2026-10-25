package net.junedev.viridium.blocks;

import java.util.Random;

import net.junedev.viridium.worldgen.CoconutPalmTreeGen;
import net.minecraft.block.Block;
import net.minecraft.block.BlockBush;
import net.minecraft.init.Blocks;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraft.world.gen.feature.WorldGenerator;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class CoconutPalmSaplingBlock extends BaseSaplingBlock {

    public CoconutPalmSaplingBlock(Block trunkWoodBlock, Block leafBlock) {
        super(trunkWoodBlock, leafBlock);
    }

    @Override
    public void func_149878_d(World world, int x, int y, int z, Random rand) {
        if (!net.minecraftforge.event.terraingen.TerrainGen.saplingGrowTree(world, rand, x, y, z)) return;
        world.setBlock(x, y, z, Blocks.air);
        WorldGenerator obj;
        obj = new CoconutPalmTreeGen(true, this.leafBlock, this.trunkWoodBlock);
        if (!(obj.generate(world, rand, x, y, z))) world.setBlock(x, y, z, this);
    }

    @Override
    protected boolean canPlaceBlockOn(Block ground) {
        return ground == Blocks.grass || ground == Blocks.dirt || ground == Blocks.farmland || ground == Blocks.sand;
    }

    @Override
    public boolean canSustainPlant(IBlockAccess world, int x, int y, int z, ForgeDirection direction,
        IPlantable plantable) {

        if (plantable instanceof BlockBush && this.canPlaceBlockOn(this)) {
            return true;
        }

        return false;
    }
}
