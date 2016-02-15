package by.org.cgm.magneticobserver;

import android.support.test.espresso.Espresso;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.LargeTest;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import by.org.cgm.magneticobserver.ui.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNull;

/**
 * Author: Anatol Salanevich
 * Date: 15.02.2016
 */
@RunWith(AndroidJUnit4.class)
@LargeTest
public class MainActivityChartsTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void setUp() {
        Espresso.registerIdlingResources(
                mActivityTestRule.getActivity().getCountingIdlingResource());
    }

    @Test
    public void testActivity() {
        assertNull(mActivityTestRule.getActivity().getIntent().getExtras());
        onView(ViewMatchers.withId(R.id.fragment_charts__chart1)).check(matches(isDisplayed()));
        onView(withId(R.id.fragment_charts__chart2)).check(matches(isDisplayed()));
    }

    @After
    public void tearDown() {
        Espresso.unregisterIdlingResources(
                mActivityTestRule.getActivity().getCountingIdlingResource());
    }

}
