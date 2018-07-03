package zw.co.vokers.zoledge.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

/**
 * Created by VinceGee on 1/8/2018.
 */

public class DbObject {
    private static SongbookDatabase dbHelper;
    private SQLiteDatabase db;

    public DbObject(Context context) {
        dbHelper = new SongbookDatabase(context);
        this.db = dbHelper.getReadableDatabase();
    }

    public SQLiteDatabase getDbConnection(){
        return this.db;
    }

    public void closeDbConnection(){
        if(this.db != null){
            this.db.close();
        }
    }
}
