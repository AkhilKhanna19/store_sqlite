package appngo.myapp.com.appforngo;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Akhil Khanna on 14-Apr-16.
 */
public class DatabaseHandler extends SQLiteOpenHelper{

    userDetails details = new userDetails();

    public DatabaseHandler(Context context) {
        super(context, Constants.DATABASE_NAME, null, Constants.DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_NGO_TABLE = "CREATE TABLE " + Constants.TABLE_NAME + "(" + Constants._ID +
                " INTEGER PRIMARY KEY, " + Constants.COLUMN_USER_NAME + " TEXT, " + Constants.COLUMN_FIRST_NAME +
                " TEXT, " + Constants.COLUMN_LAST_NAME + " TEXT, " + Constants.COLUMN_PASSWORD + " TEXT);";


        db.execSQL(CREATE_NGO_TABLE);

        String CREATE_USER_EVENT_DETAIL = "CREATE TABLE " +Constants.TABLE_USER + "(" + Constants._ID +
                " INTEGER PRIMARY KEY," +Constants.COLUMN_USER_NAME +
                " TEXT, " +Constants.COLUMN_USER_SUBJECTS + " TEXT, " +Constants.COLUMN_EVENT_DATE +
                " TEXT, " +Constants.COLUMN_EVENT_TIME + " TEXT," +Constants.COLUMN_EVENT_PLACE + " TEXT);";
        db.execSQL(CREATE_USER_EVENT_DETAIL);


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + Constants.TABLE_NAME);
        onCreate(db);
        /*db.execSQL("DROP TABLE IF EXISTS" +Constants.tableDetails.TABLE_USER);
        onCreate(db);*/

    }


    public void addUserDetails(userDetails details){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_USER_NAME, details.getUsername());
        contentValues.put(Constants.COLUMN_FIRST_NAME, details.getFirstname());
        contentValues.put(Constants.COLUMN_LAST_NAME, details.getLastname());
        contentValues.put(Constants.COLUMN_PASSWORD, details.getPasswpord());
        //Log.d("usernmae", details.firstname);


        db.insert(Constants.TABLE_NAME, null, contentValues);
        db.close();

    }

    public void addData(String datesave, String placesave, String timesave) {
        SQLiteDatabase dbs = this.getWritableDatabase();
        ContentValues contentv = new ContentValues();
        contentv.put(Constants.COLUMN_EVENT_DATE, datesave);
        contentv.put(Constants.COLUMN_EVENT_PLACE, placesave);
        contentv.put(Constants.COLUMN_EVENT_TIME, timesave);
        dbs.insert(Constants.TABLE_USER, null, contentv);
        dbs.close();


    }

    public void addSubjects(String subjects){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(Constants.COLUMN_USER_SUBJECTS, subjects);
        db.insert(Constants.TABLE_USER, null, contentValues);
        db.close();

    }

    public userDetails userInformation() {
        String selectQuery = "SELECT  * FROM " + Constants.TABLE_NAME;
        SQLiteDatabase database = this.getReadableDatabase();
        Cursor cursor = database.rawQuery(selectQuery, null);
        //userDetails details = new userDetails();
        String data[]= null;
        if(cursor.moveToFirst()){

            do{
                details.setUsername(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_USER_NAME)));
                details.setFirstname(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_FIRST_NAME)));
                details.setLastname(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_LAST_NAME)));
                details.setPasswpord(cursor.getString(cursor.getColumnIndex(Constants.COLUMN_PASSWORD)));

                Log.d("firstname", details.firstname);

            }while (cursor.moveToNext());
        }

        return details;


    }


}
