package byc.by.com.threeplayer.my.sqlites;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

import byc.by.com.threeplayer.my.Myuser;

/**
 * Created by 白玉春 on 2017/12/8.
 */

public class dao {

    private SQLiteDatabase db;

    public dao(Context context) {
        DBHelper dbHelper = new DBHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  void  add(String name,String url,String urls){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("url", url);
        values.put("urls", urls);

        db.insert("Myuser", null, values);
    //    db.close();
    }


    /**
     * 查询全部数据
     * @return
     */
    public ArrayList<Myuser> findAllstudent(){

        ArrayList<Myuser> list = new ArrayList<Myuser>();
        Cursor cursor = db.rawQuery("select * from Myuser",null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Myuser info = new Myuser();
            info.setName(cursor.getString(1));
            info.setUrl(cursor.getString(2));
            info.setUrls(cursor.getString(3));
            list.add(info);
        }
   //     cursor.close();
    //    db.close();
        return list;
    }


    public void deleteStudent1(){

        db.delete("Myuser",null,null);
   //     db.close();
    }
}
