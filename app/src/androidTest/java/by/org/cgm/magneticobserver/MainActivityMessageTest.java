package by.org.cgm.magneticobserver;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.test.suitebuilder.annotation.SmallTest;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import by.org.cgm.magneticobserver.model.MagMessage;
import by.org.cgm.magneticobserver.ui.activity.MainActivity;
import by.org.cgm.magneticobserver.util.StringUtils;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.junit.Assert.assertNotNull;

/**
 * Author: Anatol Salanevich
 * Date: 12.02.2016
 */
@RunWith(AndroidJUnit4.class)
@SmallTest
public class MainActivityMessageTest {

    private final String MESSAGE = "{'begin':'11:25','end':'12:24','date':'2016-01-16','value':2}";
    @Rule
    public ActivityTestRule<MainActivity> mActivityTestRule =
            new ActivityTestRule<>(MainActivity.class, true, false);

    @Before
    public void setUp() throws Exception {
        Intent startIntent = new Intent();
        startIntent.putExtra(MagMessage.TAG, StringUtils.parse(MESSAGE));
        mActivityTestRule.launchActivity(startIntent);
    }

    @Test
    public void testActivity() {
        assertNotNull(mActivityTestRule.getActivity().getIntent().getExtras());
        onView(withId(R.id.fragment_message__date)).check(matches(withText("2016-01-16")));
        onView(withId(R.id.fragment_message__begin)).check(matches(withText("11:25")));
        onView(withId(R.id.fragment_message__end)).check(matches(withText("12:24")));
        String level = AppCache.getInstance().getLevels()[1];
        onView(withId(R.id.fragment_message__level)).check(matches(withText(level)));
    }

}
