package desnej.domov.duchodcu.tvprogram;


import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Stepan on 4/4/2015.
 */

public class SQLHelper extends SQLiteOpenHelper
{

    public SQLHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE IF NOT EXISTS " + sqlConstraits.DATABASE_TABLE +
                "(" +
                "`channel` TEXT NOT NULL," +
                "`date` TEXT NOT NULL," +
                "`id` INTEGER NOT NULL," +
                "`jmeno` TEXT NOT NULL," +
                "`delka` INTEGER NOT NULL" +
                ");");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        if (oldVersion != newVersion)
        {
            db.execSQL("DROP TABLE IF EXISTS " + sqlConstraits.DATABASE_TABLE + ";");
            onCreate(db);
        }
    }
}

class sqlConstraits
{
    public static final String DATABASE_NAME = "tv_program_db";
    public static final String DATABASE_TABLE = "program";
    public static final int DATABASE_VERSION = 1;
}