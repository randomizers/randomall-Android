package com.example.hackathon.random.model;

import com.example.hackathon.random.database.helpers.Realmable;
import com.example.hackathon.random.database.models.RealmParticipant;
import com.example.hackathon.random.database.models.RealmTeam;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hackathon on 1/9/16.
 */
public class Team implements Realmable {
    private int position;
    private List<Participant> participants = new ArrayList<>();

    public Team(int position, List<Participant> participants) {
        this.position = position;
        this.participants = participants;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    @Override
    public RealmObject toRealmObject() {
        RealmTeam realmTeam = new RealmTeam();
        realmTeam.setPosition(position);
        RealmList<RealmParticipant> realmParticipants = new RealmList<>();
        for (Participant participant : participants) {
            RealmParticipant realmParticipant = new RealmParticipant();
            realmParticipant.setName(participant.getName());
            realmParticipant.setSeed(participant.getSeed());
            realmParticipants.add(realmParticipant);
        }
        realmTeam.setRealmParticipants(realmParticipants);

        return realmTeam;
    }

    @Override
    public Realmable setDataFromRealmObject(RealmObject realmObject) {
        RealmTeam realmTeam = (RealmTeam) realmObject;
        position = realmTeam.getPosition();
        RealmList<RealmParticipant> realmParticipants = realmTeam.getRealmParticipants();
        for (RealmParticipant realmParticipant : realmParticipants) {
            Participant participant = new Participant(realmParticipant.getName(), realmParticipant.getSeed());
            participants.add(participant);
        }

        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        return !(participants != null ? !participants.equals(team.participants) : team.participants != null);

    }
}
