package com.example.hackathon.random.model;

import com.example.hackathon.random.database.helpers.Realmable;
import com.example.hackathon.random.database.models.RealmParticipant;
import com.example.hackathon.random.database.models.RealmResult;
import com.example.hackathon.random.database.models.RealmTeam;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hackathon on 1/9/16.
 */
public class Result implements Realmable {

    private String name;
    private List<Team> teams = new ArrayList<>();
    private String date;

    public Result() {
        name = "";
        teams = new ArrayList<>();
        date = "";
    }

    public Result(String name, List<Team> teams, String date) {
        this.name = name;
        this.teams = teams;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Team> getTeams() {
        return teams;
    }

    public void setTeams(List<Team> teams) {
        this.teams = teams;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public RealmObject toRealmObject() {
        RealmResult realmResult = new RealmResult();
        realmResult.setName(name);
        RealmList<RealmTeam> realmTeams = new RealmList<>();
        for (Team team : teams) {
            RealmTeam realmTeam = new RealmTeam();
            RealmList<RealmParticipant> realmParticipants = new RealmList<>();
            for (Participant participant : team.getParticipants()) {
                RealmParticipant realmParticipant = new RealmParticipant();
                realmParticipant.setName(participant.getName());
                realmParticipant.setSeed(participant.getSeed());
                realmParticipants.add(realmParticipant);
            }
            realmTeam.setRealmParticipants(realmParticipants);
            realmTeam.setPosition(team.getPosition());
            realmTeams.add(realmTeam);
        }
        realmResult.setRealmTeams(realmTeams);
        realmResult.setDate(date);

        return realmResult;
    }

    @Override
    public Realmable setDataFromRealmObject(RealmObject realmObject) {
        RealmResult realmResult = (RealmResult) realmObject;
        name = realmResult.getName();
        RealmList<RealmTeam> realmTeams = realmResult.getRealmTeams();
        int position = 0;
        for (RealmTeam realmTeam : realmTeams) {
            List<Participant> participants = new ArrayList<>();
            for (RealmParticipant realmParticipant : realmTeam.getRealmParticipants()) {
                Participant participant = new Participant(realmParticipant.getName(), realmParticipant.getSeed());
                participants.add(participant);
            }
            Team team = new Team(position, participants);
            teams.add(team);
            position++;
        }
        date = realmResult.getDate();

        return this;
    }
}
