package com.example.hackathon.random.integration;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.hackathon.random.R;
import com.example.hackathon.random.activity.MainActivity;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

import org.junit.Rule;
import org.junit.Test;

/**
 * Created by hackathon on 1/9/16.
 */
@LargeTest
public class MainActivityTest extends BaseEspressoActivityTest {

    @Rule
    public ActivityTestRule<MainActivity> mActivityRule = new ActivityTestRule<>(MainActivity.class, true, false);

    @Override
    protected void prepareTestActivity() {
        Intent intent = new Intent();
        launchTestActivity(mActivityRule, intent);
    }

    @Test
    public void testMainActivity_headers() throws Exception {
        onView(allOf(withText(R.string.app_name), withId(R.id.title_text_view))).check(matches(isDisplayed()));
        onView(withText(R.string.back)).check(matches(isDisplayed()));
        onView(withText(R.string.next)).check(matches(isDisplayed()));
    }
}
