/*
 * This file is generated by jOOQ.
*/
package de.warhog.fpvlaptracker.jooq;


import de.warhog.fpvlaptracker.jooq.tables.Config;
import de.warhog.fpvlaptracker.jooq.tables.Laps;
import de.warhog.fpvlaptracker.jooq.tables.Participants;
import de.warhog.fpvlaptracker.jooq.tables.Races;
import de.warhog.fpvlaptracker.jooq.tables.records.ConfigRecord;
import de.warhog.fpvlaptracker.jooq.tables.records.LapsRecord;
import de.warhog.fpvlaptracker.jooq.tables.records.ParticipantsRecord;
import de.warhog.fpvlaptracker.jooq.tables.records.RacesRecord;

import javax.annotation.Generated;

import org.jooq.Identity;
import org.jooq.UniqueKey;
import org.jooq.impl.AbstractKeys;


/**
 * A class modelling foreign key relationships between tables of the <code>PUBLIC</code> 
 * schema
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Keys {

    // -------------------------------------------------------------------------
    // IDENTITY definitions
    // -------------------------------------------------------------------------

    public static final Identity<RacesRecord, Integer> IDENTITY_RACES = Identities0.IDENTITY_RACES;
    public static final Identity<LapsRecord, Integer> IDENTITY_LAPS = Identities0.IDENTITY_LAPS;

    // -------------------------------------------------------------------------
    // UNIQUE and PRIMARY KEY definitions
    // -------------------------------------------------------------------------

    public static final UniqueKey<ConfigRecord> PK_CONFIG = UniqueKeys0.PK_CONFIG;
    public static final UniqueKey<ConfigRecord> UQ_CONFIG = UniqueKeys0.UQ_CONFIG;
    public static final UniqueKey<RacesRecord> PK_RACES = UniqueKeys0.PK_RACES;
    public static final UniqueKey<RacesRecord> UQ_RACES = UniqueKeys0.UQ_RACES;
    public static final UniqueKey<LapsRecord> PK_LAPS = UniqueKeys0.PK_LAPS;
    public static final UniqueKey<LapsRecord> UQ_LAPS = UniqueKeys0.UQ_LAPS;
    public static final UniqueKey<ParticipantsRecord> PK_PARTICIPANTS = UniqueKeys0.PK_PARTICIPANTS;
    public static final UniqueKey<ParticipantsRecord> UQ_PARTICIPANTS = UniqueKeys0.UQ_PARTICIPANTS;

    // -------------------------------------------------------------------------
    // FOREIGN KEY definitions
    // -------------------------------------------------------------------------


    // -------------------------------------------------------------------------
    // [#1459] distribute members to avoid static initialisers > 64kb
    // -------------------------------------------------------------------------

    private static class Identities0 extends AbstractKeys {
        public static Identity<RacesRecord, Integer> IDENTITY_RACES = createIdentity(Races.RACES, Races.RACES.ID);
        public static Identity<LapsRecord, Integer> IDENTITY_LAPS = createIdentity(Laps.LAPS, Laps.LAPS.ID);
    }

    private static class UniqueKeys0 extends AbstractKeys {
        public static final UniqueKey<ConfigRecord> PK_CONFIG = createUniqueKey(Config.CONFIG, "pk_config", Config.CONFIG.CONFIG_KEY);
        public static final UniqueKey<ConfigRecord> UQ_CONFIG = createUniqueKey(Config.CONFIG, "uq_config", Config.CONFIG.CONFIG_KEY);
        public static final UniqueKey<RacesRecord> PK_RACES = createUniqueKey(Races.RACES, "pk_races", Races.RACES.ID);
        public static final UniqueKey<RacesRecord> UQ_RACES = createUniqueKey(Races.RACES, "uq_races", Races.RACES.ID);
        public static final UniqueKey<LapsRecord> PK_LAPS = createUniqueKey(Laps.LAPS, "pk_laps", Laps.LAPS.ID);
        public static final UniqueKey<LapsRecord> UQ_LAPS = createUniqueKey(Laps.LAPS, "uq_laps", Laps.LAPS.ID);
        public static final UniqueKey<ParticipantsRecord> PK_PARTICIPANTS = createUniqueKey(Participants.PARTICIPANTS, "pk_participants", Participants.PARTICIPANTS.CHIPID);
        public static final UniqueKey<ParticipantsRecord> UQ_PARTICIPANTS = createUniqueKey(Participants.PARTICIPANTS, "uq_participants", Participants.PARTICIPANTS.CHIPID);
    }
}