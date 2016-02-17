package by.org.cgm.magneticobserver.util;

import android.graphics.Color;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import by.org.cgm.magneticobserver.R;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyInt;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Author: Anatol Salanevich
 * Date: 11.02.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({Color.class})
public class ColorUtilsTest {

    @Mock
    private int mColor;

    @Before
    public void setUp() throws Exception {
        mockStatic(Color.class);
        when(Color.rgb(anyInt(), anyInt(), anyInt())).thenReturn(mColor);
    }

    @Test
    public void testGetColorId() throws Exception {
        assertEquals(R.color.color_quiet, ColorUtils.getColorId(1));
        assertEquals(R.color.color_low_storm, ColorUtils.getColorId(2));
        assertEquals(R.color.color_moderate_storm, ColorUtils.getColorId(4));
        assertEquals(R.color.color_high_storm, ColorUtils.getColorId(7));
        assertEquals(R.color.color_very_high_storm, ColorUtils.getColorId(9));
        assertEquals(R.color.white, ColorUtils.getColorId(521));
    }

    @Test
    public void testGetColorRgb() throws Exception {
        assertEquals(Color.rgb(42, 224, 0), ColorUtils.getColorRgb(0));
        assertEquals(Color.rgb(42, 224, 0), ColorUtils.getColorRgb(1));
        assertEquals(Color.rgb(207, 193, 0), ColorUtils.getColorRgb(2));
        assertEquals(Color.rgb(207, 193, 0), ColorUtils.getColorRgb(3));
        assertEquals(Color.rgb(203, 144, 0), ColorUtils.getColorRgb(4));
        assertEquals(Color.rgb(203, 144, 0), ColorUtils.getColorRgb(5));
        assertEquals(Color.rgb(199, 98, 0), ColorUtils.getColorRgb(6));
        assertEquals(Color.rgb(199, 98, 0), ColorUtils.getColorRgb(7));
        assertEquals(Color.rgb(191, 9, 0), ColorUtils.getColorRgb(8));
        assertEquals(Color.rgb(191, 9, 0), ColorUtils.getColorRgb(9));
        assertEquals(Color.rgb(0, 0, 0), ColorUtils.getColorRgb(321));
    }
}