Continental biome generation comes in a few different parts. A terrain setting, followed by modifiers. These modifiers can come with variations for temperature, humidity, foliage, and geology types. For starters, every actively used biome in Biomes O’ Plenty will have a cleanroom implementation for old worlds, but they might not all be used in the full release for new worlds. Biome blending will also be incorporated much more, with hot biomes and cold biomes being more separated by distance unless connected by a mountain range. 

## Biome Generation

The idea of biomes in general is fully changing. Instead of biomes, generation will happen through [[Ecoregions]], aka areas with a set distribution of flora and fauna. Terrain generation happens separately from the eco-region stage, and divides the world into distinct regions of: open grasslands, forests, hills, mountains, deserts, wetlands, and lacustrine and riparian (lake and river) zones, respectively. Eco-regions are placed over the terrain and temperature zones are placed within them, roughly forming Climate-Control style temperature bands. Humidity noise is then added over the eco-regions. If an incompatible region forms within an eco-region, such as a humid band within an Outback region, the generator will replace it with a compatible one. Georegions are then defined, and local features, such as hot spots and tar pits, are placed down, modifying the biomes. Finally, plants are allowed to generate, forming at rates defined by their own statistics related to the climate and terrain of the region and the density of the region defined by the biome itself.

## Vertical Biomes

Vertical biomes can exist through Cubic Chunks, allowing for different biomes at different heights of mountains as well as cave biomes. Aether-like floating islands will also exist, but will be rare and well-declared, so players don’t blow up their rockets crashing into them.

### Cave Biomes

- Sunken Grotto is a magical cave biome spawning as pockets in rainforests.

### Aerial Biomes

- Cliffs of Aether are a rare floating island biome thousands of blocks above the clouds unaffected by weather. The biome features pale yellow grass, colorful blue stone, and other flora and fauna inspired by the Aether mod. (800m-900m elevation)
- Cirrus is a widespread biome made of traversable cirrus cloud blocks. It will rarely contain structures such as floating castles and airships. (600m-1200m elevation)
- Slimelands are uncommon sky islands made of green and blue grass and trees, replacing the existing Tinkers’ Construct slime islands in the overworld. (600m-800m elevation)
- Alien Asteroids are extremely rare structure-biomes which contain endstone and other end materials (3,000m-5,000m elevation)

## Sub-Biomes

Sub-Biomes consist of smaller groupings of similar ecological and geological features that can exist within a biome. 

- Thickets are small patches of dense woodland dominated by a single species of small tree or large shrub.
- Lakes and Rivers have special biome-specific alterations depending on what biome they spawn in. For example, a lake which spawns in a desert becomes an oasis biome, spreading foliage around it. A lake which spawns in a georegion of karst limestone has a low chance to become a grotto, and have different foliage depending on what biome it is in. Rivers will have different morphologies depending on the elevation gradient of the biome that it sits in. A river in a flat biome like a grassland will meander, while an upland river will be straight or braided.

## Fantasy Biomes

- Silver Springs will replace BOP’s Sacred Springs, featuring metallic flowers and trees. 
- Arcane Grove will replace BOP’s Mystic Grove. 
- Fae Gardens can spawn as microbiomes in any boreal forest region, supplying their own rare unique flora, fauna, and structures, including the Fairy Fountain and Seelie Court.
- Cursed Acres is a dark, corrupted forest biome with deep ravines commonly cutting across the landscape, emitting strange, toxic, magical fumes that have twisted and defiled the natural landscape.
- Candyland is a food-themed wonderland that can spawn rarely in the place of any polar island biome. 
- Garden Of Eden is the replacement to BOP’s Origin Island, and will generate and spawn in the same ways.

## Biomes O’ Plenty Biomes

BOP adds many biomes which will all need to be replaced cleanroom-style one way or another through Postea (see [[Viridium 1.0]]). Biomes O’ Plenty’s Nether biomes will probably be the only features included for that dimension, since Viridium is intended as mostly an overworld overhaul. 

### Microclimates

Different settings can cause conditions for plants to spawn that normally would be unable to:

- Montane environments increase humidity profiles by 1.
- Lake and River environments increase humidity profiles by 2.
- Starting with Montane, each increase in the altitudinal belt decreases temperature by 1. However, in arid and subarid environments, each increase in altitude actually increases the temperature.
- Sinkhole and Crater environments decrease temperature profiles by 1 due to the Cold-Air-Pool (CAP) effect.
