package net.junedev.viridium;

import net.junedev.viridium.blocks.sets.LargeWoodBlockSet;
import net.junedev.viridium.blocks.sets.SmallTreeBlockSet;
import net.junedev.viridium.blocks.sets.WoodBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.AmericanChestnutBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.BurntLeavesTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.LargeTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.PalmTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.RubberTreeBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.WeepingWillowBlockSet;
import net.junedev.viridium.blocks.sets.large_tree.WhitePoplarBlockSet;
import net.junedev.viridium.plants.PlantCategory;
import net.junedev.viridium.plants.PlantRegistry;
import net.junedev.viridium.plants.PlantType;
import net.junedev.viridium.plants.forms.BushForm;
import net.junedev.viridium.plants.forms.FernForm;
import net.junedev.viridium.plants.forms.FlowerForm;
import net.junedev.viridium.plants.forms.GroundCoverForm;
import net.junedev.viridium.plants.forms.SmallGrassForm;
import net.junedev.viridium.plants.forms.TallPlantForm;
import net.junedev.viridium.plants.forms.WoodSetForm;
import net.minecraft.block.Block;

import java.util.function.Function;

public final class ViriPlants {

    private ViriPlants() {}

    public static void createAllBlocks() {
        PlantRegistry.createAllBlocks();
    }

    // ===== Bushes =====
    public static final PlantType<Block> AINSELU = bush("ainselu");
    public static final PlantType<Block> BEARS_BREECHES = bush("bears_breeches");
    public static final PlantType<Block> BLACKBERRY = bush("blackberry");
    public static final PlantType<Block> CREOSOTE_BUSH = bush("creosote_bush");
    public static final PlantType<Block> CROTON = bush("croton");
    public static final PlantType<Block> ELDERBERRY = bush("elderberry");
    public static final PlantType<Block> FALSE_INDIGO = bush("false_indigo");
    public static final PlantType<Block> GOLDEN_WATTLE = bush("golden_wattle");
    public static final PlantType<Block> HONEYSUCKLE = bush("honeysuckle");
    public static final PlantType<Block> JAPANESE_ANDROMEDA = bush("japanese_andromeda");
    public static final PlantType<Block> KOKI_O_ONO = bush("koki_o_ono");
    public static final PlantType<Block> KUDZU = bush("kudzu");
    public static final PlantType<Block> KUTJERA = bush("kutjera");
    public static final PlantType<Block> MA_O_HAU_HELE = bush("ma_o_hau_hele");
    public static final PlantType<Block> OLDMAN_SALTBUSH = bush("oldman_saltbush");
    public static final PlantType<Block> POINSETTIA = bush("poinsettia");
    public static final PlantType<Block> QUANDONG = bush("quandong");
    public static final PlantType<Block> RASPBERRY = bush("raspberry");
    public static final PlantType<Block> RHODODENDRON = bush("rhododendron");
    public static final PlantType<Block> ROSE_SHRUB = bush("rose_shrub");
    public static final PlantType<Block> SANDHILL_WATTLE = bush("sandhill_wattle");
    public static final PlantType<Block> SATSUKI_AZALEA = bush("satsuki_azalea");
    public static final PlantType<Block> SILVERLEAF_HYDRANGEA = bush("silverleaf_hydrangea");
    public static final PlantType<Block> STAGHORN_SUMAC = bush("staghorn_sumac");
    public static final PlantType<Block> WEDDING_BUSH = bush("wedding_bush");

