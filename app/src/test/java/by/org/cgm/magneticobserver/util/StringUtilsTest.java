package by.org.cgm.magneticobserver.util;

import junit.framework.TestCase;

import by.org.cgm.magneticobserver.model.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 16.01.2016
 */
public class StringUtilsTest extends TestCase {

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

}