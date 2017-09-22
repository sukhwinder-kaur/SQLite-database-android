package com.registerlogindemo.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.registerlogindemo.data.AddImagesData;
import com.registerlogindemo.data.UserData;

import java.util.ArrayList;



public class DatabaseHandler extends SQLiteOpenHelper {
    // All Static variables
    // Database Version
    private static final int DATABASE_VERSION = 1;

    // Database Name
    private static final String DATABASE_NAME = "contactsManager";

    // Contacts table name
    private static final String TABLE_USER_DETAILS = "userDetials";
    private static final String TABLE_NAME_ADD_IMAGES = "addimages";

    // Contacts Table Columns names
    private static final String KEY_ID = "id";
    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PASSWORD = "password";

    private static final String KEY_IMAGE_ID = "id";
    private static final String KEY_IMAGE_NAME = "imageName";
    private static final String KEY_DATE = "date";

    private static final String CREATE_IMAGES_TABLES="CREATE TABLE "+TABLE_NAME_ADD_IMAGES+"("+KEY_IMAGE_ID+
            " INTEGER PRIMARY KEY,"+KEY_IMAGE_NAME+" TEXT,"+KEY_DATE+" TEXT"+")";

    public DatabaseHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_CONTACTS_TABLE = "CREATE TABLE " + TABLE_USER_DETAILS + "("
                + KEY_ID + " INTEGER PRIMARY KEY," + KEY_NAME + " TEXT," + KEY_PASSWORD + " TEXT,"
                + KEY_EMAIL + " TEXT" + ")";
        db.execSQL(CREATE_CONTACTS_TABLE);
        db.execSQL(CREATE_IMAGES_TABLES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER_DETAILS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_ADD_IMAGES);

        // Create tables again
        onCreate(db);
    }

    // Adding new User
    public void addUser(UserData userData) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_NAME, userData.getName()); // Contact Name
        values.put(KEY_EMAIL, userData.getEmail()); // Contact Phone Number
        values.put(KEY_PASSWORD, userData.getPassword()); // Contact Phone Number

        // Inserting Row
        db.insert(TABLE_USER_DETAILS, null, values);
        db.close();
        // Closing database connection
    }

    public boolean checkUser(String email) {

        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = KEY_EMAIL + " = ?";

        // selection argument
        String[] selectionArgs = {email};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER_DETAILS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }
    public boolean checkLoginUser(String email,String pass) {

        // array of columns to fetch
        String[] columns = {
                KEY_ID
        };
        SQLiteDatabase db = this.getReadableDatabase();

        // selection criteria
        String selection = KEY_EMAIL + " = ? AND "+KEY_PASSWORD + " = ?";

        // selection argument
        String[] selectionArgs = {email,pass};

        // query user table with condition
        /**
         * Here query function is used to fetch records from user table this function works like we use sql query.
         * SQL query equivalent to this query function is
         * SELECT user_id FROM user WHERE user_email = 'jack@androidtutorialshub.com';
         */
        Cursor cursor = db.query(TABLE_USER_DETAILS, //Table to query
                columns,                    //columns to return
                selection,                  //columns for the WHERE clause
                selectionArgs,              //The values for the WHERE clause
                null,                       //group the rows
                null,                      //filter by row groups
                null);                      //The sort order
        int cursorCount = cursor.getCount();
        cursor.close();
        db.close();

        if (cursorCount > 0) {
            return true;
        }

        return false;
    }

    // Updating single pasword
    public int updateUserPassword(UserData contact) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_PASSWORD, contact.getPassword());

        // updating row
        return db.update(TABLE_USER_DETAILS, values, KEY_EMAIL + " = ?",
                new String[] { String.valueOf(contact.getEmail()) });
    }

    // Insert the image to the Sqlite DB
    public void insertImage(byte[] imageBytes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(KEY_IMAGE_NAME, imageBytes);
        db.insert(TABLE_NAME_ADD_IMAGES, null, cv);
        db.close();
    }
    // Getting All Contacts
    public ArrayList<AddImagesData> getAllImages() {
        ArrayList<AddImagesData> contactList = new ArrayList<AddImagesData>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + TABLE_NAME_ADD_IMAGES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                AddImagesData contact = new AddImagesData();
                contact.setId(Integer.parseInt(cursor.getString(0)));
                byte[] blob = cursor.getBlob(cursor.getColumnIndex(KEY_IMAGE_NAME));
                 contact.setImages(blob);
                // Adding contact to list
                contactList.add(contact);
            } while (cursor.moveToNext());
        }

        // return contact list
        return contactList;
    }

    // Deleting single contact
    public void deleteImage(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME_ADD_IMAGES, KEY_ID + " = ?",
                new String[] { String.valueOf(id) });
        db.close();
    }
    public UserData getContact(String email) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_USER_DETAILS, new String[] { KEY_NAME,
                        KEY_EMAIL }, KEY_EMAIL + "=?",
                new String[] { String.valueOf(email) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        UserData contact = new UserData(cursor.getString(0),
                cursor.getString(1));
        // return contact
        return contact;
    }
}
