package byc.by.com.threeplayer.find.view;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.PingLun;

/**
 * Created by ASUS on 2017/12/6.
 */

public class MyAdapter extends BaseAdapter {
    private Context context;
    private List<PingLun.RetBean.ListBean> list;

    public MyAdapter(Context context, List<PingLun.RetBean.ListBean> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @SuppressLint("ViewHolder")
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        ViewHolder viewHolder=null;

        if (view==null){
            viewHolder=new ViewHolder();
            view=View.inflate(context, R.layout.panlun_item,null);
            viewHolder.tv1 =view.findViewById(R.id.lv_name);
            viewHolder.tv2 =view.findViewById(R.id.lv_msg);
            viewHolder.tv3 =view.findViewById(R.id.lv_time);
            view.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) view.getTag();
        }
        viewHolder.tv1.setText(list.get(i).getPhoneNumber());
        viewHolder.tv2.setText(list.get(i).getMsg());
        viewHolder.tv3.setText(list.get(i).getTime());
        
        
        return view;
    }

    class ViewHolder{
        TextView tv1,tv2,tv3;
    }
}
