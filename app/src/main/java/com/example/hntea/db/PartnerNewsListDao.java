package com.example.hnTea.db;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.example.hnTea.mvpmodel.greendaobean.PartnerNewsList;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "news".
*/
public class PartnerNewsListDao extends AbstractDao<PartnerNewsList, Long> {

    public static final String TABLENAME = "news";

    /**
     * Properties of entity PartnerNewsList.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property NewsId = new Property(1, String.class, "newsId", false, "NEWS_ID");
        public final static Property NewsTitle = new Property(2, String.class, "newsTitle", false, "NEWS_TITLE");
        public final static Property NewsTime = new Property(3, String.class, "newsTime", false, "NEWS_TIME");
        public final static Property NewsType = new Property(4, int.class, "newsType", false, "NEWS_TYPE");
    }


    public PartnerNewsListDao(DaoConfig config) {
        super(config);
    }
    
    public PartnerNewsListDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"news\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NEWS_ID\" TEXT," + // 1: newsId
                "\"NEWS_TITLE\" TEXT," + // 2: newsTitle
                "\"NEWS_TIME\" TEXT," + // 3: newsTime
                "\"NEWS_TYPE\" INTEGER NOT NULL );"); // 4: newsType
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"news\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PartnerNewsList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String newsId = entity.getNewsId();
        if (newsId != null) {
            stmt.bindString(2, newsId);
        }
 
        String newsTitle = entity.getNewsTitle();
        if (newsTitle != null) {
            stmt.bindString(3, newsTitle);
        }
 
        String newsTime = entity.getNewsTime();
        if (newsTime != null) {
            stmt.bindString(4, newsTime);
        }
        stmt.bindLong(5, entity.getNewsType());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PartnerNewsList entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String newsId = entity.getNewsId();
        if (newsId != null) {
            stmt.bindString(2, newsId);
        }
 
        String newsTitle = entity.getNewsTitle();
        if (newsTitle != null) {
            stmt.bindString(3, newsTitle);
        }
 
        String newsTime = entity.getNewsTime();
        if (newsTime != null) {
            stmt.bindString(4, newsTime);
        }
        stmt.bindLong(5, entity.getNewsType());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PartnerNewsList readEntity(Cursor cursor, int offset) {
        PartnerNewsList entity = new PartnerNewsList( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // newsId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // newsTitle
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // newsTime
            cursor.getInt(offset + 4) // newsType
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PartnerNewsList entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setNewsId(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setNewsTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setNewsTime(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setNewsType(cursor.getInt(offset + 4));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PartnerNewsList entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PartnerNewsList entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PartnerNewsList entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}