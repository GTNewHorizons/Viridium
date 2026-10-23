package net.junedev.viridium.worldgen.biomes;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public final class WeightedSelector<T> {

    private final List<T> values = new ArrayList<>();
    private final List<Integer> cumulativeWeights = new ArrayList<>();
    private int totalWeight;

    public void add(T value, int weight) {
        if (value == null || weight <= 0 || weight > Integer.MAX_VALUE - totalWeight) {
            throw new IllegalArgumentException("Weighted entries need a value and a positive, non-overflowing weight");
        }

        totalWeight += weight;
        values.add(value);
        cumulativeWeights.add(totalWeight);
    }

    public boolean isEmpty() {
        return values.isEmpty();
    }

    public T select(Random random) {
        if (isEmpty()) throw new IllegalStateException("Cannot select from an empty weighted list");

        int roll = random.nextInt(totalWeight);
        for (int i = 0; i < cumulativeWeights.size(); i++) {
            if (roll < cumulativeWeights.get(i)) return values.get(i);
        }

        throw new IllegalStateException("Weighted entries do not match their total");
    }

}
