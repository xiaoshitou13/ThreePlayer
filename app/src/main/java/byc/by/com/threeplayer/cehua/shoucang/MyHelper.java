package byc.by.com.threeplayer.cehua.shoucang;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Maibenben on 2017/12/12.
 */

public class MyHelper extends SQLiteOpenHelper {

    public MyHelper(Context context) {
        // 第一个参数：上下文，必须留下；第二个参数：数据库名称；第三个参数cursor工厂类，直接给null；第四个参数：版本号，如果不涉及升级，直接写死；
        super(context, "sc.db", null, 1);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // 创建数据库的时候被调用，而且只调用一次；已经存在的数据库，将不会被调用；
        // 创建数据库时创建一个表；
        db.execSQL("create table user_info(_id integer primary key autoincrement,name text,image_url text,sp_url text)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // 数据库版本升级时被调用；如果verson不变，这个方法也不会被调用；
    }

}