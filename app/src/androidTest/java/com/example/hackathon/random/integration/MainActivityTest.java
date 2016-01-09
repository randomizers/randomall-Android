package com.example.hackathon.random.integration;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;
import android.test.suitebuilder.annotation.LargeTest;

import com.example.hackathon.random.R;
import com.example.hackathon.random.activity.CategoryActivity;
import com.example.hackathon.random.activity.MainActivity;
import com.example.hackathon.random.activity.RandomizerActivity;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;

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
        onView(withId(R.id.left_button)).check(matches(isDisplayed()));
        onView(withId(R.id.right_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testMainActivity_components_fully_display() throws Exception {
        onView(withText(R.string.main_title_text)).check(matches(isDisplayed()));
        onView(withText(R.string.main_info_text_1)).check(matches(isDisplayed()));
        onView(withText(R.string.main_info_text_2)).check(matches(isDisplayed()));
        onView(withText(R.string.main_info_text_2)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_players)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_teams)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_groups)).check(matches(isDisplayed()));
    }

    @Test
    public void testMainActivity_players_button_click() throws Exception {
        onView(withId(R.id.btn_players)).perform(click());
        intended(hasComponent(RandomizerActivity.class.getName()));
    }

    @Test
    public void testMainActivity_teams_button_click() throws Exception {
        onView(withId(R.id.btn_teams)).perform(click());
        intended(hasComponent(CategoryActivity.class.getName()));
    }

    @Test
    public void testMainActivity_groups_button_click() throws Exception {
        onView(withId(R.id.btn_groups)).perform(click());
        intended(hasComponent(CategoryActivity.class.getName()));
    }
}
