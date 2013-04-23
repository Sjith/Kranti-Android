package Storage;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteStatement;
import android.provider.Settings;
import model.Issue;

import java.util.ArrayList;
import java.util.List;

import static android.provider.BaseColumns._ID;


public class DataStorage extends SQLiteOpenHelper {
    private static final String TABLE_NAME = "issues";
    private static final String TITLE_COL = "title";
    private static final String DESCRIPTION_COL = "description";
    private static final String LOCATION_COL = "location";
    private static final String DB_NAME = "kranti.db";
    private static final String IMAGEPATH_COL = "imagepath";
    private Context context;

    public DataStorage(Context context) {
        super(context, DB_NAME, null, 1);
        this.context = context;
    }

    private static final String DATABASE_CREATE = "CREATE TABLE " + TABLE_NAME + " (" +
            _ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
            TITLE_COL + " TEXT NOT NULL ," +
            DESCRIPTION_COL + " TEXT, " +
            LOCATION_COL + " TEXT, " +
            IMAGEPATH_COL + " TEXT );";

    public void store(Issue issue) {
        SQLiteDatabase db = getWritableDatabase();
        SQLiteStatement statement = db.compileStatement("insert into " + TABLE_NAME + " (" + TITLE_COL + "," + DESCRIPTION_COL + "," + LOCATION_COL + "," + IMAGEPATH_COL + ") values ( ?, ?, ?, ?)");
        statement.bindString(1, issue.getTitle());
        statement.bindString(2, issue.getDescription());
        statement.bindString(3, issue.getLocation());
        statement.bindString(4, issue.getImagePath());
        statement.executeInsert();
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(DATABASE_CREATE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<Issue> get() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        List<Issue> issues = new ArrayList<Issue>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    String title = c.getString(c.getColumnIndex(TITLE_COL));
                    String description = c.getString(c.getColumnIndex(DESCRIPTION_COL));
                    String location = c.getString(c.getColumnIndex(LOCATION_COL));
                    String imagePath = c.getString(c.getColumnIndex(IMAGEPATH_COL));
                    String uuid = getDeviceId();
                    Issue issue = new Issue(title, description, location, imagePath, getDeviceId());
                    issues.add(issue);
                } while (c.moveToNext());
            }
        }
        return issues;
    }

    private String getDeviceId() {
        return Settings.Secure.getString(context.getContentResolver(),
                Settings.Secure.ANDROID_ID);
    }

    public Issue getIssueDetails() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor c = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
        String title, description, location, imagePath;
        Issue issue = null;
        List<String> titles = new ArrayList<String>();
        if (c != null) {
            if (c.moveToFirst()) {
                do {
                    title = c.getString(c.getColumnIndex(TITLE_COL));
                    description = c.getString(c.getColumnIndex(DESCRIPTION_COL));
                    location = c.getString(c.getColumnIndex(LOCATION_COL));
                    imagePath = c.getString(c.getColumnIndex(IMAGEPATH_COL));
                    issue = new Issue(title, description, location, imagePath, getDeviceId());
                } while (c.moveToNext());
            }
        }
        return issue;
    }
}
