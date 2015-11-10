package com.mcr.lgss.questionresolved;

/**
 * Created by scott on 10/11/2015.
 */

/**
 * Created by Scott on 19/06/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbName="CalcuPayTerDatabase";


    public DatabaseHelper(Context context) {
        super(context, dbName, null,5);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS "+ Person.TableName);

        db.execSQL(Person.CreateTable());

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public static DateFormat timeFormat = new SimpleDateFormat("HH:mm");
    public static DateFormat dateFormat = new SimpleDateFormat("EEE dd/MM");
    public static DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    public long InsertPerson(Person p,SQLiteDatabase db)
    {
        if(db==null )
            db=this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(Person.colID,p.ID    );
        cv.put(Person.colName,p.Name);
        cv.put(Person.colDescription,p.Description);
        cv.put(Person.colImage,p.Image);
        return db.insert(Person.TableName,Person.colID,cv);

    }

}