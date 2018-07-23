package com.pranabchakma.mcqprepartion.UserData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.pranabchakma.mcqprepartion.UserData.UserContract.UserEntry;

/**
 * Created by Pranab on 3/29/2018.
 */

public class UserDbHelper extends SQLiteOpenHelper {
    private static final String DATABASE_NAME ="users.db";
    static final int DATABASE_VERSION = 1;
    public UserDbHelper(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String  SQL_CREATE_USERS_TABLE = "CREATE TABLE "+ UserEntry.TABLE_NAME+" ("
                +UserEntry._ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "
                +UserEntry.CATEGORY+" TEXT,"
                +UserEntry.LEVEL+" TEXT ,"
                +UserEntry.DATE_TIME+" TEXT, "
                +UserEntry.TOTAL_ANSWERED+" INTEGER NOT NULL DEFAULT 0,"
                +UserEntry.CORRECT_ANSWER+" INTEGER NOT NULL DEFAULT 0,"
                +UserEntry.WRONG_ANSWER+" INTEGER NOT NULL DEFAULT 0 );";
            sqLiteDatabase.execSQL(SQL_CREATE_USERS_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
