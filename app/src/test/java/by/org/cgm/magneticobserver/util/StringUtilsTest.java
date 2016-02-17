package by.org.cgm.magneticobserver.util;

import junit.framework.TestCase;

import by.org.cgm.magneticobserver.AppCache;
import by.org.cgm.magneticobserver.model.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 16.01.2016
 */
public class StringUtilsTest extends TestCase {

    @Override
    protected void setUp() throws Exception {
        AppCache.initialize();
        String[] levels = new String[] {
                "quiet", "small storm", "moderate storm", "big storm", "very big storm"
        };
        AppCache.getInstance().setLevels(levels);
    }

    public void testParse() throws Exception {
        String json = "{'begin':'11:25','end':'12:24','date':'2016-01-16','value':2}";
        MagMessage mes = StringUtils.parse(json);
        assertEquals("11:25", mes.getBegin());
        assertEquals("12:24", mes.getEnd());
        assertEquals("2016-01-16", mes.getDate());
        assertEquals(2, mes.getValue());
    }

    public void testToDoubleDigits() throws Exception {
        assertEquals("05", StringUtils.toDoubleDigits(5));
        assertEquals("23", StringUtils.toDoubleDigits(23));
    }

    public void testFormatDecimals() throws Exception {
        assertEquals("value = 12.2", StringUtils.formatDecimals("value = %s", 12.21f));
        assertEquals("value = 12.3", StringUtils.formatDecimals("value = %s", 12.25f));
    }

    public void testGetStormLevel() throws Exception {
        assertEquals(AppCache.getInstance().getLevels()[0], StringUtils.getStormLevel(0));
        assertEquals(AppCache.getInstance().getLevels()[1], StringUtils.getStormLevel(2));
        assertEquals(AppCache.getInstance().getLevels()[2], StringUtils.getStormLevel(4));
        assertEquals(AppCache.getInstance().getLevels()[3], StringUtils.getStormLevel(6));
        assertEquals(AppCache.getInstance().getLevels()[4], StringUtils.getStormLevel(8));
    }

    public void testToMessage() throws Exception {
        String template = "Magnetic level - %s";
        assertEquals("Magnetic level - " + AppCache.getInstance().getLevels()[0],
                StringUtils.toMessage(template, 0));
        assertEquals("Magnetic level - " + AppCache.getInstance().getLevels()[1],
                StringUtils.toMessage(template, 2));
        assertEquals("Magnetic level - " + AppCache.getInstance().getLevels()[2],
                StringUtils.toMessage(template, 4));
        assertEquals("Magnetic level - " + AppCache.getInstance().getLevels()[3],
                StringUtils.toMessage(template, 6));
        assertEquals("Magnetic level - " + AppCache.getInstance().getLevels()[4],
                StringUtils.toMessage(template, 8));
    }

}