    // ===== Small Trees =====
    public static final PlantType<SmallTreeBlockSet> BLACK_SPRUCE = smallTree("black_spruce");
    public static final PlantType<SmallTreeBlockSet> BLACKTHORN = smallTree("blackthorn");
    public static final PlantType<SmallTreeBlockSet> BLUE_MAHOE = smallTree("blue_mahoe");
    public static final PlantType<SmallTreeBlockSet> BOX_ELDER = smallTree("box_elder");
    public static final PlantType<SmallTreeBlockSet> CALLERY_PEAR = smallTree("callery_pear");
    public static final PlantType<SmallTreeBlockSet> CITRON = smallTree("citron");
    public static final PlantType<SmallTreeBlockSet> CRAPE_MYRTLE = smallTree("crape_myrtle");
    public static final PlantType<SmallTreeBlockSet> DESERT_BLOODWOOD = smallTree("desert_bloodwood");
    public static final PlantType<SmallTreeBlockSet> FLOWERING_DOGWOOD = smallTree("flowering_dogwood");
    public static final PlantType<SmallTreeBlockSet> GIANT_BAMBOO = smallTree("giant_bamboo", 3, 4, false);
    public static final PlantType<SmallTreeBlockSet> HOLLY = smallTree("holly");
    public static final PlantType<SmallTreeBlockSet> JACARANDA = smallTree("jacaranda");
    public static final PlantType<SmallTreeBlockSet> JAPANESE_MAPLE = smallTree("japanese_maple");
    public static final PlantType<SmallTreeBlockSet> JOSHUA_TREE = smallTree("joshua_tree");
    public static final PlantType<SmallTreeBlockSet> KOKI_O_KEA = smallTree("koki_o_kea");
    public static final PlantType<SmallTreeBlockSet> KUMQUAT = smallTree("kumquat");
    public static final PlantType<SmallTreeBlockSet> LEMON_MYRTLE = smallTree("lemon_myrtle");
    public static final PlantType<SmallTreeBlockSet> LILAC = smallTree("lilac");
    public static final PlantType<SmallTreeBlockSet> MIMOSA = smallTree("mimosa");
    public static final PlantType<SmallTreeBlockSet> MULGA = smallTree("mulga");
    public static final PlantType<SmallTreeBlockSet> OLIVE = smallTree("olive");
    public static final PlantType<SmallTreeBlockSet> PACIFIC_YEW = smallTree("pacific_yew");
    public static final PlantType<SmallTreeBlockSet> PAPERBARK_MAPLE = smallTree("paperbark_maple");
    public static final PlantType<SmallTreeBlockSet> PAWPAW = smallTree("pawpaw");
    public static final PlantType<SmallTreeBlockSet> PEAR = smallTree("pear");
    public static final PlantType<SmallTreeBlockSet> RED_CINCHONA = smallTree("red_cinchona");
    public static final PlantType<SmallTreeBlockSet> REDBUD = smallTree("redbud");
    public static final PlantType<SmallTreeBlockSet> WILD_APPLE = smallTree("wild_apple");
    public static final PlantType<SmallTreeBlockSet> WITCH_HAZEL = smallTree("witch_hazel");
    public static final PlantType<SmallTreeBlockSet> WISTERIA = smallTree("wisteria");

