package ly.generalassemb.drewmahrt.shoppinglistver2;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Serkan on 25/10/16.
 */

public class LabSQLiteOpenHelper extends SQLiteOpenHelper {
    public static final int DATABASE_VERSION = 7;
    public static final String DATABASE_NAME = "SHOPPING_DB";
    public static final String TABLE_NAME = "SHOPPING_LIST";

    public static final String COL_ID = "_id";
    public static final String COL_ITEM_NAME = "ITEM_NAME";
    public static final String COL_DESCRIPTION = "DESCRIPTION";
    public static final String COL_PRICE = "PRICE";
    public static final String COL_TYPE = "TYPE";

    public static final String[] EXAMPLE_COLUMNS = {COL_ID,COL_ITEM_NAME,COL_DESCRIPTION,COL_PRICE,COL_TYPE};

    public static final String CREATE_TABLE =
            "CREATE TABLE " + TABLE_NAME +
                    "(" +
                    COL_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                    COL_ITEM_NAME + " TEXT, " +
                    COL_DESCRIPTION + " TEXT, " +
                    COL_PRICE + " TEXT, " +
                    COL_TYPE + " TEXT )";

    //SINGLETON STARTS
    private static LabSQLiteOpenHelper mInstance;

    public static LabSQLiteOpenHelper getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new LabSQLiteOpenHelper(context.getApplicationContext());
        }
        return mInstance;
    }

    private LabSQLiteOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    //SINGLETON ENDS

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        this.onCreate(db);
    }

    public List<ItemObject> getAllAsList() {
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME,
                new String[]{COL_ITEM_NAME, COL_DESCRIPTION, COL_PRICE, COL_TYPE},
                null,
                null,
                null,
                null,
                null);

    List<ItemObject> itemList = new ArrayList<>();

        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                itemList.add(new ItemObject(cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME)),
                        cursor.getString(cursor.getColumnIndex(COL_DESCRIPTION)),
                        cursor.getString(cursor.getColumnIndex(COL_PRICE)),
                        cursor.getString(cursor.getColumnIndex(COL_TYPE))));
                cursor.moveToNext();
            }
            cursor.close();
        }return itemList;
    }

    public List<ItemObject> itemNameSearch(String query) {
        SQLiteDatabase db = getReadableDatabase();

        Cursor cursor = db.query(TABLE_NAME,
                null,
                COL_ITEM_NAME + " LIKE ?",
                new String[] {"%"+query+"%"},
                null,
                null,
                COL_ITEM_NAME,
                null);

        List<ItemObject> itemName = new ArrayList<>();
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                ItemObject item = new ItemObject(cursor.getString(cursor.getColumnIndex(COL_ITEM_NAME)),
                        null,
                        null,
                        null);
                itemName.add(item);
                cursor.moveToNext();
            }
        }cursor.close();
        return itemName;
    }

}
