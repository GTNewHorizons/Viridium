# How to add a new biome

1. Create a biome definition JSON file.
2. Add the biome to `ViriBiomes` as a static variable (for now).
3. Register the biome in `preInit`.

Biome IDs are handled directly by ViriBiomes, in alphabetical order.
IDs currently follow registration order; adding or reordering entries can change assigned IDs until
individual biome IDs are configurable.
The fixed vanilla ID ranges (0-39 and the 1.7.10 mutation IDs) are skipped; collisions with other mods
still fail explicitly. At the moment, biomes are registered from `firstBiomeId=100` onward, and they skip used
vanilla IDs.

# Biome definition

A JSON file can define the properties of a Viridium biome. Every top-level section has a default value, so you can omit sections or fields unless you want to override them. Fields marked as optional have no value by default and are ignored when omitted, unless noted otherwise.

## `name`

The name of the biome shown on the F3 screen. Default: `"Default"`.

## `generation`

- `weight`: Weight of the biome within its climate zone. Higher values make it more common; the value is not capped. Default: `10`.
- `climateType`: Forge 1.7.10 generation zone: `DESERT`, `WARM`, `COOL`, or `ICY`. Default: `WARM`. Humid warm biomes use `WARM`,
  and snowy biomes use `ICY`. Unknown or null enum values are rejected when loading the JSON.
- `dictionaryTypes`: Extra tags used by Minecraft and other mods to identify biome properties. Default: `[]`.

## `climate`

These values influence vanilla biome behavior, such as rain chance or snowing/freezing of water, and default grass and foliage colors.

- `temperatureCelsius`: Optional source temperature in degrees Celsius. Overrides `temperature`.
  The project conversion is `0.15 + C / 25`, snapped to at most `0.1` for C <= 0 and at least
  `0.2` for C > 0 to avoid Minecraft 1.7.10's forbidden temperature interval. This is a project
  convention, not a BOP or vanilla Celsius standard. Altitude can still lower local temperatures.
- `humidityPercent`: Optional source humidity, including values above 100. Overrides `rainfall`;
  the vanilla rainfall value is clamped to 0-1 after dividing by 100. The original value is retained.
- `rain`: Whether rain can occur in the biome. Default: `true`.
- `snow`: Enables snowy precipitation. Default: `false`; built-ins enable it for subzero source climates.

## `terrain`

Terrain properties.

### `height`

- `baseHeight`: Base terrain height. Can be negative. Default: `0.2`.
- `heightVariation`: Terrain height variation. Default: `0.2`.

### `surface`

- `topBlockId`: Block ID for the biome's top surface block. Optional; if omitted, the Minecraft default (`minecraft:grass`) is used.
- `topBlockMeta`: Surface metadata, e.g. `1` for red sand or coarse dirt. Default: `0`.
- `fillerBlockId`: Block ID for the biome's filler block. Optional; if omitted, the Minecraft default (`minecraft:dirt`) is used.

#### `patches`

Surface patches replace the surface blocks when the terrain noise is above the specified threshold. Default: `[]`.

- `minNoise`: Noise threshold. Default: `0.0` if omitted.
- `topBlockId`: Block ID for the patch's top surface block. Optional; if omitted, the biome's base top block is used.
- `topBlockMeta`: Optional surface metadata. Defaults to `0` when changing the block, or the base
  surface metadata when the patch omits `topBlockId`.
- `fillerBlockId`: Block ID for the patch's filler block. Optional; if omitted, the biome's base filler block is used.

## `decoration`

Properties related to the world-generation decoration phase.

- `treesPerChunk`: Number of trees per chunk. Default: `0`.
- `trees`: Weighted tree generator IDs (listed below). Each entry has an `id`, a `variant`, and a positive `weight`; weights set the relative chance for each tree attempt. An empty array uses Minecraft's default tree choice.
- `grassPerChunk`: Number of grass features per chunk. Default: `4`.
- `flowersPerChunk`: Number of flower features per chunk. Default: `1`.
- `grass`: Weighted `blockId`, `meta`, `weight` entries, with the same placement rules as flowers.
  Ground cover checks the soil (not the registry ones yet) tall grass and multi-block plants are placed at their full height.