    // ===== Tall Plants =====
    public static final PlantType<Block> AGAVE_AMERICANA_LEAVES = tallPlant("agave_americana_leaves", 2, 30, 1);
    public static final PlantType<Block> WELWITSCHIA_LEAF = tallPlant("welwitschia_leaf", 3, 19, 22);
    public static final PlantType<Block> CORPSE_FLOWER_INFLORESCENCE = tallPlant(
        "corpse_flower_inflorescence",
        2,
        24,
        4);
    public static final PlantType<Block> CORPSE_FLOWER_LEAF = tallPlant("corpse_flower_leaf", 2, 21, 6);
    public static final PlantType<Block> HEMP_LEAF = tallPlant("hemp_leaf", 2, 21, 5);
    public static final PlantType<Block> MONSTERA_LEAF_LARGE = tallPlant("monstera_leaf_large", 2, 13, 9);
    public static final PlantType<Block> BELLADONNA_LILY = tallPlant("belladonna_lily", 2, 15, 8);
    public static final PlantType<Block> BETHROOT = tallPlant("bethroot", 2, 17, 9);
    public static final PlantType<Block> BLUE_AGAVE = tallPlant("blue_agave", 2, 29, 1);
    public static final PlantType<Block> CHICORY = tallPlant("chicory", 2, 15, 7);
    public static final PlantType<Block> DELPHINIUM = tallPlant("delphinium", 2, 15, 7);
    public static final PlantType<Block> DOGFENNEL = tallPlant("dogfennel", 2, 13, 7);
    public static final PlantType<Block> DRAGON_LILY = tallPlant("dragon_lily", 2, 22, 6);
    public static final PlantType<Block> ENGLISH_LAVENDER = tallPlant("english_lavender", 2, 16, 8);
    public static final PlantType<Block> FALLING_STARS = tallPlant("falling_stars", 2, 18, 7);
    public static final PlantType<Block> FEZ_ALOE = tallPlant("fez_aloe", 2, 16, 8);
    public static final PlantType<Block> FIREWEED = tallPlant("fireweed", 2, 6, 14);
    public static final PlantType<Block> FOXGLOVE = tallPlant("foxglove", 2, 13, 9);
    public static final PlantType<Block> GARDEN_LUPINE = tallPlant("garden_lupine", 2, 12, 10);
    public static final PlantType<Block> GIANT_AIRPLANT = tallPlant("giant_airplant", 2, 21, 5);
    public static final PlantType<Block> GIANT_GOLDENROD = tallPlant("giant_goldenrod", 2, 14, 9);
    public static final PlantType<Block> GIANT_IRONWEED = tallPlant("giant_ironweed", 2, 11, 10);
    public static final PlantType<Block> GREEN_PITCHER_PLANT = tallPlant("green_pitcher_plant", 2, 8, 12);
    public static final PlantType<Block> ILIAU_FLOWER = tallPlant("iliau_flower", 2, 13, 9);
    public static final PlantType<Block> MUGWORT = tallPlant("mugwort", 2, 16, 8);
    public static final PlantType<Block> MULTIFLORA_ROSE = tallPlant("multiflora_rose", 2, 15, 8);
    public static final PlantType<Block> NEW_ZEALAND_CALCEOLARIA = tallPlant("new_zealand_calceolaria", 2, 18, 7);
    public static final PlantType<Block> OCOTILLO = tallPlant("ocotillo", 4, 64, 1);
    public static final PlantType<Block> OCOTILLO_FLOWERING = tallPlant("ocotillo_flowering", 4, 64, 1);
    public static final PlantType<Block> PAINTED_TRILLIUM = tallPlant("painted_trillium", 2, 16, 7);
    public static final PlantType<Block> PARROT_FEATHER_WATERMILFOIL = tallPlant(
        "parrot_feather_watermilfoil",
        2,
        16,
        8);
    public static final PlantType<Block> PATUJU_FLOWER = tallPlant("patuju_flower", 2, 30, 1);
    public static final PlantType<Block> PURPLE_LOOSESTRIFE = tallPlant("purple_loosestrife", 2, 16, 8);
    public static final PlantType<Block> ROSS_LILY = tallPlant("ross_lily", 1, 20, 6);
    public static final PlantType<Block> ROYAL_HAKEA = tallPlant("royal_hakea", 2, 16, 8);
    public static final PlantType<Block> SKUNK_CABBAGE = tallPlant("skunk_cabbage", 1, 23, 5);
    public static final PlantType<Block> SKUNK_CABBAGE_FLOWERING = tallPlant("skunk_cabbage_flowering", 2, 23, 5);
    public static final PlantType<Block> SNAPDRAGON = tallPlant("snapdragon", 2, 11, 10);
    public static final PlantType<Block> STARGAZER_LILY = tallPlant("stargazer_lily", 2, 14, 9);
    public static final PlantType<Block> STINGING_NETTLE = tallPlant("stinging_nettle", 2, 12, 10);
    public static final PlantType<Block> SUNFLOWER = tallPlant("sunflower", 2, 8, 12);
    public static final PlantType<Block> SWANLEAF_SAGE = tallPlant("swanleaf_sage", 1, 24, 4);
    public static final PlantType<Block> VALERIAN = tallPlant("valerian", 2, 13, 9);
    public static final PlantType<Block> WHITE_SNAKEROOT = tallPlant("white_snakeroot", 2, 11, 11);
    public static final PlantType<Block> WOAD = tallPlant("woad", 2, 18, 7);

