package by.org.cgm.magneticobserver;

import junit.framework.Assert;
import junit.framework.TestCase;

import java.util.ArrayList;

import by.org.cgm.magneticobserver.model.Data;

/**
 * Author: Anatol Salanevich
 * Date: 24.11.2015
 */
public class AppCacheTest extends TestCase {

    public void testSetData2() throws Exception {

        Assert.assertNotNull(AppCache.getInstance());
        AppCache.getInstance().setData2(new ArrayList<Data>());
        Assert.assertEquals(0, AppCache.getInstance().getData2().length);
    }

    public void testGetData2() throws Exception {

    }
}