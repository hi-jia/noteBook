package chzy.org.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class NoteDb extends SQLiteOpenHelper {
	public static final String TABLE_NAME = "note";
	public static final String CONTENT = "content";
	public static final String TIME = "time";
	public static final String ID = "_id";


	public NoteDb(Context context) {
		super(context, "notes", null, 1);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("create table " + TABLE_NAME + "(" + ID
				+ " integer primary key autoincrement, "+TIME
				+ " text not null, " + CONTENT + " text not null)");
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

	}

}
