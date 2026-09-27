package net.junedev.viridium.blocks;

import net.junedev.viridium.Viridium;
import net.minecraft.block.BlockLog;
import net.minecraft.client.renderer.texture.IIconRegister;
import net.minecraft.util.IIcon;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class BaseFullLog extends BlockLog {

    @SideOnly(Side.CLIENT)
    private IIcon topIcon;

    @SideOnly(Side.CLIENT)
    private IIcon sideIcon;

    public BaseFullLog() {
        super();
        this.setHardness(1.0F); // Hardness of vanilla logs?
        this.setCreativeTab(Viridium.VTab);
        this.setStepSound(soundTypeWood);
    }

    @Override
    @SideOnly(Side.CLIENT)
    public void registerBlockIcons(IIconRegister register) {
        topIcon = register.registerIcon(getTextureName() + "_top");
        sideIcon = register.registerIcon(getTextureName() + "_side");
    }

    @Override
    @SideOnly(Side.CLIENT)
    public IIcon getIcon(int side, int meta) {
        int orientation = meta & 12;

        switch (orientation) {
            case 0:
                if (side == 1 || side == 0) return this.getTopIcon();
                break;
            case 4:
                if (side == 5 || side == 4) return this.getTopIcon();
                break;
            case 8:
                if (side == 2 || side == 3) return this.getTopIcon();
                break;
        }
        return this.getSideIcon();
    }

    @Override
    public BlockLog setBlockTextureName(String textureName) {
        super.setBlockTextureName(Viridium.MOD_ID + ":" + textureName);
        return this;
    }

    // Getters
    public IIcon getTopIcon() {
        return topIcon;
    }

    public IIcon getSideIcon() {
        return sideIcon;
    }
}
