package byc.by.com.threeplayer.my.sqlites;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 白玉春 on 2017/12/8.
 */

public class DBHelper extends SQLiteOpenHelper {
    private static final String TAG = "DatabaseHelper";
    private static final String DB_NAME = "test_db";//数据库名字
    public static String TABLE_NAME = "person";// 表名
    public static String FIELD_ID = "id";// 列名
    public static String  FIELD_NAME= "name";// 列名
    private static final int DB_VERSION = 1;   // 数据库版本

    public DBHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    /**
     * 创建数据库
     * @param db
     */
    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table Myuser (id integer primary key autoincrement,name varchar(50),url integer,urls varchar(5))");
    }


    /**
     * 数据库升级
     * @param db
     * @param oldVersion
     * @param newVersion
     */
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }


}
