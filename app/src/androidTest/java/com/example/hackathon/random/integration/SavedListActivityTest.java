package com.example.hackathon.random.integration;

import android.content.Intent;
import android.support.test.espresso.Espresso;
import android.support.test.rule.ActivityTestRule;

import com.example.hackathon.random.R;
import com.example.hackathon.random.activity.MainActivity;
import com.example.hackathon.random.activity.RandomizerActivity;
import com.example.hackathon.random.activity.ResultActivity;
import com.example.hackathon.random.activity.SavedListActivity;

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
import static junit.framework.Assert.assertEquals;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by hackathon on 1/10/16.
 */
public class SavedListActivityTest extends BaseEspressoActivityTest {

    @Rule
    public ActivityTestRule<SavedListActivity> mActivityRule = new ActivityTestRule<>(SavedListActivity.class, true, false);

    @Override
    protected void prepareTestActivity() {
        Intent intent = new Intent();
        launchTestActivity(mActivityRule, intent);
    }

    @Test
    public void testSavedListActivity_headers() throws Exception {
        onView(allOf(withText(R.string.app_name), withId(R.id.title_text_view))).check(matches(isDisplayed()));
        onView(withId(R.id.left_button)).check(matches(isDisplayed()));
        onView(withId(R.id.right_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testSavedListActivity_components_fully_display() throws Exception {
        Espresso.closeSoftKeyboard();
        onView(withText(R.string.start_new)).check(matches(isDisplayed()));
        onView(withId(R.id.saved_list_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void testSavedListActivity_click_start_new() throws Exception {
        onView(withText(R.string.start_new)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void testSavedListActivity_click_on_item() throws Exception {
        onView(withText("Test")).perform(click());
        intended(hasComponent(ResultActivity.class.getName()));
        onView(withId(R.id.result_recyclerview)).check(matches(isDisplayed()));
        onView(withText(R.string.reuse)).check(matches(isDisplayed()));
        onView(withText(R.string.delete)).check(matches(isDisplayed()));
    }

    @Test
    public void testSavedListActivity_delete_result() throws Exception {
        onView(withText("Test")).perform(click());
        intended(hasComponent(ResultActivity.class.getName()));
        onView(withId(R.id.result_recyclerview)).check(matches(isDisplayed()));
        onView(withText(R.string.delete)).perform(click());
        intended(hasComponent(MainActivity.class.getName()));
    }

    @Test
    public void testSavedListActivity_reuse_result() throws Exception {
        onView(withText("Test")).perform(click());
        intended(hasComponent(ResultActivity.class.getName()));
        onView(withId(R.id.result_recyclerview)).check(matches(isDisplayed()));
        onView(withText(R.string.reuse)).perform(click());
        intended(hasComponent(RandomizerActivity.class.getName()));
        onView(withText("a1")).check(matches(isDisplayed()));
    }
}
