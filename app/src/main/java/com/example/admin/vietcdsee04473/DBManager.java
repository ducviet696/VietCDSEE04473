package com.example.admin.vietcdsee04473;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;


import java.util.ArrayList;
import java.util.List;

public class DBManager extends SQLiteOpenHelper {
    private static String DATABASE_NAME = "exam";
    private static String TABLE_NAME = "Person";

    private String SQLquery = "create table " + TABLE_NAME +
            " ( id integer primary key autoincrement, name text, gender text, address text)";

    public DBManager(Context context) {
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(SQLquery);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public void addChampion(Person person)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", person.getName());
        values.put("gender", person.getGender());
        values.put("address", person.getAddress());
        db.insert(TABLE_NAME,null,values);
        db.close();
    }

    public List<Person> getAllStudent() {
        List<Person> listStudent = new ArrayList<>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        if (cursor.moveToFirst()) {
            do {
                Person student = new Person();
                student.setId(cursor.getInt(0));
                student.setName(cursor.getString(1));
                student.setGender(cursor.getString(2));
                student.setAddress(cursor.getString(3));
                listStudent.add(student);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return listStudent;
    }
    public int deleteStudent(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME," id =?",new String[] {String.valueOf(id)});
    }
}
