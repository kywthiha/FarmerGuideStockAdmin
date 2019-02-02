package farm.com.farmerguidestockadmin.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
import java.util.List;

public class CropDBHelper extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "crops";
    public static final int DATABASE_VERSION = 1;

    public CropDBHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table crop (name text)");
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public List<String> getCrops() {
        List<String> cs = new ArrayList();
        SQLiteDatabase cursor = getWritableDatabase();
        Cursor cursor2 = cursor.query("crop", new String[]{"name"}, null, null, null, null, null);
        while (cursor2.moveToNext()) {
            cs.add(cursor2.getString(0));
        }
        return cs;
    }

    public void addCropList(String c) {
        SQLiteDatabase database = getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("name", c);
        database.insert("crop", null, values);
    }

    public boolean isThereCrops() {
        String[] strArr = new String[]{"name"};
        if (getWritableDatabase().query("crop", strArr, null, null, null, null, null).getCount() > 0) {
            return true;
        }
        Log.i("c", "no crops");
        return false;
    }

    public void addCrops() {
        addCropList("ကုလားပဲ (၉၂၉)");
        addCropList("ကုလားပဲ (ဗီတူး)");
        addCropList("ပဲဆီ");
        addCropList("ႏွမ္းဆီ");
        addCropList("ၾကက္သြန္နီ");
        addCropList("ၾကက္သြန္ျဖဴႀကီး (သစ္)");
        addCropList("ၾကက္သြန္ျဖဴလတ္ (သစ္)");
        addCropList("ၾကက္သြန္ျဖဴေသး (သစ္)");
    }
}