    // ===== Ferns =====
    public static final PlantType<Block> AUTUMN_FERN_FROND = fern("autumn_fern_frond", 8);
    public static final PlantType<Block> BRACKEN_FROND = fern("bracken_frond", 16, 40);
    public static final PlantType<Block> CHRISTMAS_FERN = fern("christmas_fern", 16, 40);
    public static final PlantType<Block> CINNAMON_FERN_FROND = fern("cinnamon_fern_frond", 16, 30);
    public static final PlantType<Block> DEER_FERN_FROND = fern("deer_fern_frond", 8);
    public static final PlantType<Block> HARTS_TONGUE_FERN_FROND = fern("harts_tongue_fern_frond", 8);
    public static final PlantType<Block> HORSETAIL = fern("horsetail", 16, 40);
    public static final PlantType<Block> MT_KAALA_CYANEA_FROND = fern("mt_kaala_cyanea_frond", 16, 40);
    public static final PlantType<Block> OEM_FROND = fern("oem_frond", 32, -20, 0.2D);
    public static final PlantType<Block> SILVER_FERN_FROND = fern("silver_fern_frond", 16, 40, 0.2D);
    public static final PlantType<Block> SILVER_FERN_KORU = fern("silver_fern_koru", 8, 45, 0.1D);
    public static final PlantType<Block> SWORD_FERN = fern("sword_fern", 8);

