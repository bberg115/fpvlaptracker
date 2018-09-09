/*
 * This file is generated by jOOQ.
*/
package de.warhog.fpvlaptracker.jooq.tables;


import de.warhog.fpvlaptracker.jooq.Keys;
import de.warhog.fpvlaptracker.jooq.Public;
import de.warhog.fpvlaptracker.jooq.tables.records.ParticipantsRecord;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.UniqueKey;
import org.jooq.impl.TableImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.9.0"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Participants extends TableImpl<ParticipantsRecord> {

    private static final long serialVersionUID = 1468095274;

    /**
     * The reference instance of <code>PUBLIC.PARTICIPANTS</code>
     */
    public static final Participants PARTICIPANTS = new Participants();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<ParticipantsRecord> getRecordType() {
        return ParticipantsRecord.class;
    }

    /**
     * The column <code>PUBLIC.PARTICIPANTS.CHIPID</code>.
     */
    public final TableField<ParticipantsRecord, Long> CHIPID = createField("CHIPID", org.jooq.impl.SQLDataType.BIGINT.nullable(false), this, "");

    /**
     * The column <code>PUBLIC.PARTICIPANTS.NAME</code>.
     */
    public final TableField<ParticipantsRecord, String> NAME = createField("NAME", org.jooq.impl.SQLDataType.VARCHAR.length(255).nullable(false), this, "");

    /**
     * Create a <code>PUBLIC.PARTICIPANTS</code> table reference
     */
    public Participants() {
        this("PARTICIPANTS", null);
    }

    /**
     * Create an aliased <code>PUBLIC.PARTICIPANTS</code> table reference
     */
    public Participants(String alias) {
        this(alias, PARTICIPANTS);
    }

    private Participants(String alias, Table<ParticipantsRecord> aliased) {
        this(alias, aliased, null);
    }

    private Participants(String alias, Table<ParticipantsRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, "");
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public UniqueKey<ParticipantsRecord> getPrimaryKey() {
        return Keys.PK_PARTICIPANTS;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public List<UniqueKey<ParticipantsRecord>> getKeys() {
        return Arrays.<UniqueKey<ParticipantsRecord>>asList(Keys.PK_PARTICIPANTS, Keys.UQ_PARTICIPANTS);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Participants as(String alias) {
        return new Participants(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public Participants rename(String name) {
        return new Participants(name, null);
    }
}
