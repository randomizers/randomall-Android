package com.example.hackathon.random.utils;

import android.util.Log;

import com.example.hackathon.random.model.Participant;
import com.example.hackathon.random.model.Team;
import com.google.common.collect.Lists;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

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

    public List<Team> doSeed(List<Participant> participants, int teamNumber) {
        List<Team> teams = new ArrayList<>();
        for (int i = 0; i < teamNumber; i++) {
            teams.add(new Team(i, new ArrayList<Participant>()));
        }

        Map<String, List<Participant>> seeds = new HashMap<>();
        for (Participant participant : participants) {
            String seed = participant.getSeed();
            if (seeds.get(seed) == null) {
                seeds.put(seed, new ArrayList<Participant>());
            }
            seeds.get(seed).add(participant);
        }
        List<List<Participant>> seedArrayList = new ArrayList<>(seeds.values());
        Collections.shuffle(seedArrayList);

        for (List<Participant> participantList : seedArrayList) {
            Collections.shuffle(participantList);
            for (Participant participant : participantList) {
                Team team = getSmallestTeam(teams);
                team.getParticipants().add(participant);
            }
        }

        return teams;
    }

    private Team getSmallestTeam(List<Team> teams) {
        Team smallestTeam = teams.get(0);
        for (Team team : teams) {
            if (smallestTeam.getParticipants().size() > team.getParticipants().size()) {
                smallestTeam = team;
            }
        }

        return smallestTeam;
    }
}
