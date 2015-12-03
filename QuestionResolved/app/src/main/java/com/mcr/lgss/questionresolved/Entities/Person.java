package com.mcr.lgss.questionresolved.Entities;

/**
 * Created by scott on 10/11/2015.
 */
public class Person {
    public static String TableName="Person";
    public static String colID="ID";
    public static String colName="Name";
    public static  String colDescription="Description";
    public static String colImage="PersonalImage";
    public static String colPositionName="PositionName";
    public static String colQuote="Quote";
    public static String colPhoneNumber="PhoneNumber";
    public static String colEmail="Email";
    public static String colLastSeenCoordinates="LastSeenCoordinates";

    public static String CreateTable()
    {
        return  "CREATE TABLE " + TableName+ " ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+colName+ " TEXT NOT NULL,"+ colDescription+" TEXT NOT NULL,"
                + colPositionName+" TEXT NOT NULL,"+ colQuote+" TEXT NOT NULL,"+ colPhoneNumber+" TEXT NOT NULL,"
                + colEmail+" TEXT NOT NULL,"+ colLastSeenCoordinates+" TEXT NOT NULL,"+colImage+ " BLOB)";
    }






    public int ID;
    public String Name;
    public String Description;
    public String PosName;
    public  String Quote;
    public  String PhoneNumber;
    public  String Email;
    public  String LastSeenCoordinates;
    public byte[] Image;
    public Person(int _id, String _name, String _description, byte[] _image, String PositionName, String Quote, String PhoneNumber, String Email, String LastSeenCoordinates)
    {
        ID=_id;
        Name=_name;
        Description= _description;
        Image=_image;
        PosName=PositionName;
        this.Quote=Quote;
        this.PhoneNumber=PhoneNumber;
        this.Email=Email;
        this.LastSeenCoordinates=LastSeenCoordinates;
    }

    @Override
    public String toString() {
        return Name;
    }
}
