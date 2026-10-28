package net.junedev.viridium;

import net.junedev.viridium.blocks.BaseFlora;
import net.junedev.viridium.blocks.BushBlock;
import net.junedev.viridium.blocks.FernBlock;
import net.junedev.viridium.blocks.GroundPlaneBlock;
import net.junedev.viridium.blocks.TallPlantBlock;
import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.sets.SmallTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.AmericanChestnutBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.BurntLeavesTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.PalmTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.WhitePoplarBlockSet;
import net.junedev.viridium.blocks.vanilike.BaseFullLog;
import net.junedev.viridium.blocks.vanilike.BaseLeaves;
import net.junedev.viridium.blocks.vanilike.BasePlanks;
import net.junedev.viridium.blocks.vanilike.WillowVineBlock;
import net.minecraft.block.Block;

import cpw.mods.fml.common.registry.GameRegistry;

public class ViriBlocks {

    public void preInit() {

        ainselu = registerBush("ainselu");
        bears_breeches = registerBush("bears_breeches");
        blackberry = registerBush("blackberry");
        creosote_bush = registerBush("creosote_bush");
        croton = registerBush("croton");
        elderberry = registerBush("elderberry");
        false_indigo = registerBush("false_indigo");
        golden_wattle = registerBush("golden_wattle");
        honeysuckle = registerBush("honeysuckle");
        japanese_andromeda = registerBush("japanese_andromeda");
        koki_o_ono = registerBush("koki_o_ono");
        kudzu = registerBush("kudzu");
        kutjera = registerBush("kutjera");
        ma_o_hau_hele = registerBush("ma_o_hau_hele");
        oldman_saltbush = registerBush("oldman_saltbush");
        poinsettia = registerBush("poinsettia");
        quandong = registerBush("quandong");
        raspberry = registerBush("raspberry");
        rhododendron = registerBush("rhododendron");
        rose_shrub = registerBush("rose_shrub");
        sandhill_wattle = registerBush("sandhill_wattle");
        satsuki_azalea = registerBush("satsuki_azalea");
        silverleaf_hydrangea = registerBush("silverleaf_hydrangea");
        staghorn_sumac = registerBush("staghorn_sumac");
        wedding_bush = registerBush("wedding_bush");

        black_spruce = new SmallTreeBlockSet("black_spruce");
        blackthorn = new SmallTreeBlockSet("blackthorn");
        blue_mahoe = new SmallTreeBlockSet("blue_mahoe");
        box_elder = new SmallTreeBlockSet("box_elder");
        callery_pear = new SmallTreeBlockSet("callery_pear");
        citron = new SmallTreeBlockSet("citron");
        crape_myrtle = new SmallTreeBlockSet("crape_myrtle");
        desert_bloodwood = new SmallTreeBlockSet("desert_bloodwood");
        flowering_dogwood = new SmallTreeBlockSet("flowering_dogwood");
        giant_bamboo = new SmallTreeBlockSet("giant_bamboo", 3, 4, false);
        holly = new SmallTreeBlockSet("holly");
        jacaranda = new SmallTreeBlockSet("jacaranda");
        japanese_maple = new SmallTreeBlockSet("japanese_maple");
        joshua_tree = new SmallTreeBlockSet("joshua_tree");
        koki_o_kea = new SmallTreeBlockSet("koki_o_kea");
        kumquat = new SmallTreeBlockSet("kumquat");
        lemon_myrtle = new SmallTreeBlockSet("lemon_myrtle");
        lilac = new SmallTreeBlockSet("lilac");
        mimosa = new SmallTreeBlockSet("mimosa");
        mulga = new SmallTreeBlockSet("mulga");
        olive = new SmallTreeBlockSet("olive");
        pacific_yew = new SmallTreeBlockSet("pacific_yew");
        paperbark_maple = new SmallTreeBlockSet("paperbark_maple");
        pawpaw = new SmallTreeBlockSet("pawpaw");
        pear = new SmallTreeBlockSet("pear");
        red_cinchona = new SmallTreeBlockSet("red_cinchona");
        redbud = new SmallTreeBlockSet("redbud");
        wild_apple = new SmallTreeBlockSet("wild_apple");
        witch_hazel = new SmallTreeBlockSet("witch_hazel");
        wisteria = new SmallTreeBlockSet("wisteria");

        agave_americana_leaves = registerTallPlant("agave_americana_leaves", 2, 30, 1);
        welwitschia_leaf = registerTallPlant("welwitschia_leaf", 3, 19, 22);
        corpse_flower_inflorescence = registerTallPlant("corpse_flower_inflorescence", 2, 24, 4);
        corpse_flower_leaf = registerTallPlant("corpse_flower_leaf", 2, 21, 6);
        hemp_leaf = registerTallPlant("hemp_leaf", 2, 21, 5);
        monstera_leaf_large = registerTallPlant("monstera_leaf_large", 2, 13, 9);
        belladonna_lily = registerTallPlant("belladonna_lily", 2, 15, 8);
        bethroot = registerTallPlant("bethroot", 2, 17, 9);
        blue_agave = registerTallPlant("blue_agave", 2, 29, 1);
        chicory = registerTallPlant("chicory", 2, 15, 7);
        delphinium = registerTallPlant("delphinium", 2, 15, 7);
        dogfennel = registerTallPlant("dogfennel", 2, 13, 7);
        dragon_lily = registerTallPlant("dragon_lily", 2, 22, 6);
        english_lavender = registerTallPlant("english_lavender", 2, 16, 8);
        falling_stars = registerTallPlant("falling_stars", 2, 18, 7);
        fez_aloe = registerTallPlant("fez_aloe", 2, 16, 8);
        fireweed = registerTallPlant("fireweed", 2, 6, 14);
        foxglove = registerTallPlant("foxglove", 2, 13, 9);
        garden_lupine = registerTallPlant("garden_lupine", 2, 12, 10);
        giant_airplant = registerTallPlant("giant_airplant", 2, 21, 5);
        giant_goldenrod = registerTallPlant("giant_goldenrod", 2, 14, 9);
        giant_ironweed = registerTallPlant("giant_ironweed", 2, 11, 10);
        green_pitcher_plant = registerTallPlant("green_pitcher_plant", 2, 8, 12);
        iliau_flower = registerTallPlant("iliau_flower", 2, 13, 9);
        mugwort = registerTallPlant("mugwort", 2, 16, 8);
        multiflora_rose = registerTallPlant("multiflora_rose", 2, 15, 8);
        new_zealand_calceolaria = registerTallPlant("new_zealand_calceolaria", 2, 18, 7);
        ocotillo = registerTallPlant("ocotillo", 4, 64, 1);
        ocotillo_flowering = registerTallPlant("ocotillo_flowering", 4, 64, 1);
        painted_trillium = registerTallPlant("painted_trillium", 2, 16, 7);
        parrot_feather_watermilfoil = registerTallPlant("parrot_feather_watermilfoil", 2, 16, 8);
        patuju_flower = registerTallPlant("patuju_flower", 2, 30, 1);
        purple_loosestrife = registerTallPlant("purple_loosestrife", 2, 16, 8);
        ross_lily = registerTallPlant("ross_lily", 1, 20, 6);
        royal_hakea = registerTallPlant("royal_hakea", 2, 16, 8);
        skunk_cabbage = registerTallPlant("skunk_cabbage", 1, 23, 5);
        skunk_cabbage_flowering = registerTallPlant("skunk_cabbage_flowering", 2, 23, 5);
        snapdragon = registerTallPlant("snapdragon", 2, 11, 10);
        stargazer_lily = registerTallPlant("stargazer_lily", 2, 14, 9);
        stinging_nettle = registerTallPlant("stinging_nettle", 2, 12, 10);
        sunflower = registerTallPlant("sunflower", 2, 8, 12);
        swanleaf_sage = registerTallPlant("swanleaf_sage", 1, 24, 4);
        valerian = registerTallPlant("valerian", 2, 13, 9);
        white_snakeroot = registerTallPlant("white_snakeroot", 2, 11, 11);
        woad = registerTallPlant("woad", 2, 18, 7);

        // Bushes

        // Ferns
        autumn_fern_frond = registerFern("autumn_fern_frond", 8);
        bracken_frond = registerFern("bracken_frond", 16, 40);
        christmas_fern = registerFern("christmas_fern", 16, 40);
        cinnamon_fern_frond = registerFern("cinnamon_fern_frond", 16, 30);
        deer_fern_frond = registerFern("deer_fern_frond", 8);
        harts_tongue_fern_frond = registerFern("harts_tongue_fern_frond", 8);
        horsetail = registerFern("horsetail", 16, 40);
        mt_kaala_cyanea_frond = registerFern("mt_kaala_cyanea_frond", 16, 40);
        oem_frond = registerFern("oem_frond", 32, -20, 0.2D);
        silver_fern_frond = registerFern("silver_fern_frond", 16, 40, 0.2D);
        silver_fern_koru = registerFern("silver_fern_koru", 8, 45, 0.1D);
        sword_fern = registerFern("sword_fern", 8);

        // Flora / Vegetation
        african_lily = registerFlower("african_lily");
        african_violet = registerFlower("african_violet");
        akiohala = registerFlower("akiohala");
        aloe_vera = registerFlower("aloe_vera");
        alstroemeria = registerFlower("alstroemeria");
        alumroot = registerFlower("alumroot");
        ambrette = registerFlower("ambrette");
        anise_hyssop = registerFlower("anise_hyssop");
        argentine_saltroot = registerFlower("argentine_saltroot");
        asian_bleeding_heart = registerFlower("asian_bleeding_heart");
        aster = registerFlower("aster");
        auricula = registerFlower("auricula");
        azure_bluet = registerFlower("azure_bluet");
        beebalm = registerFlower("beebalm");
        black_eyed_susan = registerFlower("black_eyed_susan");
        bog_rosemary = registerFlower("bog_rosemary");
        cape_sundew = registerFlower("cape_sundew");
        carnation = registerFlower("carnation");
        carolina_cranesbill = registerFlower("carolina_cranesbill");
        carolina_lily = registerFlower("carolina_lily");
        catnip = registerFlower("catnip");
        celadine_poppy = registerFlower("celadine_poppy");
        chamomile = registerFlower("chamomile");
        chrysanthemum = registerFlower("chrysanthemum");
        common_peony = registerFlower("common_peony");
        coralbells = registerFlower("coralbells");
        corn_poppy = registerFlower("corn_poppy");
        cornflower = registerFlower("cornflower");
        cotton_headed_snow_lotus = registerFlower("cotton_headed_snow_lotus");
        cow_parsley = registerFlower("cow_parsley");
        coyote_mint = registerFlower("coyote_mint");
        creeping_thistle = registerFlower("creeping_thistle");
        cutleaf_anemone = registerFlower("cutleaf_anemone");
        dwarf_larkspur = registerFlower("dwarf_larkspur");
        edelweiss = registerFlower("edelweiss");
        false_rue_anemone = registerFlower("false_rue_anemone");
        fawn_lily = registerFlower("fawn_lily");
        feverfew = registerFlower("feverfew");
        firewheel = registerFlower("firewheel");
        forget_me_not = registerFlower("forget_me_not");
        geranium = registerFlower("geranium");
        gladiolus_italicus = registerFlower("gladiolus_italicus");
        grape_hyacinth = registerFlower("grape_hyacinth");
        heather = registerFlower("heather");
        hedgehog_coneflower = registerFlower("hedgehog_coneflower");
        iceland_poppy = registerFlower("iceland_poppy");
        japanese_spirea = registerFlower("japanese_spirea");
        king_protea = registerFlower("king_protea");
        koki_o_ke_oke_o = registerFlower("koki_o_ke_oke_o");
        koki_o_ula = registerFlower("koki_o_ula");
        labdanum = registerFlower("labdanum");
        lemon_daylily = registerFlower("lemon_daylily");
        lenten_rose = registerFlower("lenten_rose");
        lily_of_the_valley = registerFlower("lily_of_the_valley");
        madder = registerFlower("madder");
        mandrake = registerFlower("mandrake");
        marigold = registerFlower("marigold");
        noog = registerFlower("noog");
        orange_daylily = registerFlower("orange_daylily");
        oriental_poppy = registerFlower("oriental_poppy");
        oxeye_daisy = registerFlower("oxeye_daisy");
        paperwhite = registerFlower("paperwhite");
        patchouli = registerFlower("patchouli");
        peace_lily = registerFlower("peace_lily");
        petunia = registerFlower("petunia");
        pink_rockrose = registerFlower("pink_rockrose");
        poppy_anemone = registerFlower("poppy_anemone");
        primrose = registerFlower("primrose");
        purple_heart = registerFlower("purple_heart");
        purple_morning_glory = registerFlower("purple_morning_glory");
        quinchamali = registerFlower("quinchamali");
        raupotaranga = registerFlower("raupotaranga");
        red_campion = registerFlower("red_campion");
        rocky_mountain_columbine = registerFlower("rocky_mountain_columbine");
        scarlet_globemallow = registerFlower("scarlet_globemallow");
        spear_thistle = registerFlower("spear_thistle");
        spongeplant = registerFlower("spongeplant");
        stringy_stonecrop = registerFlower("stringy_stonecrop");
        sulphur_cosmos = registerFlower("sulphur_cosmos");
        swamp_sunflower = registerFlower("swamp_sunflower");
        sweet_bobbejaantjie = registerFlower("sweet_bobbejaantjie");
        tacca_chantrieri = registerFlower("tacca_chantrieri");
        thale_cress = registerFlower("thale_cress");
        trailing_arbutus = registerFlower("trailing_arbutus");
        tritomea = registerFlower("tritomea");
        turks_cap_lily = registerFlower("turks_cap_lily");
        varileaf_cinquefoil = registerFlower("varileaf_cinquefoil");
        veined_sun_orchid = registerFlower("veined_sun_orchid");
        venus_flytrap_closed = registerFlower("venus_flytrap_closed");
        venus_flytrap_open = registerFlower("venus_flytrap_open");
        virginia_bluebells = registerFlower("virginia_bluebells");
        water_flag = registerFlower("water_flag");
        welsh_poppy = registerFlower("welsh_poppy");
        white_lupin = registerFlower("white_lupin");
        yarrow = registerFlower("yarrow");
        yellow_star_of_bethlehem = registerFlower("yellow_star_of_bethlehem");
        yerba_mansa = registerFlower("yerba_mansa");
        zinnia = registerFlower("zinnia");

        // Small Grasses
        bergbamboes = registerFlower("bergbamboes");
        cattail_rushes = registerFlower("cattail_rushes");
        daffodil_flowering = registerFlower("daffodil_flowering");
        daffodil_grass = registerFlower("daffodil_grass");
        dandelion_flowering = registerFlower("dandelion_flowering");
        dandelion_leaves = registerFlower("dandelion_leaves");
        dandelion_seeding = registerFlower("dandelion_seeding");
        dog_violet = registerFlower("dog_violet");
        dunegrass = registerFlower("dunegrass");
        goose_grass = registerFlower("goose_grass");
        kangaroo_grass = registerFlower("kangaroo_grass");
        liriope_flowering = registerFlower("liriope_flowering");
        liriope_grass = registerFlower("liriope_grass");
        manyspike_flatsedge = registerFlower("manyspike_flatsedge");
        mitchell_grass = registerFlower("mitchell_grass");
        native_millet = registerFlower("native_millet");
        needleleaf_sedge = registerFlower("needleleaf_sedge");
        resurrection_plant = registerFlower("resurrection_plant");
        siberian_squall_flowering = registerFlower("siberian_squall_flowering");
        siberian_squall_grass = registerFlower("siberian_squall_grass");
        spearmint = registerFlower("spearmint");
        spider_plant = registerFlower("spider_plant");
        strawberry_spiderhead = registerFlower("strawberry_spiderhead");
        swamp_canegrass = registerFlower("swamp_canegrass");

        // Mushrooms

        // Grass Decorators
        autumn_leaf_pile = registerGroundPlane("autumn_leaf_pile");
        clover_patch = registerGroundPlane("clover_patch_top");
        dead_leaf_pile = registerGroundPlane("dead_leaf_pile");
        leaf_pile = registerGroundPlane("leaf_pile");
        pinestraw = registerGroundPlane("pinestraw");
        snorkelwort = registerGroundPlane("snorkelwort");

        // Vines

        // Full Trees
        amaranth = new LargeTreeBlockSet("amaranth");
        american_chestnut = new AmericanChestnutBlockSet("american_chestnut");
        american_sycamore = new LargeTreeBlockSet("american_sycamore");
        ash = new LargeTreeBlockSet("ash");
        bald_cypress = new LargeTreeBlockSet("bald_cypress");
        baobab = new LargeTreeBlockSet("baobab");
        beech = new LargeTreeBlockSet("beech");
        black_oak = new LargeTreeBlockSet("black_oak");
        black_pine = new LargeTreeBlockSet("black_pine");
        cacao = new LargeTreeBlockSet("cacao");
        candlenut = new LargeTreeBlockSet("candlenut");
        coast_redwood = new BurntLeavesTreeBlockSet("coast_redwood");
        coconut_palm = new LargeTreeBlockSet("coconut_palm");
        coolibah = new LargeTreeBlockSet("coolibah");
        dahurian_larch = new LargeTreeBlockSet("dahurian_larch");
        dragons_blood = new LargeTreeBlockSet("dragons_blood");
        driftwood = new LargeWoodBlockSet("driftwood");
        ebony = new LargeTreeBlockSet("ebony");
        giant_sequoia = new BurntLeavesTreeBlockSet("giant_sequoia");
        grand_fir = new LargeTreeBlockSet("grand_fir");
        hawthorn = new LargeTreeBlockSet("hawthorn");
        kankan = new LargeTreeBlockSet("kankan");
        koa = new LargeTreeBlockSet("koa");
        mahogany = new LargeTreeBlockSet("mahogany");
        mediterranean_cypress = new LargeTreeBlockSet("mediterranean_cypress");
        nazareno = new LargeTreeBlockSet("nazareno");
        paper_birch = new LargeTreeBlockSet("paper_birch");
        pedunculate_oak = new LargeTreeBlockSet("pedunculate_oak");
        quaking_aspen = new LargeTreeBlockSet("quaking_aspen");
        rainbow_eucalyptus = new LargeTreeBlockSet("rainbow_eucalyptus");
        red_alder = new LargeTreeBlockSet("red_alder");
        red_gum = new LargeTreeBlockSet("red_gum");
        red_maple = new LargeTreeBlockSet("red_maple");
        red_spruce = new LargeTreeBlockSet("red_spruce");
        rowan = new LargeTreeBlockSet("rowan");
        royal_palm = new PalmTreeBlockSet("royal_palm");
        rubber = new LargeTreeBlockSet("rubber");
        rubberLogExpended = registerLargeLog("rubber_log_expended");
        rubberLogResinous = registerLargeLog("rubber_log_resinous");
        scots_pine = new LargeTreeBlockSet("scots_pine");
        siberian_larch = new LargeTreeBlockSet("siberian_larch");
        sierra_juniper = new LargeTreeBlockSet("sierra_juniper");
        southern_magnolia = new LargeTreeBlockSet("southern_magnolia");
        sweet_cherry = new LargeTreeBlockSet("sweet_cherry");
        sweetgum = new LargeTreeBlockSet("sweetgum");
        tamarack = new LargeTreeBlockSet("tamarack");
        turkish_pine = new LargeTreeBlockSet("turkish_pine");
        umbrella_tree = new LargeTreeBlockSet("umbrella_tree");
        weeping_willow = new LargeTreeBlockSet("weeping_willow");
        Block weepingWillowVines = new WillowVineBlock().setBlockName("weeping_willow_vines");
        GameRegistry.registerBlock(weepingWillowVines, "weeping_willow_vines");
        white_poplar = new WhitePoplarBlockSet("white_poplar");
        yew = new LargeTreeBlockSet("yew");
    }

