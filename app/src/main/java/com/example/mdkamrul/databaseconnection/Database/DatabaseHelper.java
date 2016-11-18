package com.example.mdkamrul.databaseconnection.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import com.example.mdkamrul.databaseconnection.Model.Student;

import java.util.ArrayList;

/**
 * Created by mdkamrul on 18-Nov-16.
 */

public class DatabaseHelper extends SQLiteOpenHelper{
    public  static final String DB_NAME="studentdb.db";
    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        TableAttributes tableAttributes = new TableAttributes();
        String query = tableAttributes.studentTableCreateQuery();
        try {
            db.execSQL(query);
            Log.i("Create ","Table Created");
        }catch (SQLiteException e){
            Log.i("Create Error ",e.toString());
        }


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void insertStudent(Student studentObject) {
        SQLiteDatabase dbInsert = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TableAttributes.USERNAME,studentObject.getUserName());
        values.put(TableAttributes.PASSWORD,studentObject.getPassword());
        try{
            dbInsert.insert(TableAttributes.STUDENTTABLENAME,null,values);
            Log.i("Create","Successfully Created");
        }catch (SQLiteException e){
            Log.i("Create Error",e.toString());
        }

    }

    public ArrayList<Student> getAllStudents() {
        SQLiteDatabase fetchStudent = this.getReadableDatabase();
        ArrayList<Student> arrayListAllStudent = new ArrayList<Student>();
        String query = "SELECT * FROM " + TableAttributes.STUDENTTABLENAME;
        Cursor cursor= fetchStudent.rawQuery(query,null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()){
            Student studentObject = new Student();
            studentObject.setUserName(cursor.getString(cursor.getColumnIndex(TableAttributes.USERNAME)));
            studentObject.setUserName(cursor.getString(cursor.getColumnIndex(TableAttributes.PASSWORD)));
            arrayListAllStudent.add(studentObject);
            cursor.moveToNext();
        }
        return arrayListAllStudent;
    }
}
