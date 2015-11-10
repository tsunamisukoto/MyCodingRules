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
    public static String CreateTable()
    {
        return  "CREATE TABLE " + TableName+ " ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT,"+colName+ " STRING NOT NULL,"+ colDescription+" STRING NOT NULL,"+colImage+ " BLOB)";
    }






    public int ID;
    public String Name;
    public String Description;
    public byte[] Image;
    public Person(int _id, String _name, String _description,byte[] _image)
    {
        ID=_id;
        Name=_name;
        Description= _description;
        Image=_image;
    }
}
