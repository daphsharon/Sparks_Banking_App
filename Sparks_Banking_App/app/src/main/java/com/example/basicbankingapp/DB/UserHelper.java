package com.example.basicbankingapp.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.basicbankingapp.DB.UserContract.UserEntry;
import com.example.basicbankingapp.Data.User;

public class UserHelper extends SQLiteOpenHelper {

    String TABLE_NAME = UserEntry.TABLE_NAME;

    /** Name of the database file */
    private static final String DATABASE_NAME = "Users.db";

    /**
     * Database version. If you change the database schema, you must increment the database version.*/
    private static final int DATABASE_VERSION = 1;

    public UserHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Create a String that contains the SQL statement to create the pets table
        String SQL_CREATE_USER_TABLE =  "CREATE TABLE " + UserEntry.TABLE_NAME + " ("
                + UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " INTEGER, "
                + UserEntry.COLUMN_USER_NAME + " VARCHAR, "
                + UserEntry.COLUMN_USER_EMAIL + " VARCHAR, "
                + UserEntry.COLUMN_USER_IFSC_CODE + " VARCHAR, "
                + UserEntry.COLUMN_USER_PHONE_NO + " VARCHAR, "
                + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " INTEGER NOT NULL);";

        // Execute the SQL statement
        db.execSQL(SQL_CREATE_USER_TABLE);

        // Insert Into Table
        db.execSQL("insert into " + TABLE_NAME + " values(4301,'Super Man', 'superman@dc.com','SPARKS4301','9790865231', 100000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4302,'Bat Man', 'batman@dc.com','SPARKS4302','8685326598', 150000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4303,'Flash', 'flash@dc.com','SPARKS4303','7358269865', 12000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4304,'Green Lantern', 'glantern@dc.com','SPARKS4304','9856235965', 90000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4305,'Wonder Woman', 'wwoman@dc.com','SPARKS4305','8425682654', 50000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4306,'Aqua Man', 'aquaman@dc.com','SPARKS4306','9352692653', 68000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4307,'John Constantine', 'johnc@dc.com','SPARKS4307','9898665352', 150000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4308,'Green Arrow', 'greenarrow@dc.com','SPARKS4308','8520635201', 125000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4309,'Booster Gold', 'boosterg@dc.com','SPARKS4309','8494543462', 300000)");
        db.execSQL("insert into " + TABLE_NAME + " values(4310,'Cyborg', 'cyborg@dc.com','SPARKS4310','8256265891', 299000)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (oldVersion != newVersion) {
            // Simplest implementation is to drop all old tables and recreate them
            db.execSQL("DROP TABLE IF EXISTS " + UserEntry.TABLE_NAME);
            onCreate(db);
        }
    }

    public Cursor readAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserEntry.TABLE_NAME, null);
        return cursor;
    }

    public Cursor readParticularData (int accountNo) {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from " + UserEntry.TABLE_NAME + " where " +
                                        UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo, null);
        return cursor;
    }

    public void updateAmount(int accountNo, int amount) {
        Log.d ("TAG", "update Amount");
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("update " + UserEntry.TABLE_NAME + " set " + UserEntry.COLUMN_USER_ACCOUNT_BALANCE + " = " + amount + " where " +
                UserEntry.COLUMN_USER_ACCOUNT_NUMBER + " = " + accountNo);
    }
}