    // Bushes
    private Block registerBush(String name) {
        Block bush = new BushBlock().setBlockName(name)
            .setBlockTextureName("bushes/" + name);
        GameRegistry.registerBlock(bush, name);
        return bush;
    }

    // Full Trees
    private Block registerLargeLog(String name) {
        Block log = new BaseFullLog().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(log, name);
        return log;
    }

    private Block registerLeaves(String name) {
        Block leaves = new BaseLeaves().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(leaves, name);
        return leaves;
    }

    private Block registerPlanks(String name) {
        Block planks = new BasePlanks().setBlockName(name)
            .setBlockTextureName("large_trees/" + name);
        GameRegistry.registerBlock(planks, name);
        return planks;
    }

    // Standalone Grasses
    private Block registerTallPlant(String name, int size, int pixelWidth) {
        Block tallPlant = new TallPlantBlock(size, pixelWidth).setBlockName(name);
        GameRegistry.registerBlock(tallPlant, name);
        return tallPlant;
    }

    private Block registerTallPlant(String name, int size, int pixelWidth, int textureX) {
        Block tallPlant = new TallPlantBlock(size, pixelWidth, textureX).setBlockName(name)
            .setBlockTextureName("flowers_tall/" + name);
        GameRegistry.registerBlock(tallPlant, name);
        return tallPlant;
    }

