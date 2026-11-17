package net.junedev.viridium;

import net.junedev.viridium.blocks.vanilike.BaseRock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ViriBlocks {

    public void preInit() {
        ViriPlants.createAllBlocks();

        // Rocks
        adakite = registerRock("adakite");
        amphibolite = registerRock("amphibolite");
        andesite = registerRock("andesite");
        arkose = registerRock("arkose");
        astorite = registerRock("astorite");
        basalt = registerRock("basalt");
        black_granite = registerRock("black_granite");
        black_shale = registerRock("black_shale");
        blueschist = registerRock("blueschist");
        breccia = registerRock("breccia");
        chalcedony = registerRock("chalcedony");
        chalk = registerRock("chalk");
        charoitite = registerRock("charoitite");
        chert = registerRock("chert");
        chromitite = registerRock("chromitite");
        conglomerate = registerRock("conglomerate");
        diamictite = registerRock("diamictite");
        diorite = registerRock("diorite");
        dolerite = registerRock("dolerite");
        dolostone = registerRock("dolostone");
        eclogite = registerRock("eclogite");
        emery = registerRock("emery");
        evaporite = registerRock("evaporite");
        gabbro = registerRock("gabbro");
        geyserite = registerRock("geyserite");
        glauconite = registerRock("glauconite");
        gneiss = registerRock("gneiss");
        granitic_gneiss = registerRock("granitic_gneiss");
        granodiorite = registerRock("granodiorite");
        greenschist = registerRock("greenschist");
        greywacke = registerRock("greywacke");
        ironstone = registerRock("ironstone");
        jasper = registerRock("jasper");
        jasperoid = registerRock("jasperoid");
        kimberlite = registerRock("kimberlite");
        komatiite = registerRock("komatiite");
        lapis_lazuli = registerRock("lapis_lazuli");
        limestone = registerRock("limestone");
        marble = registerRock("marble");
        melilitolite = registerRock("melilitolite");
        metaconglomerate = registerRock("metaconglomerate");
        micaschist = registerRock("micaschist");
        migmatite = registerRock("migmatite");
        moonmilk_limestone = registerRock("moonmilk_limestone");
        mudstone = registerRock("mudstone");
        obsidian = registerRock("obsidian");
        pegmatite = registerRock("pegmatite");
        peperite = registerRock("peperite");
        peridotite = registerRock("peridotite");
        perlite = registerRock("perlite");
        phyllite = registerRock("phyllite");
        pink_granite = registerRock("pink_granite");
        pumice = registerRock("pumice");
        quartz_arenite = registerRock("quartz_arenite");
        quartzite = registerRock("quartzite");
        rapakivi = registerRock("rapakivi");
        red_granite = registerRock("red_granite");
        rhyolite = registerRock("rhyolite");
        sandstone = registerRock("sandstone");
        scoria = registerRock("scoria");
        serpentinite = registerRock("serpentinite");
        shale = registerRock("shale");
        soapstone = registerRock("soapstone");
        tufa = registerRock("tufa");
        tuff = registerRock("tuff");
        unakite = registerRock("unakite");
        variolite = registerRock("variolite");
        weathered_sandstone = registerRock("weathered_sandstone");
    }

    private Block registerRock(String name) {
        Block rocks = new BaseRock().setBlockName(name)
            .setBlockTextureName("rocks/" + name);
        GameRegistry.registerBlock(rocks, name);
        return rocks;
    }

    // Rocks
    public static Block adakite;
    public static Block amphibolite;
    public static Block andesite;
    public static Block arkose; // TODO: top texture
    public static Block astorite;
    public static Block basalt;
    public static Block black_granite;
    public static Block black_shale;
    public static Block blueschist; // TODO x
    public static Block breccia;
    public static Block chalcedony;
    public static Block chalk;
    public static Block charoitite;
    public static Block chert;
    public static Block chromitite;
    public static Block conglomerate;
    public static Block diamictite;
    public static Block diorite;
    public static Block dolerite;
    public static Block dolostone;
    public static Block eclogite;
    public static Block emery;
    public static Block evaporite;
    public static Block gabbro;
    public static Block geyserite;
    public static Block glauconite;
    public static Block gneiss;
    public static Block granitic_gneiss; // TODO x
    public static Block granodiorite;
    public static Block greenschist;
    public static Block greywacke; // TODO x
    public static Block ironstone;
    public static Block jasper;
    public static Block jasperoid;
    public static Block kimberlite;
    public static Block komatiite;
    public static Block lapis_lazuli;
    public static Block limestone; // TODO x
    public static Block listvenite;
    public static Block llanite;
    public static Block marble;
    public static Block melilitolite;
    public static Block metaconglomerate;
    public static Block micaschist;
    public static Block migmatite;
    public static Block moonmilk_limestone;
    public static Block mudstone;
    public static Block obsidian;
    public static Block pegmatite;
    public static Block peperite;
    public static Block peridotite;
    public static Block perlite;
    public static Block phyllite;
    public static Block pink_granite;
    public static Block pumice;
    public static Block quartz_arenite;
    public static Block quartzite;
    public static Block rapakivi;
    public static Block red_granite;
    public static Block rhyolite;
    public static Block sandstone; // TODO x
    public static Block scoria;
    public static Block serpentinite;
    public static Block shale;
    public static Block siltstone;
    public static Block slate;
    public static Block soapstone;
    public static Block tufa;
    public static Block tuff; // TODO x
    public static Block unakite;
    public static Block variolite;
    public static Block weathered_sandstone; // TODO x
    public static Block white_granite;

}
