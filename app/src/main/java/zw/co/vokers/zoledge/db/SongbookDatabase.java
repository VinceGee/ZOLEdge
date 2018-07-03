package zw.co.vokers.zoledge.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

/**
 * Created by VinceGee on 1/8/2018.
 */

public class SongbookDatabase extends SQLiteAssetHelper {
    private static final String DATABASE_NAMES = "zoledge";
    private static final int DATABASE_VERSION = 5;
    private final Context myContext;

    public SongbookDatabase(Context context) {
        super(context, DATABASE_NAMES, null, DATABASE_VERSION);
        // TODO Auto-generated constructor stub
        this.myContext = context;
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        if(newVersion > oldVersion){
            myContext.deleteDatabase(DATABASE_NAMES);
        }
    }
}