    private Block registerTallPlant(String name, int size) {
        return registerTallPlant(name, size, 16);
    }

    private Block registerFern(String name, int halfSize) {
        return registerFern(name, halfSize, 45, 0.0);
    }

    private Block registerFern(String name, int halfSize, double openingAngle) {
        return registerFern(name, halfSize, openingAngle, 0.0);
    }

    private Block registerFern(String name, int halfSize, double openingAngle, double minimumRadius) {
        Block fern = new FernBlock(halfSize, openingAngle, minimumRadius).setBlockName(name)
            .setBlockTextureName("ferns/" + name);
        GameRegistry.registerBlock(fern, name);
        return fern;
    }

    // Standalone Flora
    private Block registerFlower(String name) {
        Block flora = new BaseFlora().setBlockName(name)
            .setBlockTextureName("flowers/" + name);
        GameRegistry.registerBlock(flora, name);
        return flora;
    }

    // Mushrooms

    // Grass Decorators
    private Block registerGroundPlane(String name) {
        Block groundPlane = new GroundPlaneBlock().setBlockName(name)
            .setBlockTextureName("ground/" + name);
        GameRegistry.registerBlock(groundPlane, name);
        return groundPlane;
    }

    // Vines

    // Bushes
    public static Block ainselu;
    public static Block bears_breeches;
    public static Block blackberry;
    public static Block creosote_bush;
    public static Block croton;
    public static Block elderberry;
    public static Block false_indigo;
    public static Block golden_wattle;
    public static Block honeysuckle;
    public static Block japanese_andromeda;
    public static Block koki_o_ono;
    public static Block kudzu;
    public static Block kutjera;
    public static Block ma_o_hau_hele;
    public static Block oldman_saltbush;
    public static Block poinsettia;
    public static Block quandong;
    public static Block raspberry;
    public static Block rhododendron;
    public static Block rose_shrub;
    public static Block sandhill_wattle;
    public static Block satsuki_azalea;
    public static Block silverleaf_hydrangea;
    public static Block staghorn_sumac;
    public static Block wedding_bush;

