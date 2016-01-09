package com.example.hackathon.random.integration;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.hackathon.random.R;
import com.example.hackathon.random.activity.ResultActivity;
import com.example.hackathon.random.utils.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.ViewMatchers.isDescendantOfA;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;

/**
 * Created by hackathon on 1/9/16.
 */
public class ResultActivityTest extends BaseEspressoActivityTest {
    @Rule
    public ActivityTestRule<ResultActivity> mActivityRule = new ActivityTestRule<>(ResultActivity.class, true, false);

    @Override
    protected void prepareTestActivity() {
        Intent intent = new Intent();
        launchTestActivity(mActivityRule, intent);
    }

    @Test
    public void testRandomizerActivity_headers() throws Exception {
        onView(allOf(withText(R.string.app_name), withId(R.id.title_text_view))).check(matches(isDisplayed()));
        onView(withId(R.id.left_button)).check(matches(isDisplayed()));
        onView(withId(R.id.right_button)).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_components_fully_display() throws Exception {
        onView(withText(R.string.teams)).check(matches(isDisplayed()));
        onView(withId(R.id.result_save)).check(matches(isDisplayed()));
        onView(withId(R.id.result_edit)).check(matches(isDisplayed()));
        onView(withId(R.id.result_recyclerview)).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_show_correct_teams() throws Exception {
        onView(new RecyclerViewMatcher(R.id.result_recyclerview).atPositionOnView(0, R.id.team_list_item_title))
                .check(matches(withText(containsString("1"))));
        onView(new RecyclerViewMatcher(R.id.team_list_participant_recyclerview).atPositionOnView(0, R.id.team_participant_item_name))
                .check(matches(withText(containsString("a1"))));
        onView(new RecyclerViewMatcher(R.id.team_list_participant_recyclerview).atPositionOnView(1, R.id.team_participant_item_name))
                .check(matches(withText(containsString("a2"))));
        onView(new RecyclerViewMatcher(R.id.team_list_participant_recyclerview).atPositionOnView(2, R.id.team_participant_item_name))
                .check(matches(withText(containsString("a3"))));

        onView(new RecyclerViewMatcher(R.id.result_recyclerview).atPositionOnView(1, R.id.team_list_item_title))
                .check(matches(withText(containsString("2"))));
    }

    @Test
    public void testRandomizerActivity_save_teams() throws Exception {
        onView(withId(R.id.result_save)).perform(click());

    }
}
