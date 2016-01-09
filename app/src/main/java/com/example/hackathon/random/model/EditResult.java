package com.example.hackathon.random.model;

import com.example.hackathon.random.database.helpers.Realmable;
import com.example.hackathon.random.database.models.RealmEditResult;
import com.example.hackathon.random.database.models.RealmParticipant;

import java.util.ArrayList;
import java.util.List;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by hackathon on 1/10/16.
 */
public class EditResult implements Realmable {

    private String resultName;
    private String method;
    private String category;
    private List<Participant> participants = new ArrayList<>();
    private String teamNumber;

    public EditResult() {

    }

    public EditResult(String method, String category, List<Participant> participants, String teamNumber) {
        this.method = method;
        this.category = category;
        this.participants = participants;
        this.teamNumber = teamNumber;
    }

    public String getResultName() {
        return resultName;
    }

    public void setResultName(String resultName) {
        this.resultName = resultName;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Participant> getParticipants() {
        return participants;
    }

    public void setParticipants(List<Participant> participants) {
        this.participants = participants;
    }

    public String getTeamNumber() {
        return teamNumber;
    }

    public void setTeamNumber(String teamNumber) {
        this.teamNumber = teamNumber;
    }

    @Override
    public RealmObject toRealmObject() {
        RealmEditResult realmEditResult = new RealmEditResult();
        realmEditResult.setName(resultName);
        realmEditResult.setMethod(method);
        realmEditResult.setCategory(category);
        RealmList<RealmParticipant> realmParticipants = new RealmList<>();
        for (Participant participant : participants) {
            RealmParticipant realmParticipant = new RealmParticipant();
            realmParticipant.setName(participant.getName());
            realmParticipant.setSeed(participant.getSeed());
            realmParticipants.add(realmParticipant);
        }
        realmEditResult.setRealmParticipants(realmParticipants);
        realmEditResult.setTeamNumber(teamNumber);

        return realmEditResult;
    }

    @Override
    public Realmable setDataFromRealmObject(RealmObject realmObject) {
        RealmEditResult realmEditResult = (RealmEditResult) realmObject;
        resultName = realmEditResult.getName();
        method = realmEditResult.getMethod();
        category = realmEditResult.getCategory();
        RealmList<RealmParticipant> realmParticipants = realmEditResult.getRealmParticipants();
        for (RealmParticipant realmParticipant : realmParticipants) {
            Participant participant = new Participant(realmParticipant.getName(), realmParticipant.getSeed());
            participants.add(participant);
        }
        teamNumber = realmEditResult.getTeamNumber();
        return this;
    }
}