    // ===== Flowers =====
    public static final PlantType<Block> AFRICAN_LILY = flower("african_lily");
    public static final PlantType<Block> AFRICAN_VIOLET = flower("african_violet");
    public static final PlantType<Block> AKIOHALA = flower("akiohala");
    public static final PlantType<Block> ALOE_VERA = flower("aloe_vera");
    public static final PlantType<Block> ALSTROEMERIA = flower("alstroemeria");
    public static final PlantType<Block> ALUMROOT = flower("alumroot");
    public static final PlantType<Block> AMBRETTE = flower("ambrette");
    public static final PlantType<Block> ANISE_HYSSOP = flower("anise_hyssop");
    public static final PlantType<Block> ARGENTINE_SALTROOT = flower("argentine_saltroot");
    public static final PlantType<Block> ASIAN_BLEEDING_HEART = flower("asian_bleeding_heart");
    public static final PlantType<Block> ASTER = flower("aster");
    public static final PlantType<Block> AURICULA = flower("auricula");
    public static final PlantType<Block> AZURE_BLUET = flower("azure_bluet");
    public static final PlantType<Block> BEEBALM = flower("beebalm");
    public static final PlantType<Block> BLACK_EYED_SUSAN = flower("black_eyed_susan");
    public static final PlantType<Block> BOG_ROSEMARY = flower("bog_rosemary");
    public static final PlantType<Block> CAPE_SUNDEW = flower("cape_sundew");
    public static final PlantType<Block> CARNATION = flower("carnation");
    public static final PlantType<Block> CAROLINA_CRANESBILL = flower("carolina_cranesbill");
    public static final PlantType<Block> CAROLINA_LILY = flower("carolina_lily");
    public static final PlantType<Block> CATNIP = flower("catnip");
    public static final PlantType<Block> CELADINE_POPPY = flower("celadine_poppy");
    public static final PlantType<Block> CHAMOMILE = flower("chamomile");
    public static final PlantType<Block> CHRYSANTHEMUM = flower("chrysanthemum");
    public static final PlantType<Block> COMMON_PEONY = flower("common_peony");
    public static final PlantType<Block> CORALBELLS = flower("coralbells");
    public static final PlantType<Block> CORN_POPPY = flower("corn_poppy");
    public static final PlantType<Block> CORNFLOWER = flower("cornflower");
    public static final PlantType<Block> COTTON_HEADED_SNOW_LOTUS = flower("cotton_headed_snow_lotus");
    public static final PlantType<Block> COW_PARSLEY = flower("cow_parsley");
    public static final PlantType<Block> COYOTE_MINT = flower("coyote_mint");
    public static final PlantType<Block> CREEPING_THISTLE = flower("creeping_thistle");
    public static final PlantType<Block> CUTLEAF_ANEMONE = flower("cutleaf_anemone");
    public static final PlantType<Block> DWARF_LARKSPUR = flower("dwarf_larkspur");
    public static final PlantType<Block> EDELWEISS = flower("edelweiss");
    public static final PlantType<Block> FALSE_RUE_ANEMONE = flower("false_rue_anemone");
    public static final PlantType<Block> FAWN_LILY = flower("fawn_lily");
    public static final PlantType<Block> FEVERFEW = flower("feverfew");
    public static final PlantType<Block> FIREWHEEL = flower("firewheel");
    public static final PlantType<Block> FORGET_ME_NOT = flower("forget_me_not");
    public static final PlantType<Block> GERANIUM = flower("geranium");
    public static final PlantType<Block> GLADIOLUS_ITALICUS = flower("gladiolus_italicus");
    public static final PlantType<Block> GRAPE_HYACINTH = flower("grape_hyacinth");
    public static final PlantType<Block> HEATHER = flower("heather");
    public static final PlantType<Block> HEDGEHOG_CONEFLOWER = flower("hedgehog_coneflower");
    public static final PlantType<Block> ICELAND_POPPY = flower("iceland_poppy");
    public static final PlantType<Block> JAPANESE_SPIREA = flower("japanese_spirea");
    public static final PlantType<Block> KING_PROTEA = flower("king_protea");
    public static final PlantType<Block> KOKI_O_KE_OKE_O = flower("koki_o_ke_oke_o");
    public static final PlantType<Block> KOKI_O_ULA = flower("koki_o_ula");
    public static final PlantType<Block> LABDANUM = flower("labdanum");
    public static final PlantType<Block> LEMON_DAYLILY = flower("lemon_daylily");
    public static final PlantType<Block> LENTEN_ROSE = flower("lenten_rose");
    public static final PlantType<Block> LILY_OF_THE_VALLEY = flower("lily_of_the_valley");
    public static final PlantType<Block> MADDER = flower("madder");
    public static final PlantType<Block> MANDRAKE = flower("mandrake");
    public static final PlantType<Block> MARIGOLD = flower("marigold");
    public static final PlantType<Block> NOOG = flower("noog");
    public static final PlantType<Block> ORANGE_DAYLILY = flower("orange_daylily");
    public static final PlantType<Block> ORIENTAL_POPPY = flower("oriental_poppy");
    public static final PlantType<Block> OXEYE_DAISY = flower("oxeye_daisy");
    public static final PlantType<Block> PAPERWHITE = flower("paperwhite");
    public static final PlantType<Block> PATCHOULI = flower("patchouli");
    public static final PlantType<Block> PEACE_LILY = flower("peace_lily");
    public static final PlantType<Block> PETUNIA = flower("petunia");
    public static final PlantType<Block> PINK_ROCKROSE = flower("pink_rockrose");
    public static final PlantType<Block> POPPY_ANEMONE = flower("poppy_anemone");
    public static final PlantType<Block> PRIMROSE = flower("primrose");
    public static final PlantType<Block> PURPLE_HEART = flower("purple_heart");
    public static final PlantType<Block> PURPLE_MORNING_GLORY = flower("purple_morning_glory");
    public static final PlantType<Block> QUINCHAMALI = flower("quinchamali");
    public static final PlantType<Block> RAUPOTARANGA = flower("raupotaranga");
    public static final PlantType<Block> RED_CAMPION = flower("red_campion");
    public static final PlantType<Block> ROCKY_MOUNTAIN_COLUMBINE = flower("rocky_mountain_columbine");
    public static final PlantType<Block> SCARLET_GLOBEMALLOW = flower("scarlet_globemallow");
    public static final PlantType<Block> SPEAR_THISTLE = flower("spear_thistle");
    public static final PlantType<Block> SPONGEPLANT = flower("spongeplant");
    public static final PlantType<Block> STRINGY_STONECROP = flower("stringy_stonecrop");
    public static final PlantType<Block> SULPHUR_COSMOS = flower("sulphur_cosmos");
    public static final PlantType<Block> SWAMP_SUNFLOWER = flower("swamp_sunflower");
    public static final PlantType<Block> SWEET_BOBBEJAANTJIE = flower("sweet_bobbejaantjie");
    public static final PlantType<Block> TACCA_CHANTRIERI = flower("tacca_chantrieri");
    public static final PlantType<Block> THALE_CRESS = flower("thale_cress");
    public static final PlantType<Block> TRAILING_ARBUTUS = flower("trailing_arbutus");
    public static final PlantType<Block> TRITOMEA = flower("tritomea");
    public static final PlantType<Block> TURKS_CAP_LILY = flower("turks_cap_lily");
    public static final PlantType<Block> VARILEAF_CINQUEFOIL = flower("varileaf_cinquefoil");
    public static final PlantType<Block> VEINED_SUN_ORCHID = flower("veined_sun_orchid");
    public static final PlantType<Block> VENUS_FLYTRAP_CLOSED = flower("venus_flytrap_closed");
    public static final PlantType<Block> VENUS_FLYTRAP_OPEN = flower("venus_flytrap_open");
    public static final PlantType<Block> VIRGINIA_BLUEBELLS = flower("virginia_bluebells");
    public static final PlantType<Block> WATER_FLAG = flower("water_flag");
    public static final PlantType<Block> WELSH_POPPY = flower("welsh_poppy");
    public static final PlantType<Block> WHITE_LUPIN = flower("white_lupin");
    public static final PlantType<Block> YARROW = flower("yarrow");
    public static final PlantType<Block> YELLOW_STAR_OF_BETHLEHEM = flower("yellow_star_of_bethlehem");
    public static final PlantType<Block> YERBA_MANSA = flower("yerba_mansa");
    public static final PlantType<Block> ZINNIA = flower("zinnia");

