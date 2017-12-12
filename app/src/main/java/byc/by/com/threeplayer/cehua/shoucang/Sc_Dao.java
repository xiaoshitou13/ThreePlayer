package byc.by.com.threeplayer.cehua.shoucang;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

/**
 * Created by 白玉春 on 2017/12/8.
 */

public class Sc_Dao {

    private SQLiteDatabase db;

    public Sc_Dao(Context context) {
        MyHelper dbHelper = new MyHelper(context);
        db = dbHelper.getWritableDatabase();
    }

    public  void  add(String name,String url,String urls){
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("image_url", url);
        values.put("sp_url", urls);

        db.insert("user_info", null, values);
        //    db.close();
    }


    /**
     * 查询全部数据
     * @return
     */
    public ArrayList<Sc_Bean> findAllstudent(){

        ArrayList<Sc_Bean> list = new ArrayList<Sc_Bean>();
        Cursor cursor =  db.query(true,"user_info",null,null,null, "name",null,"_id desc" ,null);
      ///  Cursor cursor = db.rawQuery("select * from user_info",null);
        for (int i = 0; i < cursor.getCount(); i++) {
            cursor.moveToNext();
            Sc_Bean info = new Sc_Bean();
            info.setName(cursor.getString(1));
            info.setImage_url(cursor.getString(2));
            info.setSp_url(cursor.getString(3));
            list.add(info);
        }

        //     cursor.close();
        //    db.close();
        return list;
    }


    public void deleteStudent1(){

        db.delete("user_info",null,null);
        //     db.close();
    }
}
