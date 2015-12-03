package com.mcr.lgss.questionresolved.Services;

/**
 * Created by scott on 10/11/2015.
 */

/**
 * Created by Scott on 19/06/2015.
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.ParseException;

import com.mcr.lgss.questionresolved.Entities.Person;

import java.lang.reflect.Array;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class DatabaseHelper extends SQLiteOpenHelper {
    public static final String dbName="CalcuPayTerDatabase";


    public DatabaseHelper(Context context) {
        super(context, dbName, null,5);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("DROP TABLE IF EXISTS "+ Person.TableName);

        db.execSQL(Person.CreateTable());
        InsertPerson(new Person(1, "Name", "Description", "Image".getBytes()), db);
        InsertPerson(new Person(1, "Name2","Description","Image".getBytes()),db);
        InsertPerson(new Person(1, "Name3","Description","Image".getBytes()),db);
        InsertPerson(new Person(1, "Name4","Description","Image".getBytes()),db);
        InsertPerson(new Person(1, "Name5","Description","Image".getBytes()),db);
        InsertPerson(new Person(1, "Name6","Description","Image".getBytes()),db);
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
        cv.put(Person.colName,p.Name);
        cv.put(Person.colDescription,p.Description);
        cv.put(Person.colImage,p.Image);
        return db.insert(Person.TableName,Person.colID,cv);

    }
    public Person GetPerson(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT "+ Person.colID+", "+ Person.colName+", " +Person.colDescription+", "+Person.colImage+" FROM "+ Person.TableName+" WHERE "+ Person.colID+ "=?",new String[]{id+""});
        if(cur.moveToFirst())
        {
            Person p = new Person(cur.getInt(cur.getColumnIndex(Person.colID)),(cur.getString(cur.getColumnIndex(Person.colName))),(cur.getString(cur.getColumnIndex(Person.colDescription))), cur.getBlob(cur.getColumnIndex(Person.colImage)));
            return p;
        }
        return null;
    }
    public ArrayList<Person> GetPeople(ArrayList<Person> list)
    {
        list.clear();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT "+Person.colID+" , "+Person.colName+", "+Person.colDescription+", " + Person.colImage + " from "+Person.TableName+" ORDER BY " + Person.colName,new String [] {});

        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {

            try {
                Person p = new Person(cur.getInt(cur.getColumnIndex(Person.colID)),(cur.getString(cur.getColumnIndex(Person.colName))),(cur.getString(cur.getColumnIndex(Person.colDescription))), cur.getBlob(cur.getColumnIndex(Person.colImage)));

                list.add(p);
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        cur.close();
        return list ;
    }

    public long UpdatePerson(Person p, SQLiteDatabase db)
    {
        if(db == null)
            db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(p.colName, p.Name);
        cv.put(p.colDescription, p.Description);
        cv.put(p.colImage, p.Image);
        return db.update(Person.TableName, cv, Person.colID+"=?",  new String[]{String.valueOf(p.ID)});
    }
}