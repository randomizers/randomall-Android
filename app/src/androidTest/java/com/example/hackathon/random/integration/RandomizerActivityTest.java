package com.example.hackathon.random.integration;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import com.example.hackathon.random.R;
import com.example.hackathon.random.activity.RandomizerActivity;
import com.example.hackathon.random.utils.Constants;
import com.example.hackathon.random.utils.RecyclerViewMatcher;

import org.junit.Rule;
import org.junit.Test;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.clearText;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withSpinnerText;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;

/**
 * Created by hackathon on 1/9/16.
 */
public class RandomizerActivityTest extends BaseEspressoActivityTest {

    @Rule
    public ActivityTestRule<RandomizerActivity> mActivityRule = new ActivityTestRule<>(RandomizerActivity.class, true, false);

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
        onView(withText(R.string.randomize_by)).check(matches(isDisplayed()));
        onView(withId(R.id.method_spinner)).check(matches(isDisplayed()));
        onView(withText(R.string.category_by)).check(matches(isDisplayed()));
        onView(withId(R.id.category_spinner)).check(matches(isDisplayed()));
        onView(withText(R.string.add_name)).check(matches(isDisplayed()));
        onView(withId(R.id.randomizer_name_edit_text)).check(matches(isDisplayed()));
        onView(withText(R.string.seeds)).check(matches(isDisplayed()));
        onView(withText(R.string.optional)).check(matches(isDisplayed()));
        onView(withId(R.id.randomizer_seed_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.add_participant)).check(matches(isDisplayed()));
        onView(withText(R.string.number_of_team)).check(matches(isDisplayed()));
        onView(withId(R.id.randomizer_team_num_edit_text)).check(matches(isDisplayed()));
        onView(withId(R.id.btn_randomize)).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_randomize_by_spinner_display_item_teams() throws Exception {
        onView(withId(R.id.method_spinner)).check(matches(withSpinnerText(containsString(Constants.RANDOM_METHOD_TEAMS))));
    }

    @Test
    public void testRandomizerActivity_categorize_by_spinner_display_item_seed() throws Exception {
        onView(withId(R.id.category_spinner)).check(matches(withSpinnerText(containsString(Constants.CATEGORY_SEED))));
    }

    @Test
    public void testRandomizerActivity_add_button_empty_name() throws Exception {
        onView(withId(R.id.add_participant)).perform(click());
        onView(withText(R.string.error_name_empty)).inRoot(withDecorView(not(is(mActivityRule.getActivity()
                .getWindow().getDecorView())))).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_add_button() throws Exception {
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Bach"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_name))
                .check(matches(withText("Bach")));
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .check(matches(withText("")));
        onView(withId(R.id.randomizer_total_participants)).check(matches(withText(containsString("1"))));

        onView(withId(R.id.randomizer_name_edit_text)).perform(clearText());
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Vu"));
        onView(withId(R.id.randomizer_seed_edit_text)).perform(typeText("10"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(1, R.id.randomizer_item_name))
                .check(matches(withText("Vu")));
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(1, R.id.randomizer_item_seed))
                .check(matches(withText("10")));
        onView(withId(R.id.randomizer_total_participants)).check(matches(withText(containsString("2"))));
    }

    @Test
    public void testRandomizerActivity_edit_item_check_components() throws Exception {
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Bach"));
        onView(withId(R.id.randomizer_seed_edit_text)).perform(typeText("10"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .perform(click());

        onView(withId(R.id.edit_dialog_name)).check(matches(withText("Bach")));
        onView(withId(R.id.edit_dialog_seed)).check(matches(withText("10")));
        onView(withId(R.id.edit_dialog_save)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_dialog_delete)).check(matches(isDisplayed()));
        onView(withId(R.id.edit_dialog_cancel)).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_edit_item_empty_name() throws Exception {
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Bach"));
        onView(withId(R.id.randomizer_seed_edit_text)).perform(typeText("10"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .perform(click());

        onView(withId(R.id.edit_dialog_name)).perform(clearText());
        onView(withId(R.id.edit_dialog_save)).perform(click());
        onView(withText(R.string.edit)).check(matches(isDisplayed()));
    }

    @Test
    public void testRandomizerActivity_edit_save_success() throws Exception {
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Bach"));
        onView(withId(R.id.randomizer_seed_edit_text)).perform(typeText("10"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .perform(click());
        onView(withId(R.id.edit_dialog_name)).perform(typeText(" Vu"));
        onView(withId(R.id.edit_dialog_seed)).perform(typeText("1"));
        onView(withId(R.id.edit_dialog_save)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_name))
                .check(matches(withText("Bach Vu")));
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .check(matches(withText("101")));
    }

    @Test
    public void testRandomizerActivity_edit_delete_success() throws Exception {
        onView(withId(R.id.randomizer_name_edit_text)).perform(typeText("Bach"));
        onView(withId(R.id.randomizer_seed_edit_text)).perform(typeText("10"));
        onView(withId(R.id.add_participant)).perform(click());
        onView(new RecyclerViewMatcher(R.id.randomizer_recyclerview).atPositionOnView(0, R.id.randomizer_item_seed))
                .perform(click());
        onView(withId(R.id.edit_dialog_delete)).perform(click());
        onView(withId(R.id.randomizer_total_participants)).check(matches(withText(containsString("0"))));
    }
}
