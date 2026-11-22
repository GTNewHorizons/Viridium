package net.junedev.viridium.client.renderers.utils;

/**
 * <a href="https://gist.github.com/earthbound19/e7fe15fdf8ca3ef814750a61bc75b5ce">Source</a>
 */
public class ColorSpaces {

    public static double gammaToLinear(double c) {
        return c >= 0.04045 ? Math.pow((c + 0.055) / 1.055, 2.4) : c / 12.92;
    }

    public static double linearToGamma(double c) {
        return c >= 0.0031308 ? 1.055 * Math.pow(c, 1 / 2.4) - 0.055 : 12.92 * c;
    }

    public static double[] rgbToOklab(double r, double g, double b) {
        double lR = gammaToLinear(r);
        double lG = gammaToLinear(g);
        double lB = gammaToLinear(b);

        double l = Math.cbrt(0.4122214708 * lR + 0.5363325363 * lG + 0.0514459929 * lB);
        double m = Math.cbrt(0.2119034982 * lR + 0.6806995451 * lG + 0.1073969566 * lB);
        double s = Math.cbrt(0.0883024619 * lR + 0.2817188376 * lG + 0.6299787005 * lB);

        return new double[] { l * 0.2104542553 + m * 0.7936177850 + s * -0.0040720468,
            l * 1.9779984951 + m * -2.4285922050 + s * 0.4505937099,
            l * 0.0259040371 + m * 0.7827717662 + s * -0.8086757660 };
    }

    public static double[] oklabToRgb(double okL, double okA, double okB) {
        double l = Math.pow(okL + okA * 0.3963377774 + okB * 0.2158037573, 3);
        double m = Math.pow(okL + okA * -0.1055613458 + okB * -0.0638541728, 3);
        double s = Math.pow(okL + okA * -0.0894841775 + okB * -1.2914855480, 3);

        return new double[] {
            linearToGamma(Math.clamp((float) (l * 4.0767416621 + m * -3.3077115913 + s * 0.2309699292), 0, 1)),
            linearToGamma(Math.clamp((float) (l * -1.2684380046 + m * 2.6097574011 + s * -0.3413193965), 0, 1)),
            linearToGamma(Math.clamp((float) (l * -0.0041960863 + m * -0.7034186147 + s * 1.7076147010), 0, 1)) };
    }
}
