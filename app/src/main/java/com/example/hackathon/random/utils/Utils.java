package com.example.hackathon.random.utils;

import com.example.hackathon.random.model.Participant;
import com.example.hackathon.random.model.Team;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hackathon on 1/9/16.
 */
public class Utils {
    private static Utils sInstance;

    private Utils() {
    }

    public static Utils getInstance() {
        if (sInstance == null) {
            sInstance = new Utils();
        }
        return sInstance;
    }

    public static void setInstance(Utils instance) {
        sInstance = instance;
    }

    public List<Team> calculateTeams() {
        List<Team> teams = new ArrayList<>();
        return teams;
    }
}
