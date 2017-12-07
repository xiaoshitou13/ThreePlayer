package byc.by.com.threeplayer.find.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;

import java.util.List;

import byc.by.com.threeplayer.R;
import byc.by.com.threeplayer.find.bean.Video;

/**
 * Created by 郭宝 on 2017/12/7.
 */

public class GridAdapter extends BaseAdapter{
    private List<Video.RetBean.ListBean.ChildListBean> list;
    private Context context;
    private View view1;

    public GridAdapter(List<Video.RetBean.ListBean.ChildListBean> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        Viewhodler viewhodler;
        if(view==null){
            viewhodler=new Viewhodler();
            view1 = View.inflate(context, R.layout.griditem, null);
            viewhodler.imageView= view1.findViewById(R.id.imageview);
            viewhodler.textView= view1.findViewById(R.id.text);
            view1.setTag(viewhodler);
        }else{
            viewhodler= (Viewhodler) view1.getTag();
        }
        viewhodler.textView.setText(list.get(i).getTitle());
        Glide.with(context).load(list.get(i).getPic()).priority(Priority.IMMEDIATE).into(viewhodler.imageView);

        return view1;
    }
    class Viewhodler{
        ImageView imageView;
        TextView textView;
    }
}
