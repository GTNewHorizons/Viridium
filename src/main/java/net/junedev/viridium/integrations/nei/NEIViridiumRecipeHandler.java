package net.junedev.viridium.integrations.nei;

import codechicken.lib.gui.GuiDraw;
import codechicken.nei.ColorUtils;
import codechicken.nei.PositionedStack;
import codechicken.nei.recipe.TemplateRecipeHandler;
import net.junedev.viridium.blocks.IBlockScientificName;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.EnumChatFormatting;

public class NEIViridiumRecipeHandler extends TemplateRecipeHandler {
    @Override
    public String getGuiTexture() {
        // Will not be used, but we need something!
        return "minecraft:textures/gui/container/crafting_table.png";
    }

    @Override
    public String getRecipeName() {
        return "Viridium";
    }

    @Override
    public void loadCraftingRecipes(ItemStack result) {
        if(result.getItem() instanceof ItemBlock itemBlock)
            if(itemBlock.field_150939_a instanceof IBlockScientificName smartBlock)
                arecipes.add(new ViridiumCachedRecipe(result, smartBlock.getScientificName()));
    }

    @Override
    public void drawExtras(int recipe) {
        ViridiumCachedRecipe plantLocationRecipe = (ViridiumCachedRecipe) arecipes.get(recipe);

        GuiDraw.drawString(EnumChatFormatting.ITALIC + plantLocationRecipe.scientificName, 2, 4, ColorUtils.recipeTitle.getColor(), true);
    }

    @Override
    public void drawBackground(int recipe) {
    }

    class ViridiumCachedRecipe extends CachedRecipe{

        ItemStack input;
        String scientificName;

        ViridiumCachedRecipe(ItemStack input, String scientificName){
            this.input = input;
            this.scientificName = scientificName;
        }

        @Override
        public PositionedStack getResult() {
            return new PositionedStack(input, 0, 20);
        }
    }
}
