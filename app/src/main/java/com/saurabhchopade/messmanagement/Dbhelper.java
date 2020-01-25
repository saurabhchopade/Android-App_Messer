package com.saurabhchopade.messmanagement;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.strictmode.SqliteObjectLeakedViolation;

import java.util.List;

public class Dbhelper extends SQLiteOpenHelper {

//created a daatabase name databse register.db



    public Dbhelper(Context context) {
        super(context,"register.db", null, 1);
    }

    @Override

    public void onCreate(SQLiteDatabase db) {
        //  first table for login detail under the register.db

        db.execSQL("CREATE TABLE  user(username text primary key,password text)");
        //second table for member details under register

        db.execSQL("CREATE TABLE memeber(membername text primary key,mobilenumber text,startdate text,enddate text,paidamount text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
      db.execSQL("drop table if exists user");

      db.execSQL("drop table if exists memeber");
    }
    //inserting into databasse
    //function for string username and password into user database table
    public boolean insert(String username,String password)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("username",username);
        contentValues.put("password",password);

        long ins=db.insert("user",null,contentValues);

        if (ins==-1) return  false;
        else return true;




    }
    //checking an email address
    public boolean chkmail(String username)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where username=?",new String[]{username});
        if(cursor.getCount()>0) return false;
        else return true;
    }
    public boolean chkname(String membername)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from memeber where membername=?",new String[]{membername});
        if(cursor.getCount()>0) return false;
        else return true;
    }

    public boolean chkdelete(String dp)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from memeber where membername=?",new String[]{dp});
        if(cursor.getCount()>0) return false;

        else return true;
    }

    //checking emailaddress and password for wrintting a function
    public  Boolean emailpassword(String email,String password)
    {
        SQLiteDatabase db=this.getReadableDatabase();
        Cursor cursor=db.rawQuery("select * from user where username=? and password=?",new String[]{email,password});

        if (cursor.getCount()>0) return true;
        else return  false;


    }
    //to viewthe memeber
     public Cursor view_user(String mem_name)
     {
         SQLiteDatabase db=this.getWritableDatabase();
         Cursor cursor=db.rawQuery("select * from memeber where membername=?",new String[]{mem_name});
         return cursor;
     }
    public boolean insert1(String membername2,String mobilenumber2,String startdate2,String enddate2,String paidamount2)

    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("membername",membername2);
        contentValues.put("mobilenumber",mobilenumber2);
        contentValues.put("startdate",startdate2);
        contentValues.put("enddate",enddate2);
        contentValues.put("paidamount",paidamount2);


        long insp=db.insert("memeber",null,contentValues);

        if (insp==-1) return  false;
        else return true;



    }



public Integer deletedata(String membername)
{
    SQLiteDatabase db=this.getWritableDatabase();

      return db.delete("memeber","membername=?",new String[]{membername});

}



public  Integer updatedata(String membername2,String mobilenumber2,String startdate2,String enddate2,String paidamount2)
{

    SQLiteDatabase db=this.getWritableDatabase();
    ContentValues contentValues=new ContentValues();
    contentValues.put("membername",membername2);
    contentValues.put("mobilenumber",mobilenumber2);
    contentValues.put("startdate",startdate2);
    contentValues.put("enddate",enddate2);
    contentValues.put("paidamount",paidamount2);
  return   db.update("memeber",contentValues,"membername=?",new String[]{membername2});




}

}