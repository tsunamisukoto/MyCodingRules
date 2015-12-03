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
        InsertPerson(new Person(1, "Scott", "Description", "Image".getBytes(),"Grad Developer" ,"Fare Thee Well" ,"0449877370" , "scottb@lgss.com.au", ""), db);
        InsertPerson(new Person(1, "Dan","Description","Image".getBytes(), "Grad Developer","Can You Not?" ,"0401722245" ,"danielc@lgss.com.au" ,"" ),db);
        InsertPerson(new Person(1, "Swag","Description","Image".getBytes(), "Grad Developer","Brudddaaaaaaaaaaahhhhh!" , "","thomasm@lgss.com.au" ,"" ),db);
        InsertPerson(new Person(1, "Henry","Description","Image".getBytes(),"Grad Developer" , "Is it?","" ,"henryt@lgss.com.au" , ""),db);
        InsertPerson(new Person(1, "Gav","Description","Image".getBytes(),"Senior Developer" , "That's good, I like it a lot! Consistency Consistency Consistency", "0404769458","gavinh@lgss.com.au" ,"" ),db);
        InsertPerson(new Person(1, "Marc","Description","Image".getBytes(),"Tech Officer" , "Thats a Head F@%!", "0402414915","markt@lgss.com.au" ,"" ),db);
        InsertPerson(new Person(1, "TeeMonay","Description","Image".getBytes(),"Director" ,"" , "0414689256", "thomasr@lgss.com.au","" ),db);
        InsertPerson(new Person(1, "Geoff","Description","Image".getBytes(),"Director" ,"" , "", "geoffr@lgss.com.au","" ),db);
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
        cv.put(p.colEmail, p.Email);
        cv.put(p.colPhoneNumber, p.PhoneNumber);
        if(p.LastSeenCoordinates!=null  )
            cv.put(p.colLastSeenCoordinates, p.LastSeenCoordinates);
        else
             cv.put(p.colLastSeenCoordinates,"NA");
        cv.put(p.colPositionName, p.PosName);
        cv.put(p.colQuote, p.Quote);

        cv.put(p.colEmail, p.Email);
        return db.insert(Person.TableName,Person.colID,cv);

    }

    public Person GetPerson(int id)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur = db.rawQuery("SELECT "+Person.colID+" , "+Person.colName+", "+Person.colDescription+", "+Person.colEmail+", "+Person.colPhoneNumber+", "+Person.colPositionName+", "+Person.colQuote+", "+Person.colLastSeenCoordinates+", " + Person.colImage + " from "+Person.TableName+" WHERE "+ Person.colID+ "=?",new String[]{id+""});
        if(cur.moveToFirst())
        {
            Person p = new Person(cur.getInt(cur.getColumnIndex(Person.colID)),(cur.getString(cur.getColumnIndex(Person.colName))),(cur.getString(cur.getColumnIndex(Person.colDescription))), cur.getBlob(cur.getColumnIndex(Person.colImage)),(cur.getString(cur.getColumnIndex(Person.colPositionName))) ,(cur.getString(cur.getColumnIndex(Person.colQuote))) , (cur.getString(cur.getColumnIndex(Person.colPhoneNumber))),(cur.getString(cur.getColumnIndex(Person.colEmail))) , (cur.getString(cur.getColumnIndex(Person.colLastSeenCoordinates))));
            return p;
        }
        return null;
    }
    public ArrayList<Person> GetPeople(ArrayList<Person> list)
    {
        list.clear();
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cur=db.rawQuery("SELECT "+Person.colID+" , "+Person.colName+", "+Person.colDescription+", "+Person.colEmail+", "+Person.colPhoneNumber+", "+Person.colPositionName+", "+Person.colQuote+", "+Person.colLastSeenCoordinates+", " + Person.colImage + " from "+Person.TableName+" ORDER BY " + Person.colName,new String [] {});

        for (cur.moveToFirst(); !cur.isAfterLast(); cur.moveToNext()) {

            try {
                Person p = new Person(cur.getInt(cur.getColumnIndex(Person.colID)),(cur.getString(cur.getColumnIndex(Person.colName))),(cur.getString(cur.getColumnIndex(Person.colDescription))), cur.getBlob(cur.getColumnIndex(Person.colImage)),(cur.getString(cur.getColumnIndex(Person.colPositionName))) ,(cur.getString(cur.getColumnIndex(Person.colQuote))) , (cur.getString(cur.getColumnIndex(Person.colPhoneNumber))),(cur.getString(cur.getColumnIndex(Person.colEmail))) , (cur.getString(cur.getColumnIndex(Person.colLastSeenCoordinates))));

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
        cv.put(p.colEmail, p.Email);
        cv.put(p.colPhoneNumber, p.PhoneNumber);
        if(p.LastSeenCoordinates!=null  )
        cv.put(p.colLastSeenCoordinates, p.LastSeenCoordinates);
        cv.put(p.colPositionName, p.PosName);
        cv.put(p.colQuote, p.Quote);

        cv.put(p.colEmail, p.Email);
        return db.update(Person.TableName, cv, Person.colID+"=?",  new String[]{String.valueOf(p.ID)});
    }
}