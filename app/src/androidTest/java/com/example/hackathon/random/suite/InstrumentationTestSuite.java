package com.example.hackathon.random.suite;

import com.example.hackathon.random.integration.CategoryActivityTest;
import com.example.hackathon.random.integration.MainActivityTest;
import com.example.hackathon.random.integration.RandomizerActivityTest;
import com.example.hackathon.random.integration.ResultActivityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


/**
 * Created by EastAgile Team on 12/31/15.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({MainActivityTest.class, CategoryActivityTest.class,
        RandomizerActivityTest.class, ResultActivityTest.class})
public class InstrumentationTestSuite {
}
