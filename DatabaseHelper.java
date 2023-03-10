package com.example.nolimits;

import static android.database.DatabaseUtils.dumpCursorToString;
import static android.database.DatabaseUtils.stringForQuery;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DatabaseHelper";

    private static final String TABLE_NAME = "places_table";
    private static final String COL_ID = "id";
    private static final String COL_NAME = "name";
    private static final String COL_ADDRESS = "address";
    private static final String COL_ACCESS = "access";
    private static final String COL_TOILETS = "toilets";




    public DatabaseHelper(Context context) {
        super(context, TABLE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_NAME + "("
                + COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT,"
                + COL_NAME + " TEXT,"
                + COL_ADDRESS + " TEXT,"
                + COL_ACCESS + " BOOLEAN,"
                + COL_TOILETS + " BOOLEAN"
                + ")";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL(String.format("DROP IF TABLE EXISTS %s", TABLE_NAME));
        onCreate(db);
    }

    public boolean addData(String name,String address, Boolean access, Boolean toilets) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_NAME, name);
        contentValues.put(COL_ADDRESS, address);
        contentValues.put(String.valueOf(COL_ACCESS), access);
        contentValues.put(String.valueOf(COL_TOILETS), toilets);
        Log.d(TAG,"I am adding \n" + COL_NAME);
        if(! checkAlreadyExist(name)) {
            long result = db.insert(TABLE_NAME, null, contentValues);
            if (result == -1) {
                return false;
            }
            return true;
        }

        return false;
    }

    public boolean checkAlreadyExist(String Place_Name)
    {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT "+ COL_NAME + " FROM "+ TABLE_NAME +" WHERE " + COL_NAME + "=?";

        Cursor cursor = db.rawQuery(query, new String[]{Place_Name});
        if (cursor.getCount() > 0)
        {
            return true;
        }
        else
            return false;
    }

    public Cursor getData() {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME;
        Cursor data = db.rawQuery(query, null);
        return data;
    }
    public String getStringData(){

        return dumpCursorToString(getData());
    }


    public int getItemID(String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "SELECT " + COL_ID + " FROM " + TABLE_NAME +
                " WHERE " + COL_NAME + " = '" + name + "'";
        int id;
        Cursor data = db.rawQuery(query, null);
        if (data.moveToFirst()) {
            id = data.getInt(0);
        } else {
            id = -1;
        }
        data.close();
        db.close();
        return id;

    }

    public String getItemFromID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();

        String dataString = getNameFromID(id) + " " + getAddressFromID(id) + " " + getAccessFromID(id) + " " + getToiletsFromID(id);


        return dataString;
    }
    public String getNameFromID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dataString = stringForQuery(db,"SELECT " + COL_NAME + " FROM "+TABLE_NAME+" WHERE " + COL_ID + " = '" + id + "'", null);

        return dataString;
    }

    public String getAddressFromID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dataString = stringForQuery(db,"SELECT " + COL_ADDRESS + " FROM "+TABLE_NAME+" WHERE " + COL_ID + " = '" + id + "'", null);

        return dataString;
    }

    public String getAccessFromID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dataString = stringForQuery(db,"SELECT " + COL_ACCESS + " FROM "+TABLE_NAME+" WHERE " + COL_ID + " = '" + id + "'", null);

        return dataString;
    }

    public String getToiletsFromID(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String dataString = stringForQuery(db,"SELECT " + COL_TOILETS + " FROM "+TABLE_NAME+" WHERE " + COL_ID + " = '" + id + "'", null);

        return dataString;
    }

    public void updateName(String newName, int id, String oldName) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_NAME +
                " = '" + newName + "' WHERE " + COL_ID + " = '" + id + "'" +
                " AND " + COL_NAME + " = '" + oldName + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newName);
        db.execSQL(query);
    }

    public void updateAddress(String newAddress, int id, String oldAddress) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "UPDATE " + TABLE_NAME + " SET " + COL_ADDRESS +
                " = '" + newAddress + "' WHERE " + COL_ID + " = '" + id + "'" +
                " AND " + COL_ADDRESS + " = '" + oldAddress + "'";
        Log.d(TAG, "updateName: query: " + query);
        Log.d(TAG, "updateName: Setting name to " + newAddress);
        db.execSQL(query);
    }

    public void deleteName(int id, String name) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_ID + " = '" + id + "'" +
                " AND " + COL_NAME + " = '" + name + "'";
        Log.d(TAG, "deleteName: query: " + query);
        Log.d(TAG, "deleteName: Deleting " + name + " from database.");
        db.execSQL(query);
    }
    public void deleteID(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "DELETE FROM " + TABLE_NAME + " WHERE "
                + COL_ID + " = " + id;
        Log.d(TAG, "Deleted " + id);
        db.execSQL(query);

    }
    public void initialize(){
        addData("Aroma", "Avenue 23", true, true);
	    addData("Big Tavern","French Road 31", true, false);
        addData("Blueprint", "Avenue 26", true, true);
        addData("Chubby Cat","Boulevard 61", false, false);
        addData("Fine Taste","Avenue 2", true, true);
        addData("Gastrognome", "Boulevard 24", true, false);
        addData("Honey Chicken","Avenue & Windsor", false, true);
        addData("PearCity","French Road 13", true, true);
        addData("Pretty Palace","Windsor 5", false, false);
        addData("RamenTown","Avenue 12", true, false);
        addData("Restaurant Carte","French Road 16", true, true);
        addData("SharkFin","Boulevard 10", true, true);
        addData("Small Tavern","Avenue 17", true, false);
        addData("Spicy King","Windsor 29", false, true);
        addData("Streetwise", "French Road 5", true, true);
        addData("Tasty Fish","Boulevard 12", true, false);
        addData("The Hot Fish", "Windsor 14", false, true);
        addData("The Mellow Grill", "Windsor 16", true, true);
    }
}