package de.warhog.fpvlaptracker.service;

import de.warhog.fpvlaptracker.controllers.WebSocketController;
import de.warhog.fpvlaptracker.db.DbLayerException;
import de.warhog.fpvlaptracker.db.ParticipantsLayer;
import de.warhog.fpvlaptracker.jooq.tables.records.ParticipantsRecord;
import de.warhog.fpvlaptracker.race.entities.Participant;
import de.warhog.fpvlaptracker.race.RaceLogic;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ParticipantsDbService {

    private static final Logger LOG = LoggerFactory.getLogger(ParticipantsDbService.class);

    @Autowired
    private ParticipantsLayer dbLayer;

    @Autowired
    private RaceLogic race;

    private final List<Participant> participants = new ArrayList<>();

    public List<Participant> getAllParticipants() {
        return new ArrayList<>(participants);
    }

    public void addParticipant(Participant participant) {
        if (participants.contains(participant)) {
            throw new IllegalArgumentException("participant already existing");
        }
        participants.add(participant);
    }

    public Participant getParticipant(Long chipId) {
        for (Participant participant : participants) {
            if (Objects.equals(participant.getChipId(), chipId)) {
                return participant;
            }
        }
        throw new IllegalArgumentException("participant with chipid not found: " + chipId.toString());
    }

    public Boolean hasParticipant(Participant participant) {
        return participants.contains(participant);
    }

    public Boolean hasParticipant(Long chipId) {
        for (Participant participant : participants) {
            if (Objects.equals(participant.getChipId(), chipId)) {
                return true;
            }
        }
        return false;
    }

    public void clearParticipants() {
        participants.clear();
    }

    public void removeParticipant(Participant participant) {
        if (participants.contains(participant)) {
            race.removeParticipant(participant);
            participants.remove(participant);
        }
    }

    public ParticipantsRecord getParticipantRecordForChipIdFromDb(Long chipId) throws ServiceLayerException {
        try {
            ParticipantsRecord pr = dbLayer.getParticipantForChipId(chipId);
            return pr;
        } catch (DbLayerException ex) {
            throw new ServiceLayerException(ex.getMessage());
        }
    }

    public String getNameForChipIdFromDb(Long chipId) throws ServiceLayerException {
        ParticipantsRecord pr = getParticipantRecordForChipIdFromDb(chipId);
        return pr.getName();
    }

    public void createOrUpdateParticipantInDb(Long chipId, String name) throws ServiceLayerException {
        if (name.length() > 255) {
            name = name.substring(0, 255);
        }
        try {
            dbLayer.createOrUpdateParticipant(chipId, name);
        } catch (DbLayerException ex) {
            throw new ServiceLayerException(ex);
        }
    }

}