    public static SmallTreeBlockSet black_spruce;
    public static SmallTreeBlockSet blackthorn;
    public static SmallTreeBlockSet blue_mahoe;
    public static SmallTreeBlockSet box_elder;
    public static SmallTreeBlockSet callery_pear;
    public static SmallTreeBlockSet citron;
    public static SmallTreeBlockSet crape_myrtle;
    public static SmallTreeBlockSet desert_bloodwood;
    public static SmallTreeBlockSet flowering_dogwood;
    public static SmallTreeBlockSet giant_bamboo;
    public static SmallTreeBlockSet holly;
    public static SmallTreeBlockSet jacaranda;
    public static SmallTreeBlockSet japanese_maple;
    public static SmallTreeBlockSet joshua_tree;
    public static SmallTreeBlockSet koki_o_kea;
    public static SmallTreeBlockSet kumquat;
    public static SmallTreeBlockSet lemon_myrtle;
    public static SmallTreeBlockSet lilac;
    public static SmallTreeBlockSet mimosa;
    public static SmallTreeBlockSet mulga;
    public static SmallTreeBlockSet olive;
    public static SmallTreeBlockSet pacific_yew;
    public static SmallTreeBlockSet paperbark_maple;
    public static SmallTreeBlockSet pawpaw;
    public static SmallTreeBlockSet pear;
    public static SmallTreeBlockSet red_cinchona;
    public static SmallTreeBlockSet redbud;
    public static SmallTreeBlockSet wild_apple;
    public static SmallTreeBlockSet witch_hazel;
    public static SmallTreeBlockSet wisteria;

