/*
 * This file is generated by jOOQ.
*/
package de.warhog.fpvlaptracker.jooq.tables.records;


import de.warhog.fpvlaptracker.jooq.tables.Config;

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
public class ConfigRecord extends UpdatableRecordImpl<ConfigRecord> implements Record2<String, String> {

    private static final long serialVersionUID = -707680059;

    /**
     * Setter for <code>PUBLIC.CONFIG.CONFIG_KEY</code>.
     */
    public ConfigRecord setConfigKey(String value) {
        set(0, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.CONFIG.CONFIG_KEY</code>.
     */
    public String getConfigKey() {
        return (String) get(0);
    }

    /**
     * Setter for <code>PUBLIC.CONFIG.CONFIG_VALUE</code>.
     */
    public ConfigRecord setConfigValue(String value) {
        set(1, value);
        return this;
    }

    /**
     * Getter for <code>PUBLIC.CONFIG.CONFIG_VALUE</code>.
     */
    public String getConfigValue() {
        return (String) get(1);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Record1<String> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record2 type implementation
    // -------------------------------------------------------------------------

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> fieldsRow() {
        return (Row2) super.fieldsRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Row2<String, String> valuesRow() {
        return (Row2) super.valuesRow();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field1() {
        return Config.CONFIG.CONFIG_KEY;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Field<String> field2() {
        return Config.CONFIG.CONFIG_VALUE;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value1() {
        return getConfigKey();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String value2() {
        return getConfigValue();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigRecord value1(String value) {
        setConfigKey(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigRecord value2(String value) {
        setConfigValue(value);
        return this;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public ConfigRecord values(String value1, String value2) {
        value1(value1);
        value2(value2);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached ConfigRecord
     */
    public ConfigRecord() {
        super(Config.CONFIG);
    }

    /**
     * Create a detached, initialised ConfigRecord
     */
    public ConfigRecord(String configKey, String configValue) {
        super(Config.CONFIG);

        set(0, configKey);
        set(1, configValue);
    }
}