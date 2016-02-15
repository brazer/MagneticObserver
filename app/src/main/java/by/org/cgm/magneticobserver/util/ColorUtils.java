package by.org.cgm.magneticobserver.util;

import android.graphics.Color;

import by.org.cgm.magneticobserver.R;

/**
 * Author: Anatol Salanevich
 * Date: 07.10.2015
 */
public final class ColorUtils {

    public static int getColorId(int level) {
        switch (level) {
            case 0: return R.color.color_quiet;
            case 1: return R.color.color_quiet;
            case 2: return R.color.color_low_storm;
            case 3: return R.color.color_low_storm;
            case 4: return R.color.color_moderate_storm;
            case 5: return R.color.color_moderate_storm;
            case 6: return R.color.color_high_storm;
            case 7: return R.color.color_high_storm;
            case 8: return R.color.color_very_high_storm;
            case 9: return R.color.color_very_high_storm;
        }
        return R.color.white;
    }

    public static int getColorRgb(int level) {
        switch (level) {
            case 0: return Color.rgb(42, 224, 0);
            case 1: return Color.rgb(42, 224, 0);
            case 2: return Color.rgb(207, 193, 0);
            case 3: return Color.rgb(207, 193, 0);
            case 4: return Color.rgb(203, 144, 0);
            case 5: return Color.rgb(203, 144, 0);
            case 6: return Color.rgb(199, 98, 0);
            case 7: return Color.rgb(199, 98, 0);
            case 8: return Color.rgb(191, 9, 0);
            case 9: return Color.rgb(191, 9, 0);
        }
        return Color.rgb(0, 0, 0);
    }

}