    // ===== Small Grasses =====
    public static final PlantType<Block> BERGBAMBOES = smallGrass("bergbamboes");
    public static final PlantType<Block> CATTAIL_RUSHES = smallGrass("cattail_rushes");
    public static final PlantType<Block> DAFFODIL_FLOWERING = smallGrass("daffodil_flowering");
    public static final PlantType<Block> DAFFODIL_GRASS = smallGrass("daffodil_grass");
    public static final PlantType<Block> DANDELION_FLOWERING = smallGrass("dandelion_flowering");
    public static final PlantType<Block> DANDELION_LEAVES = smallGrass("dandelion_leaves");
    public static final PlantType<Block> DANDELION_SEEDING = smallGrass("dandelion_seeding");
    public static final PlantType<Block> DOG_VIOLET = smallGrass("dog_violet");
    public static final PlantType<Block> DUNEGRASS = smallGrass("dunegrass");
    public static final PlantType<Block> GOOSE_GRASS = smallGrass("goose_grass");
    public static final PlantType<Block> KANGAROO_GRASS = smallGrass("kangaroo_grass");
    public static final PlantType<Block> LIRIOPE_FLOWERING = smallGrass("liriope_flowering");
    public static final PlantType<Block> LIRIOPE_GRASS = smallGrass("liriope_grass");
    public static final PlantType<Block> MANYSPIKE_FLATSEDGE = smallGrass("manyspike_flatsedge");
    public static final PlantType<Block> MITCHELL_GRASS = smallGrass("mitchell_grass");
    public static final PlantType<Block> NATIVE_MILLET = smallGrass("native_millet");
    public static final PlantType<Block> NEEDLELEAF_SEDGE = smallGrass("needleleaf_sedge");
    public static final PlantType<Block> RESURRECTION_PLANT = smallGrass("resurrection_plant");
    public static final PlantType<Block> SIBERIAN_SQUALL_FLOWERING = smallGrass("siberian_squall_flowering");
    public static final PlantType<Block> SIBERIAN_SQUALL_GRASS = smallGrass("siberian_squall_grass");
    public static final PlantType<Block> SPEARMINT = smallGrass("spearmint");
    public static final PlantType<Block> SPIDER_PLANT = smallGrass("spider_plant");
    public static final PlantType<Block> STRAWBERRY_SPIDERHEAD = smallGrass("strawberry_spiderhead");
    public static final PlantType<Block> SWAMP_CANEGRASS = smallGrass("swamp_canegrass");

    // ===== Ground Cover =====
    // clover_patch keeps its historical registry name "clover_patch_top".
    public static final PlantType<Block> AUTUMN_LEAF_PILE = groundCover("autumn_leaf_pile");
    public static final PlantType<Block> CLOVER_PATCH = groundCover("clover_patch_top");
    public static final PlantType<Block> DEAD_LEAF_PILE = groundCover("dead_leaf_pile");
    public static final PlantType<Block> LEAF_PILE = groundCover("leaf_pile");
    public static final PlantType<Block> PINESTRAW = groundCover("pinestraw");
    public static final PlantType<Block> SNORKELWORT = groundCover("snorkelwort");

