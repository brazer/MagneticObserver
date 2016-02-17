package by.org.cgm.magneticobserver.util;

import android.test.AndroidTestCase;

import net.danlew.android.joda.JodaTimeAndroid;

import by.org.cgm.magneticobserver.model.MagMessage;

/**
 * Author: Anatol Salanevich
 * Date: 16.02.2016
 */
public class DateTimeUtilsAndroidTest extends AndroidTestCase {

    @Override
    protected void setUp() throws Exception {
        JodaTimeAndroid.init(getContext());
    }

    public void testConvertToLocalTime() throws Exception {
        String json = "{'begin':'11:25','end':'12:24','date':'2016-01-16','value':2}";
        MagMessage mes = StringUtils.parse(json);
        assertNotNull(mes);
        DateTimeUtils.convertToLocalTime(mes);
        assertEquals("14:25", mes.getBegin());
        assertEquals("15:24", mes.getEnd());
    }

}