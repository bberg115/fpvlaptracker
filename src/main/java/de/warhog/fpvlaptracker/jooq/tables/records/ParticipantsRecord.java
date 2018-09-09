/*
 * This file is generated by jOOQ.
*/
package de.warhog.fpvlaptracker.jooq.tables.records;


import de.warhog.fpvlaptracker.jooq.tables.Participants;

import javax.annotation.Generated;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record2;
import org.jooq.Row2;
import org.jooq.impl.UpdatableRecordImpl;


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
public class ParticipantsRecord extends UpdatableRecordImpl<ParticipantsRecord> implements Record2<Long, String> {

    private static final long serialVersionUID = 738859593;

    /**
     * Setter for <code>PUBLIC.PARTICIPANTS.CHIPID</code>.
     */
    public ParticipantsRecord setChipid(Long value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.PARTICIPANTS.CHIPID</code>.
     */
    public Long getChipid() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>PUBLIC.PARTICIPANTS.NAME</code>.
     */
    public ParticipantsRecord setName(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.PARTICIPANTS.NAME</code>.
     */
    public String getName() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<Long, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<Long> field1() {
        return Participants.PARTICIPANTS.CHIPID;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Participants.PARTICIPANTS.NAME;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Long value1() {
        return getChipid();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getName();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParticipantsRecord value1(Long value) {
        setChipid(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParticipantsRecord value2(String value) {
        setName(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ParticipantsRecord values(Long value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ParticipantsRecord
     */
    public ParticipantsRecord() {
        super(Participants.PARTICIPANTS);
    }

    /**
     * Create a detached, initialised ParticipantsRecord
     */
    public ParticipantsRecord(Long chipid, String name) {
        super(Participants.PARTICIPANTS);

        set(0, chipid);
        set(1, name);
    }
}