    public static Block autumn_fern_frond;
    public static Block bracken_frond;
    public static Block christmas_fern;
    public static Block cinnamon_fern_frond;
    public static Block deer_fern_frond;
    public static Block harts_tongue_fern_frond;
    public static Block horsetail;
    public static Block mt_kaala_cyanea_frond;
    public static Block oem_frond;
    public static Block silver_fern_frond;
    public static Block silver_fern_koru;
    public static Block sword_fern;

    public static Block welwitschia_leaf;
    public static Block corpse_flower_leaf;
    public static Block corpse_flower_inflorescence;
    public static Block hemp_leaf;
    public static Block monstera_leaf_large;
    public static Block agave_americana_leaves;
    public static Block belladonna_lily;
    public static Block bethroot;
    public static Block blue_agave;
    public static Block chicory;
    public static Block delphinium;
    public static Block dogfennel;
    public static Block dragon_lily;
    public static Block english_lavender;
    public static Block falling_stars;
    public static Block fez_aloe;
    public static Block fireweed;
    public static Block foxglove;
    public static Block garden_lupine;
    public static Block giant_airplant;
    public static Block giant_goldenrod;
    public static Block giant_ironweed;
    public static Block green_pitcher_plant;
    public static Block iliau_flower;
    public static Block mugwort;
    public static Block multiflora_rose;
    public static Block new_zealand_calceolaria;
    public static Block ocotillo;
    public static Block ocotillo_flowering;
    public static Block painted_trillium;
    public static Block parrot_feather_watermilfoil;
    public static Block patuju_flower;
    public static Block purple_loosestrife;
    public static Block ragweed;
    public static Block ross_lily;
    public static Block royal_hakea;
    public static Block sandhill_canegrass;
    public static Block skunk_cabbage;
    public static Block skunk_cabbage_flowering;
    public static Block snapdragon;
    public static Block stargazer_lily;
    public static Block stinging_nettle;
    public static Block sunflower;
    public static Block swanleaf_sage;
    public static Block valerian;
    public static Block white_snakeroot;
    public static Block woad;

