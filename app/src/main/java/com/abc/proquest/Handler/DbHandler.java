package com.abc.proquest.Handler;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import com.abc.proquest.Model.userModel;

/**
 * Created by Taha on 17/10/2016.
 */

public class DbHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "ProQuest";
    private static final String TABLE_NAME = "users";

    private static final String KEY_ID = "users";
    private static final String KEY_USERNAME = "username";
    private static final String KEY_PASSWORD = "password";
    private static final String KEY_USERTYPE = "userType";

    public DbHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_NAME + "( "
                + KEY_ID + " INTEGER PRIMARY KEY NOT NULL, "
                + KEY_USERNAME + " TEXT NOT NULL UNIQUE, "
                + KEY_PASSWORD + " TEXT NOT NULL, "
                + KEY_USERTYPE + " TEXT NOT NULL )";
        sqLiteDatabase.execSQL(CREATE_CONTACTS_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(sqLiteDatabase);
    }

    public long registerUser(userModel userModel) {
        long id;
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_USERNAME, userModel.getUserName());
        values.put(KEY_PASSWORD, userModel.getPassword());
        values.put(KEY_USERTYPE, userModel.getUserType());
        try
        {
            id = db.insert(TABLE_NAME, null, values);
            db.close();
            return id;
        }
        catch (Exception ex)
        {
            return id = -1;
        }
    }

    public long validateUser(userModel userModel) {
        long i = 0;
        SQLiteDatabase db = this.getWritableDatabase();
        String validateUserQuery = "SELECT * FROM " + TABLE_NAME
                + " where " + KEY_USERNAME + " =  '" + userModel.getUserName() + "' and "
                + KEY_PASSWORD + " = '" + userModel.getPassword()+"' and "
                + KEY_USERTYPE + " = '" + userModel.getUserType() + "' ";
        Cursor c = null;
        c = db.rawQuery(validateUserQuery, null);
        c.moveToFirst();
        i = c.getCount();
        return i;
    }
}
