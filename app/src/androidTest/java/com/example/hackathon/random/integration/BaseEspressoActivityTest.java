package com.example.hackathon.random.integration;

import android.app.Activity;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.Espresso;
import android.support.test.espresso.IdlingPolicies;
import android.support.test.espresso.IdlingResource;
import android.support.test.espresso.intent.Intents;
import android.support.test.espresso.matcher.BoundedMatcher;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;
import android.view.View;
import android.widget.ImageView;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static android.support.test.espresso.matcher.ViewMatchers.assertThat;
import static org.hamcrest.core.IsNull.notNullValue;

/**
 * Created by eastagile on 12/25/15.
 */
@RunWith(AndroidJUnit4.class)
public abstract class BaseEspressoActivityTest {

    public static int IDLING_TIME_OUT = 100000; // 100s

    protected static Activity mActivity;

    protected List<IdlingResource> mIdlingResources = new ArrayList<>();
    private List<Integer> mIdlingTimeOuts = new ArrayList<>();

    @BeforeClass
    public static void setUpClass() throws Exception {
        System.setProperty("dexmaker.dexcache", InstrumentationRegistry.getTargetContext().getCacheDir().getPath());
    }

    @Before
    public void setUp() throws Exception {
        addIdlingResourceTimeout(IDLING_TIME_OUT);
        Intents.init();
        prepareTestActivity();
    }

    @After
    public void tearDown() throws Exception {
        Intents.release();
        releaseAllIdlingResources();
    }

    @Test
    public void testPreconditions() throws Exception {
        assertThat("Activity is initialized properly", mActivity, notNullValue());
    }

    protected void launchTestActivity(ActivityTestRule activityTestRule, Intent intent) {
        mActivity = activityTestRule.launchActivity(intent);
    }

    protected void prepareTestActivity() {
        //TODO To be implemented if needed from descendant classes.
    }

    private void releaseAllIdlingResources() {
        for (IdlingResource idlingResource : mIdlingResources) {
            Espresso.unregisterIdlingResources(idlingResource);
        }
    }

    public void addIdlingResourceTimeout(int milliseconds) {
        mIdlingTimeOuts.add(milliseconds);
        int totalTimeOut = 0;
        for (Integer timeOut : mIdlingTimeOuts) {
            totalTimeOut += timeOut;
        }
        IdlingPolicies.setMasterPolicyTimeout(
                totalTimeOut * 2, TimeUnit.MILLISECONDS);
        IdlingPolicies.setIdlingResourceTimeout(
                totalTimeOut * 2, TimeUnit.MILLISECONDS);
    }

    protected Matcher<View> withBackgroundResource(final Drawable drawableResource) {
        return new BoundedMatcher<View, View>(View.class) {
            @Override
            public boolean matchesSafely(View target) {
                return target.getBackground().getConstantState().equals(drawableResource.getConstantState());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable: " + drawableResource);
            }
        };
    }

    protected Matcher<View> withDrawableResource(final Drawable drawableResource) {
        return new BoundedMatcher<View, ImageView>(ImageView.class) {
            @Override
            public boolean matchesSafely(ImageView target) {
                return target.getDrawable().getConstantState().equals(drawableResource.getConstantState());
            }

            @Override
            public void describeTo(Description description) {
                description.appendText("with drawable: " + drawableResource);
            }
        };
    }

}