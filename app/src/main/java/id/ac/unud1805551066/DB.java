package id.ac.unud1805551066;

import android.content.Context;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.text.Editable;

import androidx.annotation.Nullable;

public class DB extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "yogm.db";
    public static final String TABLE_NAME = "tb_yogm";
    public static final String COL_1 = "id";
    public static final String COL_2 = "nama";
    public static final String COL_3 = "no_tlp";

    public DB(@Nullable Context context){
        super(context, DATABASE_NAME, null , 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + " (id INTEGER PRIMARY KEY AUTOINCREMENT, nama TEXT, no_tlp INTEGER)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }
    public boolean insertData(String name, String value){
        SQLiteDatabase db = this.getWritableDatabase(); ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, name); contentValues.put(COL_3, value);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if (result == -1){
            return false;
        }else{
            return true;
        }
    }
    public Cursor getAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor result = db.rawQuery("SELECT * FROM "+TABLE_NAME,
                null);
        return result;
    }

    public boolean updateData(String id, String name, String value){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues(); contentValues.put(COL_1, id);
        contentValues.put(COL_2, name); contentValues.put(COL_3, value);
        db.update(TABLE_NAME, contentValues, "id = ?", new String[]{
                id });
        return true;
    }


    public int deleteData(String id){
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] { id });



    }
}