- `deadBushPerChunk`, `reedsPerChunk`, `waterlilyPerChunk`, `mushroomsPerChunk`,
  `bigMushroomsPerChunk`: Vanilla decoration attempt counts. Default: `0`.
  Vanilla also has baseline reed and mushroom attempts independent of these extra counts.
- `waterLakeChance`: One extra surface water-lake attempt in N chunks, before vegetation. `0` disables it.
- `melonChance`: One melon-patch attempt in N chunks. `0` disables it.

`treesPerChunk: 0` still permits vanilla's occasional bonus tree, use `-999` to disable trees entirely.

### Trees and variants

Each tree entry specifies `id`, `variant` (optional, defaults to `default`) and a positive integer `weight`.
Weights are relative probabilities per tree attempt. A new generator is created for every attempt.
Unknown IDs or variants fail during biome registration.

| ID | Variants |
| --- | --- |
| `minecraft:oak`, `minecraft:birch`, `minecraft:spruce`, `minecraft:jungle` | `default`, `tall` |
| `minecraft:acacia`, `minecraft:dark_oak` | `default` |
| `viridium:american_chestnut`, `viridium:pedunculate_oak` | `default`, `two_by_two` |
| `viridium:coconut_palm` | `default` |
| `minecraft:oak_shrub`, `minecraft:birch_shrub` | `default` |

The following existing species blocks now have placeholder generators (`default`/`single`):
`bald_cypress`, `black_oak`, `cacao`, `coast_redwood`, `giant_sequoia`, `kankan`, `mahogany`,
`quaking_aspen`, `red_maple`, `red_spruce`, `scots_pine`, `sweetgum`, `weeping_willow`,
`black_spruce`, `flowering_dogwood`, `giant_bamboo`, `jacaranda`, `mimosa`, `mulga`,
`red_cinchona`, `wisteria` (all prefixed with `viridium:`).

`single` is an alias for the default factory. Some trees also expose a
variant named after the lowercase layout (`two_by_two`, `four_by_four_plus`). Only registered
layouts are accepted. Check the TreeRegistry to see which variants do each tree have`withVariant` adds named variants such as `tall`.

```json
"trees": [
  { "id": "viridium:american_chestnut", "variant": "two_by_two", "weight": 10 },
  { "id": "minecraft:oak", "variant": "tall", "weight": 2 }
]
```

### Flowers

`flowers` is a weighted array of `blockId`, `meta` (defaults to `0`), and positive integer `weight`.
The default is `minecraft:red_flower`, metadata `0`, weight `1`; an empty array disables flowers.
`flowersPerChunk` controls patch attempts, not the number of placed blocks. Each patch chooses one
plant by weight, then tries up to 64 placements with air, soil and height checks. Forge's FLOWERS
decoration event still controls generation. Bonemeal also uses the same weighted selection.

Viridium tall plants are placed at their full height with segment
metadata. Their JSON metadata must be `0`. Vanilla double plants require a bottom variant from
`0` to `5`; the top half is automatically placed with metadata `8`. Other plants accept metadata from `0` to `15`.

```json
"flowersPerChunk": 10,
"flowers": [
  { "blockId": "viridium:english_lavender", "meta": 0, "weight": 16 },
  { "blockId": "viridium:cornflower", "meta": 0, "weight": 2 },
  { "blockId": "minecraft:red_flower", "meta": 0, "weight": 1 }
]
```

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

#### `bushes`

Shrub clusters using `blockId`, `radius`, and positive `frequency`, with the same frequency semantics
as blobs. Use a registered bush block (e.g. `viridium:satsuki_azalea`), not an unregistered tree ID.

## `appearance`

Optional color overrides. Grass and foliage colors are calculated by Minecraft from the temperature and rainfall values in `climate` when no override is provided.

- `mapColor`: Map color. Optional; if omitted, the Minecraft default is used.
- `grassColor`: Grass color. Optional; if omitted, Minecraft calculates it from the climate values.
- `foliageColor`: Foliage color. Optional; if omitted, Minecraft calculates it from the climate values.
- `waterColor`: Optional RGB tint for biome water. This multiplies the water texture's color.
