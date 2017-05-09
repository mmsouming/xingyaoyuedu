package com.shinread.StarPlan.Parent.engin.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by janecer on 2016/3/14.
 * email:jxc@fancyf.cn
 * des:
 */
public class UserDBA {

    private  final int DB_VERSION = 1;
    private  final String DB_NAME =  "userdba.db";
    private DBHelper dbHelper = null;
    private static UserDBA mInstance = null;

    public UserDBA (Context context) {
        dbHelper = new DBHelper(context, DB_NAME, null, DB_VERSION);
    }

    public synchronized static UserDBA getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new UserDBA(context);
        }
        return mInstance;
    }

    /**
     *
     */
    public void close() {
        dbHelper.destoryInstance();
        dbHelper = null;
    }


    public static class DBHelper extends SQLiteOpenHelper {

        private static DBHelper mInstance;

        public DBHelper(Context context, String dbname, SQLiteDatabase.CursorFactory factory,
                        int version) {
            super(context, dbname, factory, version);
        }

        public synchronized void destoryInstance() {
            close();
        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            // db.execSQL("CREATE TABLE zhimilan_shoppingcart ( _id INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, goods_id varchar(20) NOT NULL,goods_sn varchar(20) NOT NULL,goods_name varchar(50) NOT NULL,market_price varchar(20) NOT NULL,shop_price varchar(20) NOT NULL,goods_thumb varchar(100) NOT NULL,goodsnumber varchar(20) NOT NULL,addtime varchar(50) NOT NULL)");

        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        }
    }
}
