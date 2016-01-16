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
}