    // Grass Decorators
    public static Block autumn_leaf_pile;
    public static Block clover_patch;
    public static Block dead_leaf_pile;
    public static Block leaf_pile;
    public static Block pinestraw;
    public static Block snorkelwort;

    // small flora TODO: all commented out plants are tall or large flora, put these in a separate folder
    public static Block african_lily;
    public static Block african_violet;
    public static Block akiohala;
    public static Block aloe_vera;
    public static Block alstroemeria;
    public static Block alumroot;
    public static Block ambrette;
    public static Block anise_hyssop;
    public static Block argentine_saltroot;
    public static Block asian_bleeding_heart;
    public static Block aster;
    public static Block auricula;
    public static Block azure_bluet;
    public static Block beebalm;
    public static Block black_eyed_susan;
    public static Block bog_rosemary;
    public static Block cape_sundew;
    public static Block carnation;
    public static Block carolina_cranesbill;
    public static Block carolina_lily;
    public static Block catnip;
    public static Block celadine_poppy;
    public static Block chamomile;
    public static Block chrysanthemum;
    public static Block common_peony;
    public static Block coralbells;
    public static Block corn_poppy;
    public static Block cornflower;
    public static Block cotton_headed_snow_lotus;
    public static Block cow_parsley;
    public static Block coyote_mint;
    public static Block creeping_thistle;
    public static Block cutleaf_anemone;
    public static Block dwarf_larkspur;
    public static Block edelweiss;
    public static Block false_rue_anemone;
    public static Block fawn_lily;
    public static Block feverfew;
    public static Block firewheel;
    public static Block forget_me_not;
    public static Block geranium;
    public static Block gladiolus_italicus;
    public static Block grape_hyacinth;
    public static Block heather;
    public static Block hedgehog_coneflower;
    public static Block iceland_poppy;
    public static Block japanese_spirea;
    public static Block king_protea;
    public static Block koki_o_ke_oke_o;
    public static Block koki_o_ula;
    public static Block labdanum;
    public static Block lemon_daylily;
    public static Block lenten_rose;
    public static Block lily_of_the_valley;
    public static Block madder;
    public static Block mandrake;
    public static Block marigold;
    public static Block noog;
    public static Block orange_daylily;
    public static Block oriental_poppy;
    public static Block oxeye_daisy;
    public static Block paperwhite;
    public static Block patchouli;
    public static Block peace_lily;
    public static Block petunia;
    public static Block pink_rockrose;
    public static Block poppy_anemone;
    public static Block primrose;
    public static Block purple_heart;
    public static Block purple_morning_glory;
    public static Block quinchamali;
    public static Block raupotaranga;
    public static Block red_campion;
    public static Block rocky_mountain_columbine;
    public static Block scarlet_globemallow;
    public static Block spear_thistle;
    public static Block spongeplant;
    public static Block stringy_stonecrop;
    public static Block sulphur_cosmos;
    public static Block swamp_sunflower;
    public static Block sweet_bobbejaantjie;
    public static Block tacca_chantrieri;
    public static Block thale_cress;
    public static Block trailing_arbutus;
    public static Block tritomea;
    public static Block turks_cap_lily;
    public static Block varileaf_cinquefoil;
    public static Block veined_sun_orchid;
    public static Block venus_flytrap_open;
    public static Block venus_flytrap_closed;
    public static Block virginia_bluebells;
    public static Block water_flag;
    public static Block welsh_poppy;
    public static Block white_lupin;
    public static Block yarrow;
    public static Block yellow_star_of_bethlehem;
    public static Block yerba_mansa;
    public static Block zinnia;

