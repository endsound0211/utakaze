/*
 * This file is generated by jOOQ.
 */
package com.endsound.trpg.frontend.user.jooq;


import com.endsound.trpg.frontend.user.jooq.tables.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.annotation.Generated;

import org.jooq.Catalog;
import org.jooq.Table;
import org.jooq.impl.SchemaImpl;


/**
 * This class is generated by jOOQ.
 */
@Generated(
    value = {
        "http://www.jooq.org",
        "jOOQ version:3.11.9"
    },
    comments = "This class is generated by jOOQ"
)
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class Utakaze extends SchemaImpl {

    private static final long serialVersionUID = -1453120713;

    /**
     * The reference instance of <code>utakaze</code>
     */
    public static final Utakaze UTAKAZE = new Utakaze();

    /**
     * 使用者資訊
     */
    public final User USER = com.endsound.trpg.frontend.user.jooq.tables.User.USER;

    /**
     * No further instances allowed
     */
    private Utakaze() {
        super("utakaze", null);
    }


    /**
     * {@inheritDoc}
     */
    @Override
    public Catalog getCatalog() {
        return DefaultCatalog.DEFAULT_CATALOG;
    }

    @Override
    public final List<Table<?>> getTables() {
        List result = new ArrayList();
        result.addAll(getTables0());
        return result;
    }

    private final List<Table<?>> getTables0() {
        return Arrays.<Table<?>>asList(
            User.USER);
    }
}
