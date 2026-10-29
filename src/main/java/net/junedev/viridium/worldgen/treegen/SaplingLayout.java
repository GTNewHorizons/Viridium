package net.junedev.viridium.worldgen.treegen;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.function.BiPredicate;

public enum SaplingLayout {

    SINGLE(new Offset(0, 0)),
    TWO_BY_TWO(new Offset(0, 0), new Offset(1, 0), new Offset(0, 1), new Offset(1, 1)),
    FOUR_BY_FOUR_PLUS(new Offset(0, -1), new Offset(1, -1), new Offset(-1, 0), new Offset(0, 0), new Offset(1, 0),
        new Offset(2, 0), new Offset(-1, 1), new Offset(0, 1), new Offset(1, 1), new Offset(2, 1), new Offset(0, 2),
        new Offset(1, 2));

    SaplingLayout(Offset... offsets) {
        this.offsets = Collections.unmodifiableList(Arrays.asList(offsets));
    }

    private final List<Offset> offsets;

    public List<Offset> getOffsets() {
        return offsets;
    }

    // The enum ordered by amount of saplings needed (we always pick the biggest possible)
    private static final List<SaplingLayout> LARGEST_FIRST;

    static {
        SaplingLayout[] layouts = values();
        Arrays.sort(
            layouts,
            Comparator.comparingInt((SaplingLayout layout) -> layout.offsets.size())
                .reversed());
        LARGEST_FIRST = Collections.unmodifiableList(Arrays.asList(layouts));
    }

    public static List<SaplingLayout> largestFirst() {
        return LARGEST_FIRST;
    }

    /** Finds a complete layout containing the triggering sapling, regardless of its position in the layout. */
    public Match findMatch(int x, int z, BiPredicate<Integer, Integer> isMatchingSapling) {
        // Bruteforce (checks all origins and its offsets)
        for (Offset trigger : offsets) {
            int originX = x - trigger.x();
            int originZ = z - trigger.z();
            boolean matches = true;
            for (Offset offset : offsets) {
                if (!isMatchingSapling.test(originX + offset.x(), originZ + offset.z())) {
                    matches = false;
                    break;
                }
            }
            if (matches) return new Match(this, originX, originZ);
        }
        return null;
    }

    public record Offset(int x, int z) {}

    public record Match(SaplingLayout layout, int originX, int originZ) {}
}
