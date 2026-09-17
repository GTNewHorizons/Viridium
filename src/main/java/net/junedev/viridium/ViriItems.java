package net.junedev.viridium;

import net.junedev.viridium.items.SeedItem;
import net.minecraft.item.Item;

import cpw.mods.fml.common.registry.GameRegistry;

public class ViriItems {

    public void preInit() {

        americanSycamoreSeeds = new SeedItem().setUnlocalizedName("american_sycamore_seeds")
            .setTextureName("american_sycamore_seeds");
        GameRegistry.registerItem(americanSycamoreSeeds, "american_sycamore_seeds");

        baldCypressSeeds = new SeedItem().setUnlocalizedName("bald_cypress_seeds")
            .setTextureName("bald_cypress_seeds");
        GameRegistry.registerItem(baldCypressSeeds, "bald_cypress_seeds");

        baobabSeeds = new SeedItem().setUnlocalizedName("baobab_seeds")
            .setTextureName("baobab_seeds");
        GameRegistry.registerItem(baobabSeeds, "baobab_seeds");

        blackPineCone = new SeedItem().setUnlocalizedName("black_pine_cone")
            .setTextureName("black_pine_cone");
        GameRegistry.registerItem(blackPineCone, "black_pine_cone");

        dragonsBloodSeeds = new SeedItem().setUnlocalizedName("dragons_blood_seeds")
            .setTextureName("dragons_blood_seeds");
        GameRegistry.registerItem(dragonsBloodSeeds, "dragons_blood_seeds");

        royalPalmSeeds = new SeedItem().setUnlocalizedName("royal_palm_seeds")
            .setTextureName("royal_palm_seeds");
        GameRegistry.registerItem(royalPalmSeeds, "royal_palm_seeds");

        sierraJuniperBerry = new SeedItem().setUnlocalizedName("sierra_juniper_berry")
            .setTextureName("sierra_juniper_berry");
        GameRegistry.registerItem(sierraJuniperBerry, "sierra_juniper_seeds");

        sweetgumSeedPod = new SeedItem().setUnlocalizedName("sweetgum_seed_pod")
            .setTextureName("sweetgum_seed_pod");
        GameRegistry.registerItem(sweetgumSeedPod, "sweetgum_seed_pod");

        weepingWillowSeeds = new SeedItem().setUnlocalizedName("weeping_willow_seeds")
            .setTextureName("weeping_willow_seeds");
        GameRegistry.registerItem(weepingWillowSeeds, "weeping_willow_seeds");

    }

    public static Item americanSycamoreSeeds;
    public static Item baldCypressSeeds;
    public static Item baobabSeeds;
    public static Item blackPineCone;
    public static Item dragonsBloodSeeds;
    public static Item royalPalmSeeds;
    public static Item sierraJuniperBerry;
    public static Item sweetgumSeedPod;
    public static Item weepingWillowSeeds;
}
