package com.example.hackathon.random.unit;

import android.support.test.runner.AndroidJUnit4;
import android.test.AndroidTestCase;

import com.example.hackathon.random.RandomAllApplication;
import com.example.hackathon.random.model.Person;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Created by hackathon on 1/9/16.
 */
@RunWith(AndroidJUnit4.class)
public class PersonTest extends AndroidTestCase {

    @Before
    public void setUp() throws Exception {
        setContext(RandomAllApplication.applicationContext);
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testGetName() throws Exception {
        Person person = new Person("Bach", "100");
        assertEquals("Name should be Bach","Bach", person.getName());
    }
}
