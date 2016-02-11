package by.org.cgm.magneticobserver.util;

import android.text.format.DateFormat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.when;

/**
 * Author: Anatol Salanevich
 * Date: 10.02.2016
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest({DateFormat.class})
public class DateTimeUtilsTest {

    @Mock
    private String mYesterday;

    @Before
    public void setUp() throws Exception {
        mockStatic(DateFormat.class);
        when(DateFormat.format(anyString(), (Date) anyObject())).thenReturn(mYesterday);
    }

    @Test
    public void testGetYesterday() throws Exception {
        String yesterday = DateTimeUtils.getYesterday();
        assertThat(yesterday, is(notNullValue()));
    }

    @Test
    public void testGetPastDay() throws Exception {
        String yesterday = DateTimeUtils.getPastDay(-1);
        assertThat(yesterday, is(notNullValue()));
    }

    @Test
    public void testGetDate() throws Exception {
        String text = "10-12-2015asf";
        assertEquals("10-12-2015", DateTimeUtils.getDate(text));
    }

    @Test
    public void testGetTime() throws Exception {
        String text = "10-12-2015 12:45:21.45";
        assertEquals("12:45:21.45", DateTimeUtils.getTime(text));
    }

}