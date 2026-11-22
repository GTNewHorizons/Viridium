package net.junedev.viridium.items;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemSeedFood;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.util.ForgeDirection;

public class TreeNut extends ItemSeedFood {

    public Block seedling;
    public Block soilBlock;

    public TreeNut(int hungerRestored, float satModifier, Block seedling, Block soilBlock) {
        super(hungerRestored, satModifier, seedling, soilBlock);
        this.seedling = seedling;
        this.soilBlock = soilBlock;
    }

    @Override
    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_,
        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_7_ != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, p_77648_7_, item)
            && player.canPlayerEdit(x, y + 1, z, p_77648_7_, item)) {
                if (world.getBlock(x, y, z)
                    .canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
                    world.setBlock(x, y + 1, z, this.seedling);
                    --item.stackSize;
                    return true;
                } else {
                    return false;
                }
            } else {
                return false;
            }
    }

    @Override
    public Item setTextureName(String texName) {
        this.iconString = Viridium.MOD_ID + ":foods/" + texName;
        return this;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this.seedling;
    }
}
