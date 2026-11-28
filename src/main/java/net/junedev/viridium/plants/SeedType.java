package net.junedev.viridium.plants;

public enum SeedType {

    NONE("none"),
    GENERIC("seeds"),
    CONE("cone"),
    ACORN("acorn"),
    FRUIT("fruit"),
    NUT("nut");

    private String name;

    SeedType(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