    // Small Grasses
    public static Block bergbamboes;
    public static Block cattail_rushes;
    public static Block daffodil_flowering;
    public static Block daffodil_grass;
    public static Block dandelion_flowering;
    public static Block dandelion_seeding;
    public static Block dandelion_leaves;
    public static Block dog_violet;
    public static Block dunegrass;
    public static Block goose_grass;
    public static Block kangaroo_grass;
    public static Block liriope_flowering;
    public static Block liriope_grass;
    public static Block manyspike_flatsedge;
    public static Block mitchell_grass;
    public static Block native_millet;
    public static Block needleleaf_sedge;
    public static Block resurrection_plant;
    public static Block siberian_squall_flowering;
    public static Block siberian_squall_grass;
    public static Block spearmint;
    public static Block spider_plant;
    public static Block strawberry_spiderhead;
    public static Block swamp_canegrass;

    // Large Trees
    public static LargeTreeBlockSet amaranth;
    public static AmericanChestnutBlockSet american_chestnut;
    public static LargeTreeBlockSet american_sycamore;
    public static LargeTreeBlockSet ash;
    public static LargeTreeBlockSet bald_cypress;
    public static LargeTreeBlockSet baobab;
    public static LargeTreeBlockSet beech;
    public static LargeTreeBlockSet black_oak;
    public static LargeTreeBlockSet black_pine;
    public static LargeTreeBlockSet cacao;
    public static LargeTreeBlockSet candlenut;
    public static BurntLeavesTreeBlockSet coast_redwood;
    public static LargeTreeBlockSet coconut_palm;
    public static LargeTreeBlockSet coolibah;
    public static LargeTreeBlockSet dahurian_larch;
    public static LargeTreeBlockSet dragons_blood;
    public static LargeWoodBlockSet driftwood;
    public static LargeTreeBlockSet ebony;
    public static BurntLeavesTreeBlockSet giant_sequoia;
    public static LargeTreeBlockSet grand_fir;
    public static LargeTreeBlockSet hawthorn;
    public static LargeTreeBlockSet kankan;
    public static LargeTreeBlockSet koa;
    public static LargeTreeBlockSet mahogany;
    public static LargeTreeBlockSet mediterranean_cypress;
    public static LargeTreeBlockSet nazareno;
    public static LargeTreeBlockSet paper_birch;
    public static LargeTreeBlockSet pedunculate_oak;
    public static LargeTreeBlockSet quaking_aspen;
    public static LargeTreeBlockSet rainbow_eucalyptus;
    public static LargeTreeBlockSet red_alder;
    public static LargeTreeBlockSet red_gum;
    public static LargeTreeBlockSet red_maple;
    public static LargeTreeBlockSet red_spruce;
    public static LargeTreeBlockSet rowan;
    public static PalmTreeBlockSet royal_palm;
    public static LargeTreeBlockSet rubber;
    public static Block rubberLogExpended;
    public static Block rubberLogResinous;
    public static LargeTreeBlockSet scots_pine;
    public static LargeTreeBlockSet siberian_larch;
    public static LargeTreeBlockSet sierra_juniper;
    public static LargeTreeBlockSet southern_magnolia;
    public static LargeTreeBlockSet sweet_cherry;
    public static LargeTreeBlockSet sweetgum;
    public static LargeTreeBlockSet tamarack;
    public static LargeTreeBlockSet turkish_pine;
    public static LargeTreeBlockSet umbrella_tree;
    public static LargeTreeBlockSet weeping_willow;
    public static WhitePoplarBlockSet white_poplar;
    public static LargeTreeBlockSet yew;

}
