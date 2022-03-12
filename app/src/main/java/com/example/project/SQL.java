package com.example.project;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Environment;
import android.widget.Toast;

import java.io.File;

    class SQL extends SQLiteOpenHelper {


        public static final String dbname="DataBase.db";

        public static final File path_= Environment.getExternalStorageDirectory();
        public static final String dir=path_.getAbsolutePath();
        /* public static final String path= Environment.getExternalStorageDirectory().toString(); */
        public SQL(Context context) {
            super(context, dir+"/Download/"+dbname, null, 1);
            SQLiteDatabase.openOrCreateDatabase(dir+"/Download/"+dbname,null);
        }


        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL("create table User"+"(Username Text,Password Text)");

        }

        public boolean insert(String Username,String Password)

        {
            SQLiteDatabase db = this.getWritableDatabase();// instance of the data base

            ContentValues contenvalues = new ContentValues();


            contenvalues.put("Username", Username);
            contenvalues.put("Password", Password);

            long result = db.insert("User", null, contenvalues);//will return -1 or the inserted row

            if (result == -1)
                return false;
            else
                return true;


        }

        public Cursor run(String Sql){
            SQLiteDatabase db=  this.getReadableDatabase();
            Cursor res=db.rawQuery(Sql,null);
            return res;
        }

        @Override
        public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        }

        public boolean checkuser(String Username){
            SQLiteDatabase db=this.getWritableDatabase();
            Cursor cursor=db.rawQuery("select * from User where Username = ?",new String[]{Username});
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }

        public boolean checkuserandpass(String Username,String Password){
            SQLiteDatabase db=this.getWritableDatabase();
            Cursor cursor=db.rawQuery("select * from User where Username = ? and Password = ?",new String[]{Username,Password});
            if(cursor.getCount()>0)
                return true;
            else
                return false;
        }





    }















