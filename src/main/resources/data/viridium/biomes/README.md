# How to add a new biome

1. Create a biome definition JSON file.
2. Add the biome to `ViriBiomes` as a static variable (for now).
3. Register the biome in `preInit`.

Biomes ids are handled directly by ViriBiomes.

# Biome definition

A JSON file can define the properties of a Viridium biome. Every top-level section has a default value, so you can omit sections or fields unless you want to override them. Fields marked as optional have no value by default and are ignored when omitted, unless noted otherwise.

## `name`

The name of the biome shown on the F3 screen. Default: `"Default"`.

## `generation`

- `weight`: Weight of the biome within its climate zone. Higher values make it more common; the value is not capped. Default: `10`.
- `climateType`: Climate zone where the biome can generate: `WARM`, `WET`, `COOL`, or `SNOWY`. Default: `WARM`.
- `dictionaryTypes`: Extra tags used by Minecraft and other mods to identify biome properties. Default: `[]`.

## `climate`

These values influence vanilla biome behavior, such as rain chance or snowing/freezing of water, and default grass and foliage colors.

- `temperature`: Temperature value. Default: `0.8`.
- `rainfall`: Rainfall value. Default: `0.4`.
- `rain`: Whether rain can occur in the biome. Default: `true`.

## `terrain`

Terrain properties.

### `height`

- `baseWeight`: Base terrain height. Can be negative. Default: `0.2`.
- `heightVariation`: Terrain height variation. Default: `0.2`.

### `surface`

- `topBlockId`: Block ID for the biome's top surface block. Optional; if omitted, the Minecraft default (`minecraft:grass`) is used.
- `fillerBlockId`: Block ID for the biome's filler block. Optional; if omitted, the Minecraft default (`minecraft:dirt`) is used.

#### `patches`

Surface patches replace the surface blocks when the terrain noise is above the specified threshold. Default: `[]`.

- `minNoise`: Noise threshold. Default: `0.0` if omitted.
- `topBlockId`: Block ID for the patch's top surface block. Optional; if omitted, the biome's base top block is used.
- `fillerBlockId`: Block ID for the patch's filler block. Optional; if omitted, the biome's base filler block is used.

## `decoration`

Properties related to the world-generation decoration phase.

- `treesPerChunk`: Number of trees per chunk. Default: `0`.
- `trees`: Weighted tree generator IDs (listed below). Each entry has an `id` and a positive `weight`; weights set the relative chance for each tree attempt. An empty array uses Minecraft's default tree choice.
- `grassPerChunk`: Number of grass features per chunk. Default: `4`.
- `flowersPerChunk`: Number of flower features per chunk. Default: `1`.

### Tree generator IDs

| ID | Generator |
| --- | --- |
| `minecraft:oak` | Regular oak. |
| `minecraft:tall_oak` | Large, branched oak. |
| `minecraft:birch` | Regular birch. |
| `minecraft:tall_birch` | Tall birch. |
| `minecraft:acacia` | Regular acacia. |
| `minecraft:dark_oak` | Dark oak with a 2x2 trunk. |
| `minecraft:spruce` | Regular spruce with a 1x1 trunk. |
| `minecraft:tall_spruce` | Giant spruce with a 2x2 trunk and a deep leaf crown. |
| `minecraft:jungle` | Small jungle tree with vines and a 1x1 trunk. |
| `minecraft:tall_jungle` | Giant jungle tree with vines and a 2x2 trunk. |

These IDs belong to Viridium's tree generator registry and select vanilla Minecraft 1.7.10 generators.

### `features`

Custom features added during the decoration phase.

#### `blobs`

Block clusters. Default: `[]`.

- `blockId`: Block ID for the blob. If omitted or invalid, `minecraft:diamond_block` is used.
- `radius`: Blob radius. Default: `0` if omitted.
- `frequency`: Generation frequency; `1` is very frequent, while `8` is more spread out. Must be greater than `0`. Default: `5` if omitted.

#### `smallPatches`

Small patches based on vanilla underwater patches, with slight erosion around the edges. Default: `[]`.

- `blockId`: Block ID for the patch. If omitted or invalid, `minecraft:beacon` is used.
- `radius`: Patch radius. For values of `3` or more, the radius varies from `radius - 2` to `radius`. Default: `0` if omitted.
- `frequency`: Generation frequency; `1` is very frequent, while `8` is more spread out. Must be greater than `0`. Default: `5` if omitted.

## `appearance`

Optional color overrides. Grass and foliage colors are calculated by Minecraft from the temperature and rainfall values in `climate` when no override is provided.

- `mapColor`: Map color. Optional; if omitted, the Minecraft default is used.
- `grassColor`: Grass color. Optional; if omitted, Minecraft calculates it from the climate values.
- `foliageColor`: Foliage color. Optional; if omitted, Minecraft calculates it from the climate values.