    // ===== Large Trees =====
    // Driftwood has no living tree, so it is plain wood placed in the original registration order.
    public static final PlantType<LargeTreeBlockSet> AMARANTH = largeTree("amaranth");
    public static final PlantType<AmericanChestnutBlockSet> AMERICAN_CHESTNUT = largeTree(
        "american_chestnut",
        AmericanChestnutBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> AMERICAN_SYCAMORE = largeTree("american_sycamore");
    public static final PlantType<LargeTreeBlockSet> ASH = largeTree("ash");
    public static final PlantType<LargeTreeBlockSet> BALD_CYPRESS = largeTree("bald_cypress");
    public static final PlantType<LargeTreeBlockSet> BAOBAB = largeTree("baobab");
    public static final PlantType<LargeTreeBlockSet> BEECH = largeTree("beech");
    public static final PlantType<LargeTreeBlockSet> BLACK_OAK = largeTree("black_oak");
    public static final PlantType<LargeTreeBlockSet> BLACK_PINE = largeTree("black_pine");
    public static final PlantType<LargeTreeBlockSet> CACAO = largeTree("cacao");
    public static final PlantType<LargeTreeBlockSet> CANDLENUT = largeTree("candlenut");
    public static final PlantType<BurntLeavesTreeBlockSet> COAST_REDWOOD = largeTree(
        "coast_redwood",
        BurntLeavesTreeBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> COCONUT_PALM = largeTree("coconut_palm");
    public static final PlantType<LargeTreeBlockSet> COOLIBAH = largeTree("coolibah");
    public static final PlantType<LargeTreeBlockSet> DAHURIAN_LARCH = largeTree("dahurian_larch");
    public static final PlantType<LargeTreeBlockSet> DRAGONS_BLOOD = largeTree("dragons_blood");
    public static final PlantType<LargeWoodBlockSet> DRIFTWOOD = wood("driftwood", LargeWoodBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> EBONY = largeTree("ebony");
    public static final PlantType<BurntLeavesTreeBlockSet> GIANT_SEQUOIA = largeTree(
        "giant_sequoia",
        BurntLeavesTreeBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> GRAND_FIR = largeTree("grand_fir");
    public static final PlantType<LargeTreeBlockSet> HAWTHORN = largeTree("hawthorn");
    public static final PlantType<LargeTreeBlockSet> KANKAN = largeTree("kankan");
    public static final PlantType<LargeTreeBlockSet> KOA = largeTree("koa");
    public static final PlantType<LargeTreeBlockSet> MAHOGANY = largeTree("mahogany");
    public static final PlantType<LargeTreeBlockSet> MEDITERRANEAN_CYPRESS = largeTree("mediterranean_cypress");
    public static final PlantType<LargeTreeBlockSet> NAZARENO = largeTree("nazareno");
    public static final PlantType<LargeTreeBlockSet> PAPER_BIRCH = largeTree("paper_birch");
    public static final PlantType<LargeTreeBlockSet> PEDUNCULATE_OAK = largeTree("pedunculate_oak");
    public static final PlantType<LargeTreeBlockSet> QUAKING_ASPEN = largeTree("quaking_aspen");
    public static final PlantType<LargeTreeBlockSet> RAINBOW_EUCALYPTUS = largeTree("rainbow_eucalyptus");
    public static final PlantType<LargeTreeBlockSet> RED_ALDER = largeTree("red_alder");
    public static final PlantType<LargeTreeBlockSet> RED_GUM = largeTree("red_gum");
    public static final PlantType<LargeTreeBlockSet> RED_MAPLE = largeTree("red_maple");
    public static final PlantType<LargeTreeBlockSet> RED_SPRUCE = largeTree("red_spruce");
    public static final PlantType<LargeTreeBlockSet> ROWAN = largeTree("rowan");
    public static final PlantType<PalmTreeBlockSet> ROYAL_PALM = largeTree("royal_palm", PalmTreeBlockSet::new);
    public static final PlantType<RubberTreeBlockSet> RUBBER = largeTree("rubber", RubberTreeBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> SCOTS_PINE = largeTree("scots_pine");
    public static final PlantType<LargeTreeBlockSet> SIBERIAN_LARCH = largeTree("siberian_larch");
    public static final PlantType<LargeTreeBlockSet> SIERRA_JUNIPER = largeTree("sierra_juniper");
    public static final PlantType<LargeTreeBlockSet> SOUTHERN_MAGNOLIA = largeTree("southern_magnolia");
    public static final PlantType<LargeTreeBlockSet> SWEET_CHERRY = largeTree("sweet_cherry");
    public static final PlantType<LargeTreeBlockSet> SWEETGUM = largeTree("sweetgum");
    public static final PlantType<LargeTreeBlockSet> TAMARACK = largeTree("tamarack");
    public static final PlantType<LargeTreeBlockSet> TURKISH_PINE = largeTree("turkish_pine");
    public static final PlantType<LargeTreeBlockSet> UMBRELLA_TREE = largeTree("umbrella_tree");
    public static final PlantType<WeepingWillowBlockSet> WEEPING_WILLOW = largeTree(
        "weeping_willow",
        WeepingWillowBlockSet::new);
    public static final PlantType<WhitePoplarBlockSet> WHITE_POPLAR = largeTree(
        "white_poplar",
        WhitePoplarBlockSet::new);
    public static final PlantType<LargeTreeBlockSet> YEW = largeTree("yew");

    // ===== Helpers =====
    private static PlantType<Block> bush(String registryName) {
        return PlantRegistry.register(registryName, new BushForm());
    }

    private static PlantType<Block> flower(String registryName) {
        return PlantRegistry.register(registryName, new FlowerForm());
    }

    private static PlantType<Block> smallGrass(String registryName) {
        return PlantRegistry.register(registryName, new SmallGrassForm());
    }

    private static PlantType<Block> fern(String registryName, int halfSize) {
        return fern(registryName, halfSize, 45);
    }

    private static PlantType<Block> fern(String registryName, int halfSize, double openingAngle) {
        return fern(registryName, halfSize, openingAngle, 0.0);
    }

    private static PlantType<Block> fern(String registryName, int halfSize, double openingAngle, double minimumRadius) {
        return PlantRegistry.register(registryName, new FernForm(halfSize, openingAngle, minimumRadius));
    }

    private static PlantType<Block> tallPlant(String registryName, int verticalBlockSize, int pixelWidth,
        int textureX) {
        return PlantRegistry.register(registryName, new TallPlantForm(verticalBlockSize, pixelWidth, textureX));
    }

    private static PlantType<Block> groundCover(String registryName) {
        return PlantRegistry.register(registryName, new GroundCoverForm());
    }

    private static PlantType<SmallTreeBlockSet> smallTree(String registryName) {
        return woodSet(registryName, PlantCategory.SMALL_TREE, SmallTreeBlockSet::new);
    }

    private static PlantType<SmallTreeBlockSet> smallTree(String registryName, int halfWidth, int faceHalfWidth,
        boolean doSidesConnect) {
        return woodSet(
            registryName,
            PlantCategory.SMALL_TREE,
            name -> new SmallTreeBlockSet(name, halfWidth, faceHalfWidth, doSidesConnect));
    }

    private static PlantType<LargeTreeBlockSet> largeTree(String registryName) {
        return largeTree(registryName, LargeTreeBlockSet::new);
    }

    private static <S extends LargeWoodBlockSet> PlantType<S> largeTree(String registryName,
        Function<String, S> blockSetFactory) {
        return woodSet(registryName, PlantCategory.LARGE_TREE, blockSetFactory);
    }

    private static <S extends WoodBlockSet> PlantType<S> wood(String registryName,
        Function<String, S> blockSetFactory) {
        return woodSet(registryName, PlantCategory.WOOD, blockSetFactory);
    }

    private static <S extends WoodBlockSet> PlantType<S> woodSet(String registryName, PlantCategory category,
        Function<String, S> blockSetFactory) {
        return PlantRegistry.register(registryName, new WoodSetForm<>(category, blockSetFactory));
    }
}
