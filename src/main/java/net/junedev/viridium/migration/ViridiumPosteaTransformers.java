package net.junedev.viridium.migration;

public class ViridiumPosteaTransformers implements Runnable {

    @Override
    public void run() {
        registerBOPReplacements();
    }

    private void registerBOPReplacements() {
        // BlockReplacementManager.addSimpleReplacement(
        // "viridium:rubber_log_legacy", 3,
        // ViriPlants.RUBBER_TREE.getBlocks().log, 0);
    }
}
