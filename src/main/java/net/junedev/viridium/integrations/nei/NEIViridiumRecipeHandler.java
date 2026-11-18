package net.junedev.viridium.integrations.nei;

import net.junedev.viridium.blocks.IBlockScientificName;
import net.minecraft.client.gui.Gui;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

import org.lwjgl.opengl.GL11;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.ColorUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;

public class NEIViridiumRecipeHandler extends TemplateRecipeHandler {

    @Override
    public String getGuiTexture() {
        // Will not be used, but we need something!
        return "viridium:textures/gui/philosophia_botanica_gui_with_map.png";
    }

    @Override
    public String getRecipeName() {
        return "Viridium";
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if (result.getItem() instanceof ItemBlock itemBlock)
            if (itemBlock.field_150939_a instanceof IBlockScientificName smartBlock)
                arecipes.add(new ViridiumCachedRecipe(result, smartBlock.getScientificName()));
    }

    @Override
    public void drawExtras(int recipe) {
        ViridiumCachedRecipe plantLocationRecipe = (ViridiumCachedRecipe) arecipes.get(recipe);

        GuiDraw.drawString(
            EnumChatFormatting.DARK_GRAY + "" + EnumChatFormatting.ITALIC + plantLocationRecipe.scientificName,
            20,
            16,
            ColorUtils.recipeTitle.getColor(),
            false);
    }

    @Override
    public void drawBackground(int recipe) {
        ViridiumCachedRecipe plantLocationRecipe = (ViridiumCachedRecipe) arecipes.get(recipe);

        GL11.glColor4f(1, 1, 1, 1);
        GuiDraw.changeTexture(getGuiTexture());
        Gui.func_146110_a(4, 0, 0, 0, 146, 180, 146, 180);

        // TODO: Placeholder zone picker
        switch (plantLocationRecipe.scientificName.length() % 9) {
            case 0:
                GuiDraw.changeTexture("viridium:textures/gui/coastal.png");
                break;
            case 1:
                GuiDraw.changeTexture("viridium:textures/gui/forest.png");
                break;
            case 2:
                GuiDraw.changeTexture("viridium:textures/gui/hills.png");
                break;
            case 3:
                GuiDraw.changeTexture("viridium:textures/gui/island.png");
                break;
            case 4:
                GuiDraw.changeTexture("viridium:textures/gui/lacustrine.png");
                break;
            case 5:
                GuiDraw.changeTexture("viridium:textures/gui/montane.png");
                break;
            case 6:
                GuiDraw.changeTexture("viridium:textures/gui/open.png");
                break;
            case 7:
                GuiDraw.changeTexture("viridium:textures/gui/riparian.png");
                break;
            case 8:
                GuiDraw.changeTexture("viridium:textures/gui/wetland.png");
                break;
            default:
                break;
        }
        Gui.func_146110_a(4, 0, 0, 0, 146, 180, 146, 180);
    }

    class ViridiumCachedRecipe extends CachedRecipe {

        ItemStack input;
        String scientificName;

        ViridiumCachedRecipe(ItemStack input, String scientificName) {
            this.input = input;
            this.scientificName = scientificName;
        }

        @Override
        public PositionedStack getResult() {
            return new PositionedStack(input, 70, 140);
        }
    }
}
