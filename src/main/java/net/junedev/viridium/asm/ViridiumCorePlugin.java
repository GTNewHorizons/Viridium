package net.junedev.viridium.asm;

import java.util.List;
import java.util.Map;
import java.util.Set;

import net.junedev.viridium.mixin.ViridiumMixins;

import com.gtnewhorizon.gtnhmixins.IEarlyMixinLoader;
import com.gtnewhorizon.gtnhmixins.builders.IMixins;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;

@IFMLLoadingPlugin.MCVersion("1.7.10")
@IFMLLoadingPlugin.TransformerExclusions({ "net.junedev.viridium.asm" })
@IFMLLoadingPlugin.Name("Viridium core plugin")
public class ViridiumCorePlugin implements IFMLLoadingPlugin, IEarlyMixinLoader {

    @Override
    public String[] getASMTransformerClass() {
        return null;
    }

    @Override
    public String getModContainerClass() {
        return null;
    }

    @Override
    public String getSetupClass() {
        return null;
    }

    @Override
    public void injectData(Map<String, Object> data) {}

    @Override
    public String getAccessTransformerClass() {
        return null;
    }

    @Override
    public String getMixinConfig() {
        return "mixins.viridium.early.json";
    }

    @Override
    public List<String> getMixins(Set<String> loadedCoreMods) {
        List<String> mixins = IMixins.getEarlyMixins(ViridiumMixins.class, loadedCoreMods);
        return mixins;
    }

}
