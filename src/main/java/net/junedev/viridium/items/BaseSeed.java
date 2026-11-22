package net.junedev.viridium.items;

import net.junedev.viridium.Viridium;
import net.minecraft.block.Block;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.world.IBlockAccess;
import net.minecraft.world.World;
import net.minecraftforge.common.EnumPlantType;
import net.minecraftforge.common.IPlantable;
import net.minecraftforge.common.util.ForgeDirection;

public class BaseSeed extends Item implements IPlantable {

    public Block seedlingBlock;
    public Block soilBlock;

    public BaseSeed(Block cropBlock, Block soilBlock) {
        super();
        this.setCreativeTab(Viridium.VTab);
        this.seedlingBlock = cropBlock;
        this.soilBlock = soilBlock;
    }

    public boolean onItemUse(ItemStack item, EntityPlayer player, World world, int x, int y, int z, int p_77648_7_,
        float p_77648_8_, float p_77648_9_, float p_77648_10_) {
        if (p_77648_7_ != 1) {
            return false;
        } else if (player.canPlayerEdit(x, y, z, p_77648_7_, item)
            && player.canPlayerEdit(x, y + 1, z, p_77648_7_, item)) {
                if (world.getBlock(x, y, z)
                    .canSustainPlant(world, x, y, z, ForgeDirection.UP, this) && world.isAirBlock(x, y + 1, z)) {
                    world.setBlock(x, y + 1, z, this.seedlingBlock);
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
        this.iconString = Viridium.MOD_ID + ":seeds/" + texName;
        return this;
    }

    @Override
    public EnumPlantType getPlantType(IBlockAccess world, int x, int y, int z) {
        return EnumPlantType.Plains;
    }

    @Override
    public Block getPlant(IBlockAccess world, int x, int y, int z) {
        return this.seedlingBlock;
    }

    @Override
    public int getPlantMetadata(IBlockAccess world, int x, int y, int z) {
        return 0;
    }
}
