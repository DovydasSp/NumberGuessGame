package com.example.dovspe.NumberGuessGame;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class DatabaseHandler extends SQLiteOpenHelper {

    private static final int DATADABASE_VERSION = 1;

    private  static final String DATABASE_NAME = "WinLoseDB";

    private static final String TABLE_WL = "WinLose";

    private static final String KEY_ID = "id";
    private static final String KEY_SUNKUMAS = "sunkumas";
    private static final String KEY_SUGENERUOTAS_SK = "sk";
    private static final String KEY_SPEJIMU_SK = "spejimu";
    private static final String KEY_LIKO_SPEJIMU = "liko";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATADABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WL + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_SUNKUMAS + " INTEGER," +
                KEY_SUGENERUOTAS_SK  + " INTEGER," + KEY_SPEJIMU_SK  + " INTEGER," +
                KEY_LIKO_SPEJIMU  + " INTEGER" + ")";
        Log.d("Database", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    public void createTable()
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String CREATE_TABLE = "CREATE TABLE " + TABLE_WL + "(" + KEY_ID +
                " INTEGER PRIMARY KEY," + KEY_SUNKUMAS + " INTEGER," +
                KEY_SUGENERUOTAS_SK  + " INTEGER," + KEY_SPEJIMU_SK  + " INTEGER," +
                KEY_LIKO_SPEJIMU  + " INTEGER" + ")";
        Log.d("Database", CREATE_TABLE);
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS "+ TABLE_WL);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        onUpgrade(db, oldVersion, newVersion);
    }

    long add(Duomenys duom)//sunkumas, int sugeneruotas_sk, int spejimu, int liko_spejimu)
    {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_SUNKUMAS, duom.sunkumas);
        values.put(KEY_SUGENERUOTAS_SK, duom.sugeneruotas_sk);
        values.put(KEY_SPEJIMU_SK, duom.spejimu);
        values.put(KEY_LIKO_SPEJIMU, duom.liko_spejimu);

        long idValue = db.insert(TABLE_WL, null, values);
        db.close();
        return idValue;
    }

    public List<Duomenys> getAll()
    {
        List<Duomenys> dbDuom = new ArrayList<>();

        SQLiteDatabase db = getWritableDatabase();

        String selectQuery = "SELECT * FROM " + TABLE_WL;
        Cursor cursor = db.rawQuery(selectQuery, null);

        if(cursor.moveToFirst())
        {
            do{
                int id = Integer.parseInt(cursor.getString(0));
                int sunkumas = Integer.parseInt(cursor.getString(1));
                int sugeneruotas_sk = Integer.parseInt(cursor.getString(2));
                int spejimu = Integer.parseInt(cursor.getString(3));
                int liko_spejimu = Integer.parseInt(cursor.getString(4));
                Duomenys d = new Duomenys(id, sunkumas, sugeneruotas_sk, spejimu, liko_spejimu);
                dbDuom.add(d);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return dbDuom;
    }
}
