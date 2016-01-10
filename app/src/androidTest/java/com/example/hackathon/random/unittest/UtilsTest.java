package com.example.hackathon.random.unittest;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.example.hackathon.random.RandomAllApplication;
import com.example.hackathon.random.model.Participant;
import com.example.hackathon.random.model.Team;
import com.example.hackathon.random.utils.Utils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;

/**
 * Created by hackathon on 1/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class UtilsTest extends AndroidTestCase {

    @Before
    public void setUp() throws Exception {
        setContext(RandomAllApplication.applicationContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testDoSeed() throws Exception {
        List<Participant> participantList = Arrays.asList(new Participant("Bach", "1"), new Participant("Buu", "2"), new Participant("Quang", "3")
                , new Participant("Kien", "2"), new Participant("Hoang", "5"), new Participant("Trinh", "3"));
//        assertEquals("Number of teams should be 3", 3, (Utils.getInstance().doSeed(participantList, 3).size()));
    }
}
